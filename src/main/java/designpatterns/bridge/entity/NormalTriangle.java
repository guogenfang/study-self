package designpatterns.bridge.entity;

import javax.swing.text.Position;

import designpatterns.bridge.prop.Config;

/** 
 * 三角形类 
 * 
 */  
public class NormalTriangle extends NormalShape {  
    
   //三角形的属性  
   Position a;
   Position b;
   Position c;
     
   @Override  
   public void draw() {  
       // TODO：绘画三角形  
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
