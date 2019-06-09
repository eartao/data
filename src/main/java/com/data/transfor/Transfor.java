package com.data.transfor;

import org.springframework.util.StringUtils;
import sun.misc.JarFilter;

import javax.swing.filechooser.FileSystemView;
import java.io.*;

public class Transfor {
    public static void main(String[] args) {
        FileSystemView fileSystemView = FileSystemView.getFileSystemView();
        File homeDirectory = fileSystemView.getHomeDirectory();
        String desktopPath = homeDirectory.getPath();//桌面路径
        String bookPath = desktopPath+"\\data\\bookTxt";

        File file = new File(bookPath);
        if(null == file || !file.exists()){
            System.out.println(file+"文件不存在");
            return;
        }
        File[] files = file.listFiles();
        for (File file1 : files) {
            String fileName = file1.getName();
            String bookName = fileName.substring(0,fileName.lastIndexOf("."));
            System.out.println(bookName);
            try {
                FileReader reader = new FileReader(file1);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String txt = "";
                while ((txt = bufferedReader.readLine())!=null && !txt.trim().equals("")){
                    System.out.println(txt);
                }
                reader.close();
                bufferedReader.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }

    }
}
