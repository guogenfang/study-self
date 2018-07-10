package org.study.base.file;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GetNetImg {
	public static void main(String[] args) throws IOException {
		String url = "http://192.168.62.90:8080/cmd.txt";
		File file = new File(url);
		//Files.lines(Paths.get(URI.create(url))).forEach(System.out::println);
	}
}
