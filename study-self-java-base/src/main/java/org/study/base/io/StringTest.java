package org.study.base.io;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class StringTest {
	public static void main(String[] args) {
		Writer w = new StringWriter();
		String s = "0000000000000000";
		PrintWriter pw = new PrintWriter(System.out);
		pw.write(s);
		pw.flush();
		System.out.println();
	}
}
