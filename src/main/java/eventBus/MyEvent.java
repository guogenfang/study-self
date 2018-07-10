package eventBus;

import de.greenrobot.event.EventBus;

public class MyEvent {
	public void register(){
		EventBus.getDefault().register(this);
		EventBus.getDefault().post(new MyEvent());
	}
	
	public void onEvent(MyEvent event){
        System.out.println(Thread.currentThread().getName());
    }
	
	public static void main(String[] args) throws InterruptedException {
		MyEvent event = new MyEvent();
		event.register();
		Thread.sleep(1000);
	}
}
