package org.study.base.reflact;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.junit.Test;

public class MethodRef {
	@Test
	public String getStr(){
		return "11";
	}
	public int getC(){
		return 0;
	}
	
	public int getCC(MethodRef ref){
		return 0;
	}
	
	public static void main(String[] args) {
		
		for (Method me : MethodRef.class.getMethods()) {
			System.out.println(me.getName());
			Annotation[] anos = me.getAnnotations();
			for (Annotation annotation : anos) {
				System.out.println(annotation);
			}
			if(me.getAnnotation(Test.class) != null ){
				System.out.println(me.getAnnotation(Test.class).timeout());
			}
		}
	}
}
