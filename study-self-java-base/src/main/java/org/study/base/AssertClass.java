package org.study.base;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class AssertClass {
	public static void main(String[] args) {
		String tm = null;
		try {
			
			Assert.notNull(tm);
		}catch (Exception e) {
			Writer writer = new StringWriter();
			PrintWriter pw = new PrintWriter(writer);
			e.printStackTrace(pw);
			System.out.println(writer.toString());
		}
	}
}
