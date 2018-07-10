import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class TimerInterval {
	public static void main(String[] args) {
		Map<String,Integer> map = new HashMap<>();
		System.out.println(map.remove("1"));
		
		for(int i = 0;i<4;i++){
			final int n = i;
			final Timer timer = new Timer(false);
	        timer.schedule(new TimerTask() {
	            @Override
	            public void run() {
	                System.out.println(n + "--timer订阅用户超时");
	            }
	        }, 500,1000);
		}
	}
}
