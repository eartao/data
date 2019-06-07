package com.data.transfor;

import com.alibaba.fastjson.JSONObject;

import java.io.*;

public class TestMain {
    public static void main(String[] args) {
        try {
            String s = "47 Fun At School ";
            System.out.println(s);
            System.out.println(s.substring(0, s.lastIndexOf(" ")));
            File file = new File("C:\\Users\\86181\\Desktop\\data\\img\\47 Fun At School\\47 Fun At School.json");
            FileWriter writer = new FileWriter(file);
            JSONObject object = new JSONObject();
            object.put("aa",34324);
            writer.write(object.toJSONString());
            System.out.println(11111111);
            writer.close();
        } catch (Exception e){
            e.printStackTrace();
        }
//        int j = 0;
//        for (int i = 0;i<10;i++){
//            while (j<10){
//                System.out.println(j);
//                if(j==5){
//                    break;
//                }
//                j++;
//            }
//            System.out.println("外层："+i);
//        }
    }
    public static void main3(String[] args) {
        String s = "audio_1_1$$0.03,0.06";
        String aa = "book.txt";
        System.out.println(aa.substring(0,aa.indexOf(".")));
        System.out.println(s.substring(s.lastIndexOf("$") + 1, s.indexOf(",")));
        System.out.println(s.substring(s.indexOf(",") + 1));

    }
    public static void main2(String[] args) {
        File fileImg = new File("C:\\Users\\86181\\Desktop\\data\\bookTxt"+"\\1 At The Market.txt");
        try {
            File file = new File("C:\\Users\\86181\\Desktop\\test.txt");
            FileWriter writer = new FileWriter(file);
            JSONObject object = new JSONObject();
            object.put("a",123);
            object.put("b",334);
            writer.write(object.toJSONString());
            writer.close();
            FileReader reader = new FileReader(fileImg);
            BufferedReader br = new BufferedReader(reader);//读取单个文件，例如xxx.txt
            String lineStr;
//            for (int i = 1;(lineStr = br.readLine()) != null;i++){
//                System.out.println(lineStr+"===="+i);
//            }
//            while ((lineStr = br.readLine()) != null){
//                System.out.println(lineStr);
//            }
            System.out.println("=======");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void main1(String[] args) {
        String a = "text_1_1$$At the Market";
        System.out.println(a.substring(a.indexOf("-"), a.lastIndexOf("-")));
        File file = new File("C:\\Users\\86181\\Desktop\\data");
        File[] files = file.listFiles();
        try {
            for (File fi:files) {
                System.out.println(fi.toString());
                System.out.println(111111111);
                System.out.println(fi.getName());
//                System.out.println(fi.getAbsoluteFile());
//                System.out.println(fi.getAbsolutePath());
//                System.out.println(fi.getCanonicalFile());
//                System.out.println(fi.getCanonicalPath());
                System.out.println(11111111);
                if(fi.isDirectory()){
                    File[] files1 = fi.listFiles();
                    for (File f:files1) {
                        System.out.println(f.getName());
//                        System.out.println(fi.getPath()+"1 At The Market.txt");
//                        System.out.println(fi.getCanonicalPath());
//                        System.out.println(fi.getAbsolutePath());
                        FileReader reader = new FileReader(file.getPath()+"\\"+fi.getName()+"\\1 At The Market.txt");
                        BufferedReader bf = new BufferedReader(reader);
                        String line;
                        while ((line = bf.readLine())!=null){
                            System.out.println(line);
                        }
                    }
                }

//                FileReader reader = new FileReader(fi);
//                BufferedReader bf = new BufferedReader(reader);
//                String line;
//                while ((line = bf.readLine())!=null){
//                    System.out.println(line);
//                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
