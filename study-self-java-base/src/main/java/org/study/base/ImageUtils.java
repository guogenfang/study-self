package org.study.base;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;

public class ImageUtils {
	public static final int BUFFER_SIZE = 4096;
	
	public String base64(byte[] b){
		return Base64.getEncoder().encodeToString(b);
	}
	
	public static byte[] localImg(String url){
		byte[] data = null;
		FileImageInputStream input = null;
		try {
			input = new FileImageInputStream(new File(url));
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			int numBytesRead = 0;
			while ((numBytesRead = input.read(buf)) != -1) {
				output.write(buf, 0, numBytesRead);
			}
			data = output.toByteArray();
			output.close();
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	public static byte[] remoteImg(String imageUrl) throws IOException {
        URL url = new URL(imageUrl);
        return copyToByteArray(url.openStream());
    }
	
	private static byte[] copyToByteArray(InputStream in) throws IOException {
		if (in == null) {
			return new byte[0];
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream(BUFFER_SIZE);
		copy(in, out);
		return out.toByteArray();
	}
	
	private static int copy(InputStream in, OutputStream out) throws IOException {
		try {
			int byteCount = 0;
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			while ((bytesRead = in.read(buffer)) != -1) {
				out.write(buffer, 0, bytesRead);
				byteCount += bytesRead;
			}
			out.flush();
			return byteCount;
		}
		finally {
			try {
				in.close();
			}
			catch (IOException ex) {
			}
			try {
				out.close();
			}
			catch (IOException ex) {
			}
		}
	}

	public static void saveImg(String suffix, String path, byte[] content) throws Exception{
		FileOutputStream outputStream =  new FileOutputStream(new File(path));
		outputStream.write(content);
		outputStream.close();
	}
	
	public static void generateImg(String path, String content, Integer width, Integer height) throws IOException {
        File file = new File(path + System.currentTimeMillis() + ".png");
        Font font = new Font("楷体", Font.PLAIN, 45);
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);     
        Graphics2D g2 = bi.createGraphics();
        bi = g2.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        g2= bi.createGraphics();
//        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0));
//        g2.setColor(Color.black);
        g2.setBackground(new Color(255, 255, 255, 150));
        g2.clearRect(0, 0, width, height);
        g2.setPaint(Color.RED);
        FontRenderContext context = g2.getFontRenderContext();
        g2.setFont(font);
        Rectangle2D bounds = font.getStringBounds(content, context);
        double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = -bounds.getY();
        double baseY = y + ascent;
        g2.drawString(content, (int)x, (int)baseY);
        
        ImageIO.write(bi, "png", file);
	}
	
	public static void main(String[] args) throws IOException, Exception {
		String path = "http://fdfs.xmcdn.com/group12/M04/AD/8A/wKgDXFZcIkWCvm9JAAAyByBNR0M337_mobile_large.jpg";
		System.out.println(path.substring(path.lastIndexOf(".") + 1));
		saveImg("png", "f://aa.png", remoteImg(path));
	}
}
