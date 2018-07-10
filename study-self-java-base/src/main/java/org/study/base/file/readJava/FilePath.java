package org.study.base.file.readJava;

public class FilePath {
	public static void main(String[] args) {
		String path = FilePath.class.getResource("/").getPath();
		path = path.substring(1, path.length() - 1);
		System.out.println(FilePath.class.getResource("/").getPath());
	}
}
