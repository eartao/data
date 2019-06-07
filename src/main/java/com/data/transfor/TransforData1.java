package com.data.transfor;

import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TransforData1 {


    public static void main(String[] args) {
        String bookPath = "C:\\Users\\86181\\Desktop\\data\\bookTxt";
        String videoPath = "C:\\Users\\86181\\Desktop\\data\\video";
        String videoPointPath = "C:\\Users\\86181\\Desktop\\data\\videoPoint";
        String imgPath = "C:\\Users\\86181\\Desktop\\data\\img";

        StringBuffer bookText = new StringBuffer();
        StringBuffer error = new StringBuffer();
        int successNum = 0;
        int errorNum = 0;
        try {
            JSONObject object = new JSONObject();
            File file = new File(bookPath);
            if(null == file){
                System.out.println(file+"文件不能为空");
                return;
            }
            if(!file.exists()){
                System.out.println(file+"文件不存在");
                return;
            }
            if(file.isDirectory()){
                FileWriter writer = null;
                File[] files = file.listFiles();
                for (File fi:files) {   //此时都是文件了，没有文件夹
                    boolean falg = true;
                    String bookName = "";
                    if(null == fi || !fi.exists()){
                        System.out.println(fi+"文件不存在");
                        errorNum++;
                        continue;
                    }
                    String name = fi.getName();
                    if(StringUtils.isEmpty(name)){
                        System.out.println(fi.getAbsolutePath()+"文件不存在");
                        errorNum++;
                        continue;
                    }
                    bookName = name.substring(0,name.lastIndexOf("."));
                    if(StringUtils.isEmpty(bookName)){
                        System.out.println(fi.getAbsolutePath()+"文件不存在");
                        errorNum++;
                        continue;
                    }
                    object.put("book_id","21404342695231488");
                    object.put("book_name",bookName);
                    FileReader reader = new FileReader(fi);
                    BufferedReader br = new BufferedReader(reader);//读取单个文件，例如xxx.txt
                    String lineStr;

                    List<JSONObject> listPage = new ArrayList<>();

                    //读取图片
                    File fileImg = new File(imgPath+"\\"+bookName);
                    if(null == fileImg || !fileImg.exists()){
                        System.out.println("图片文件夹为空："+bookName);
                        errorNum++;
                        continue;
                    }
                    String[] list = fileImg.list();
                    if(null == list || list.length == 0){
                        System.out.println("图片不能为空："+bookName);
                        errorNum++;
                        continue;
                    }
                    int lineNum = -1;
                    int pageNum = 1;
                    for (int i = 0;i<list.length;i++){
                        if(!falg){
                            break;
                        }
                        JSONObject page = new JSONObject();
                        List<JSONObject> listSection = new ArrayList<>();
                        page.put("image_filename",bookName+"-"+(i+2)+".jpg");
                        page.put("page_number",i+1);

                        while ((lineStr = br.readLine()) != null && !StringUtils.isEmpty(lineStr)){
                            String page_number = "";
                            String sentence_sequence = "";
                            String text = "";
                            JSONObject section = new JSONObject();
                            lineNum++;
                            //eg:lineStr = "text_1_1$$At the Market"
                            //切割字符串
                            try {
                                page_number = lineStr.substring(lineStr.indexOf("_") + 1, lineStr.lastIndexOf("_"));//第几页
                                sentence_sequence = lineStr.substring(lineStr.lastIndexOf("_") + 1, lineStr.indexOf("$"));//第几句
                                text = lineStr.substring(lineStr.lastIndexOf("$")+1);
                            } catch (Exception e){
                                System.out.println(bookName+"数据异常,行数："+lineNum);
                                error.append(bookName+"数据异常,行数："+lineNum+"\n");
                                errorNum++;
                                falg = false;
                                break;
                            }
                            section.put("audio_filename_A",bookName+"-"+lineNum+".mp3");
                            section.put("section_sequence",1);
                            section.put("sentence_sequence",sentence_sequence);
                            section.put("text",text);
                            bookText.append(text+"\n");
                            listSection.add(section);
                            if(pageNum!=(Integer.parseInt(page_number))){
                                page.put("sections",listSection);
                                listPage.add(page);
                                pageNum = Integer.parseInt(page_number);
                                break;
                            }

                        }
                    }
                    if(falg){
                        object.put("book_text",bookText.toString());
                        object.put("pages",listPage);
                        writer = new FileWriter(imgPath+"\\"+bookName+"\\"+bookName+".json");
                        writer.write(object.toJSONString());
                        successNum++;
                        System.out.println(bookName+"成功------------------------");
                        object.clear();
                    } else {
                        object.clear();
                        continue;
                    }
                    writer.close();
                }
                System.out.println("---数据处理完成----\n成功:"+successNum+"条\n失败："+errorNum+"条\n"+error.toString());
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("------end----------");
    }
}
