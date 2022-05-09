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
    public Main() throws IOException, InterruptedException {
        scanner = new Scanner(System.in);
        System.out.println("Engine one is the file engine, engine two is the network engine, and engine three and engine four are idle engines.");
        while (true){
            System.out.print("JKL " + path + ">");
            String s = scanner.nextLine();
            switch (s) {
                case "one":
                    System.out.println("Engine one starting......");
                    one = true;
                    Thread.sleep(2000);
                    System.out.println("Engine one started.");
                    break;
                case "two":
                    System.out.println("Engine two starting......");
                    two = true;
                    Thread.sleep(2000);
                    System.out.println("Engine two started.");
                    break;
                case "three":
                    System.out.println("Engine three starting......");
                    three = true;
                    Thread.sleep(2000);
                    System.out.println("Engine three started.");
                    break;
                case "four":
                    System.out.println("Engine four starting......");
                    four = true;
                    Thread.sleep(2000);
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
                    System.out.println("JKL version 1.3");
                    break;
                case "exit":
                    System.out.println("exiting");
                    System.exit(-1);
                    break;

                default:
                    if(one){
                        if(s.startsWith("cd ")){
                            String path1 = s.substring(3);
                            if (path1.equals("..")){
                                String s1 = path.split("/")[path.split("/").length -1];
                                path = path.substring(path.length() - s1.length());
                            }
                            File path2 = new File(path + path1 + "\\");
                            if(path2.exists()){
                                path = path2.getAbsolutePath();
                            }else{
                                File path3 = new File(path1);
                                if (path3.exists()){
                                    path = path3.getAbsolutePath();
                                }else{
                                    System.out.println("Error: The system cannot find the path specified");
                                }
                            }
                        }else if(s.startsWith("wget ")){
                            if(two){
                                byte[] buffer = new byte[8 * 1024];
                                String url = s.substring(5);
                                URL url1 = new URL(url);
                                URLConnection urlConnection = url1.openConnection();
                                InputStream is = urlConnection.getInputStream();
                                String name1 = url1.getFile();
                                String[] list = name1.split("/");
                                String name = list[list.length -1];
                                FileOutputStream fos = new FileOutputStream(path + "\\" + name);
                                int len;
                                while ((len = is.read(buffer)) != -1 || is.available() != 0) {
                                    fos.write(buffer, 0, len);
                                }
                                is.close();
                                fos.close();
                            }else {
                                System.out.println("Error: Engine two did not start");
                            }

                        }else if(s.startsWith("del ")){
                            String filename = s.substring(4);
                            File file = new File(path + "\\" + filename);
                            if (file.exists()){
                                file.delete();
                            }else {
                                System.out.println("file :" + filename + " is not exists.");
                            }
                        }
                        else{
                            try {
                                Process process = runtime.exec(path + "\\" + s);
                                InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "gbk");
                                int i = -1;
                                StringBuilder stringBuilder = new StringBuilder();
                                while ((i = inputStreamReader.read()) != -1){
                                    stringBuilder.append((char) i);
                                }
                                System.out.println(stringBuilder.toString());
                            }catch (IOException ioException){
                                try {
                                    Process process = runtime.exec(s);
                                    InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "GBK");
                                    int i = -1;
                                    StringBuilder stringBuilder = new StringBuilder();
                                    while ((i = inputStreamReader.read()) != -1){
                                        stringBuilder.append((char) i);
                                    }
                                    System.out.println(stringBuilder.toString());
                                }catch (IOException ioException1){
                                    System.out.println("Error : " + s + " CMD");
                                }
                            }
                        }
                    }else {
                        System.out.println("Error: Engine one did not start");
                    }
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new Main();
    }
}
