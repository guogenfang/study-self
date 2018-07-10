package observer.push;

public class ConcreteObserver implements Observer {
    //观察者的状态
    private String observerState;
    
    /**
     * 更新观察者的状态，使其与目标的状态保持一致
     */
    public void update(String state) {
        observerState = state;
        System.out.println("观察者接收----状态为："+observerState);
    }

}