package com.xxx.JKL;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Main {
    Scanner scanner;
    boolean one;
    boolean two;
    boolean three;
    boolean four;
    String path = "C:\\";
    Runtime runtime = Runtime.getRuntime();
    public Main() throws IOException {
        scanner = new Scanner(System.in);
        while (true){
            System.out.print("JKL " + path + ">");
            String s = scanner.nextLine();
            switch (s) {
                case "one":
                    System.out.println("Engine one starting......");
                    one = true;
                    System.out.println("Engine one started.");
                    break;
                case "two":
                    System.out.println("Engine two starting......");
                    two = true;
                    System.out.println("Engine two started.");
                    break;
                case "three":
                    System.out.println("Engine three starting......");
                    three = true;
                    System.out.println("Engine three started.");
                    break;
                case "four":
                    System.out.println("Engine four starting......");
                    four = true;
                    System.out.println("Engine four started.");
                    break;
                case "shutdown /p":
                    runtime.exec("shutdown /p");
                    break;
                case "shutdown /s":
                    runtime.exec("shutdown /s");
                    break;
                case "shutdown /a":
                    runtime.exec("shutdown /a");
                    break;
                case "restart":
                    runtime.exec("shutdown /r");
                    break;
                case "JKL":
                    System.out.println("JKL version 1.1");
                    break;

                default:
                    if(s.startsWith("cd ")){
                        String path1 = s.substring(3);
                        File path2 = new File(path + path1 + "\\");
                        if(path2.exists()){
                            path = path2.getAbsolutePath();
                        }else{
                            File path3 = new File(path1);
                            if (path3.exists()){
                                path = path3.getAbsolutePath();
                            }else{
                                System.out.println("错误：系统找不到指定的路径");
                            }
                        }
                    }else if(s.startsWith("wget ")){
                        byte[] buffer = new byte[8 * 1024];
                        String url = s.substring(5);
                        URL url1 = new URL(url);
                        URLConnection urlConnection = url1.openConnection();
                        InputStream is = urlConnection.getInputStream();
                        String name1 = url1.getFile();
                        String[] list = name1.split("/");
                        String name = list[list.length -1];
                        FileOutputStream fos = new FileOutputStream(path + name);
                        int len;
                        while ((len = is.read(buffer)) != -1 || is.available() != 0) {
                            fos.write(buffer, 0, len);
                        }

                    }
                    else{
                        try {
                            Process process = runtime.exec(path + s);
                            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "gbk");
                            int i = -1;
                            StringBuilder stringBuilder = new StringBuilder();
                            while ((i = inputStreamReader.read()) != -1){
                                stringBuilder.append((char) i);
                            }
                            System.out.println(stringBuilder.toString());
                        }catch (IOException ioException){
                            System.out.println("Error : " + s + " CMD");
                        }
                    }

            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Main();
    }
}
