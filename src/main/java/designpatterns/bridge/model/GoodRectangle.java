package designpatterns.bridge.model;

import designpatterns.bridge.ShapeDraw;

/** 
 * 按照Bridge设计模式设计的矩形类 
 * 
 */  
public class GoodRectangle extends GoodShape {  
  
    public GoodRectangle(ShapeDraw draw){  
        this._draw = draw;   
    }  
      
    @Override  
    void draw() {  
        this._draw.drawRectangle();  
    }  
  
    @Override
    public String toString() {
    	return "矩形类";
    }
}  

