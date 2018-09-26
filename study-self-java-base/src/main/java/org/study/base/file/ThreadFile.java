package org.study.base.file;

import org.study.base.json.gson.GsonUtils;

import java.io.*;
import java.util.*;

/**
 * [简要描述]:
 *
 * @Author ggf
 * @Date 2018/9/24 0:52
 **/
public class ThreadFile {

    public static synchronized void read1(String name, FileInfo info) throws Exception {
        Set<String> set = new HashSet<>();
        File file = new File("E:/a.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String str = "";
        while (Objects.nonNull(str = bufferedReader.readLine())) {
            if (Objects.isNull(name) || !name.equals(GsonUtils.parseJsonWithGson(str, FileInfo.class).getName())) {
                set.add(str);
            }
        }
        bufferedReader.close();

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
        set.stream().forEach(r -> {
            try {
                bufferedWriter.write(r + "\r\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        if(Objects.nonNull(info)){
            bufferedWriter.write(GsonUtils.parseObjectToString(info) + "\r\n");
        }
        bufferedWriter.close();

    }

    public static void accessPoi() throws Exception {
        File file = new File("E:/a.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        String str = null;
        long offset = 0;
        while (Objects.nonNull(str = raf.readLine())) {
            long curPoi = raf.getFilePointer();
            if (str.contains("125.txt")) {
                raf.seek(offset);
                byte[] t = new byte[(int) (curPoi - offset) - 1];
                raf.write(t);
                raf.write("\n".getBytes());
                raf.seek(curPoi);
            }
            offset = raf.getFilePointer();
            System.out.println(raf.getFilePointer() + "------" + str);
        }
        System.out.println("-----------------------------------");
        raf.close();
    }

    public static void main(String[] args) throws Exception {
        for(int i = 0; i < 100; i++){
            final int n = i;
            new Thread(()->{
                try {
                    Thread.sleep(new Random().nextInt(100));
                    read1(null, new FileInfo(n + "--1233445adfaf","45678",154));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

        Thread.sleep(1000);
        for(int i = 0; i < 80; i++){
            final int n = i;
            new Thread(()->{
                try {
                    Thread.sleep(new Random().nextInt(100));
                    read1(n + "--1233445adfaf", null);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
