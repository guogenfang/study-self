package consumerAndProductor;

public class TestConsumer implements Runnable{

	TestQueue obj;  
    
    public void TestProduct(TestQueue tq){  
        this.obj=tq;  
    }  
      
    public void run() {  
        for(int i=0;i<10;i++){  
            try {  
                obj.product("test"+i);  
            } catch (Exception e) {               
                e.printStackTrace();  
            }  
        }  
    }  

}
