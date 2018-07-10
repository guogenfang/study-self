package designpatterns.bridge.entity;

import javax.swing.text.Position;

import designpatterns.bridge.prop.Config;

/** 
 * 矩形类 
 * 
 */  
public class NormalRectangle extends NormalShape {  
  
    //矩形的属性  
    Position m;  
    int length;  
    int width;  
      
    @Override  
    public void draw() {  
        // TODO: 绘画矩形  
        if(Config.CURRENT_SYSTEM == Config.WINDOWS){  
            //TODO: 调用Windows的画图方法  
        }  
        else if( Config.CURRENT_SYSTEM == Config.LINUX){  
            //TODO: 调用Linux的画图方法  
        }  
        else if( Config.CURRENT_SYSTEM == Config.MAC){  
            //TODO: 调用Mac的画图方法  
        }  
    }  
  
}  