package designpatterns.bridge.platform;

import designpatterns.bridge.ShapeDraw;

/** 
 * Windwos上的画图实现 
 * 
 */  
public class WindowsDraw implements ShapeDraw {

	public void drawTriangle() {
		
	}

	public void drawRectangle() {
		
	} 
	
	@Override
    public String toString() {
    	return "window draw";
    }
}  