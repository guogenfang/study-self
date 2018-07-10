package org.study.base.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.FileAttribute;

import org.junit.Test;

public class FilesTest {

	@Test
	public void readInLineFile() throws IOException {
		URI uri = URI.create("http://www.java2s.com/Tutorials/Java/java.net/URI/Java_URI_getScheme_.htm");
		System.out.println(uri.getScheme());
		System.out.println(uri.getQuery());
		//Files.lines(path).forEach(System.out::println);
	}
	
	@Test
	public void readFile() throws IOException {
		Path path = Paths.get("F://test//user1.json");
		System.out.println(Files.exists(path));
		Files.lines(path).forEach(System.out::println);
		
		BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
		String line;
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
		reader.close();
	}

	@Test
	public void copyFile() throws IOException {
		Path path = Paths.get("F://test//user1.json");
		Path path2 = Paths.get("F://test//copy_user1.json");
		Files.copy(path, path2, StandardCopyOption.REPLACE_EXISTING);//REPLACE_EXISTING:文件存在，就替换
	}
	
	@Test
	public void writeFile() throws IOException {
		Path src = Paths.get("F://test//us.txt");
		if(!Files.exists(src)){
			Files.createFile(src);
		}
	    BufferedWriter writer = Files.newBufferedWriter(src, StandardCharsets.UTF_8,StandardOpenOption.APPEND); // 追加
	    writer.write("hello word Path");
	    writer.close();
	}
	
	@Test
	public void delFile() throws IOException {
		Path path = Paths.get("F://test//user1.json");
		Files.delete(path);
	}

	@Test
	public void createFile() {
		new Thread(() -> {
			Path path = Paths.get("F:\\test\\data");// F://test//data 失败
			Path path02 = Paths.get("F://test//data//a.json");
			try {
				Files.createDirectories(path);
				Files.createFile(path02);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	@Test
	public void listFile() throws IOException {
		Path path = Paths.get("F://test");
		Files.list(path).forEach(System.out::println);
	}
	
	@Test
	public void watchFile() throws IOException, InterruptedException {
		Path path = Paths.get("F://test");
		WatchService service = FileSystems.getDefault().newWatchService();
		WatchKey key = path.register(service, StandardWatchEventKinds.ENTRY_CREATE,
												StandardWatchEventKinds.ENTRY_DELETE,
												StandardWatchEventKinds.ENTRY_MODIFY);
		while(true){
			key = service.take();
			for (WatchEvent<?> event : key.pollEvents()) {
				if(event.kind() == StandardWatchEventKinds.ENTRY_CREATE){
					System.out.println("目录下有文件创建");
				}
				if(event.kind() == StandardWatchEventKinds.ENTRY_MODIFY){
					System.out.println("目录下有文件被修改");
				}
			}
			key.reset();
		}
	}
}
