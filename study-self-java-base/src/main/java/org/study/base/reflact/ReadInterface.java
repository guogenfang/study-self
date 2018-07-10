package org.study.base.reflact;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.study.base.file.ScanClassPermissions;

public class ReadInterface {
	public static void main(String[] args) throws Exception {
		Class<?> cls = Class.forName("file.BaseInterface");
		ScanClassPermissions clsPermissions = cls.getAnnotation(ScanClassPermissions.class);
		if(clsPermissions != null){
			Arrays.stream(clsPermissions.value()).forEach(val -> System.out.print(val));
		}
		System.out.println();
		Method [] methods = cls.getMethods();
		Arrays.stream(methods).forEach(mth -> {
			ScanClassPermissions permissions = mth.getAnnotation(ScanClassPermissions.class);
			if(permissions != null){
				Arrays.stream(permissions.value()).forEach(val -> System.out.print(val));
			}
			System.out.println();
		});
	}
}
