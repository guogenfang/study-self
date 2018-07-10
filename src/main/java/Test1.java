import java.util.concurrent.TimeUnit;

public class Test1 {
	public static void main(String[] args) {
		Long overTime =  TimeUnit.NANOSECONDS.convert(1, TimeUnit.SECONDS) + System.nanoTime();
		System.out.println(overTime);
		Long t2 = TimeUnit.NANOSECONDS.convert(overTime - System.nanoTime() , TimeUnit.NANOSECONDS);
		System.out.println(t2);
	}
}
