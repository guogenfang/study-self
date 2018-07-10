package designpatterns.bridge.platform;

import designpatterns.bridge.ShapeDraw;

/** 
 * Linux上的画图实现 
 * 
 */  
public class LinuxDraw implements ShapeDraw {  
  
    public void drawTriangle() {  
    }  
  
    public void drawRectangle() {  
    }  
    
    @Override
    public String toString() {
    	return "linux draw";
    }
}  