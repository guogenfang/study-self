package designpatterns.bridge;

/** 
 * 按照Bridge设计模式进行设计的画图的接口，封装了跨平台不同的实现 
 * 
 */  
public interface ShapeDraw {  
  
    public void drawTriangle();  
    public void drawRectangle();  
}  
