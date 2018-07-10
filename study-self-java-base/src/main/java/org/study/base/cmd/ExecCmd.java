package org.study.base.cmd;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ExecCmd {
	public static void main(String[] args) {
		new Thread(new Runnable() {
			public void run() {
				try {
					String [] cmd = {"/bin/sh","-c","cd /c;rm -rf current;ln -s 20150809 current"};
					Process process = Runtime.getRuntime().exec(cmd);
					InputStream inputStream = process.getInputStream();
					BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
					reader.lines().forEach(System.out::println);
				}
				catch (Exception e) {
					e.getMessage();
				}
			}
		}).start();
	}
}
