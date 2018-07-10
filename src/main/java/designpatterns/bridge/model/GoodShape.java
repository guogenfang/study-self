package designpatterns.bridge.model;

import designpatterns.bridge.ShapeDraw;

/** 
 * 在NormalShape的基础上，增加Bridge设计模式的实现，使其更加适应于跨平台 
 * 
 */  
abstract public class GoodShape {  
  
    protected ShapeDraw _draw;  //将不同平台的实现封装到一个新的接口ShapeDraw  
    abstract void draw();  
    
    //如果重写类没有重写toString，它将执行
    @Override
    public String toString() {
    	return "当前图形类名：" + _draw.getClass().getName();
    }
} 