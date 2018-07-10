package observer.pull;

public class ConcreteObserver implements Observer {
    //观察者的状态
    private String observerState;
    
    /**
     * 更新观察者的状态，使其与目标的状态保持一致
     */
    public void update(Subject subject) {
        observerState = ((ConcreteSubject)subject).getState();
        System.out.println("观察者状态为："+observerState);
    }

}