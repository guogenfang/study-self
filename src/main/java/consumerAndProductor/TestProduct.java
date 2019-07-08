package consumerAndProductor;

public class TestProduct implements Runnable{

	TestQueue obj;  
    
    void TestConsumer(TestQueue tq){  
        this.obj=tq;  
    }  

    @Override
    public void run() {               
        try {  
            for(int i=0;i<10;i++){  
                obj.consumer();  
            }             
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  

}
