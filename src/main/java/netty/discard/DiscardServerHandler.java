package netty.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter{
	
		//接收数据
		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			 ByteBuf in = (ByteBuf) msg;
			    try {
			        while (in.isReadable()) { // (1)
			            System.out.print((char) in.readByte());
			            System.out.flush();
			        }
			    } finally {
			        ReferenceCountUtil.release(msg); // (2)
			    }
		}
		
	    @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
	        // Close the connection when an exception is raised.
	        cause.printStackTrace();
	        ctx.close();
	    }
}
