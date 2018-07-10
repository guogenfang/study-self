package designpatterns.bridge.model;

import designpatterns.bridge.ShapeDraw;

/** 
 * 按照Bridge设计模式设计的三角形类 
 * 
 */  
public class GoodTriangle extends GoodShape {  
  
	public GoodTriangle(ShapeDraw draw){  
        this._draw = draw;  
    }  
      
    @Override  
    void draw() {  
        // TODO Auto-generated method stub  
        this._draw.drawTriangle();  
    }  
  
}  

