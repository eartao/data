package com.data.transfor;

import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ToZip {

    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer();
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File com=fsv.getHomeDirectory();
        String desktop = com.getPath();
        String basePath = desktop+"\\data\\img";
        File file = new File(basePath);
        File[] files = file.listFiles();
        for (File fi:files) {
            String name = fi.getName();
            File[] files1 = fi.listFiles();
            for (File f:files1) {
                String fileName = f.getName();
                if(!fileName.contains("-1.jpg")){
                    sb.append(fi.getAbsolutePath()+"\\"+fileName+",");
                }
            }
            String zip = fi.getAbsolutePath()+"\\"+name;
            try {
                writeZip(sb,zip);
                sb.setLength(0);
                System.out.println(name+"--打包成功");
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private static void writeZip(StringBuffer sb,String zipname) throws IOException {
        String[] files = sb.toString().split(",");
        OutputStream os = new BufferedOutputStream( new FileOutputStream( zipname+".zip" ) );
        ZipOutputStream zos = new ZipOutputStream( os );
        byte[] buf = new byte[819200];
        int len;
        for (int i=0;i<files.length;i++) {
            File file = new File( files[i] );
            if ( !file.isFile() ) continue;
            ZipEntry ze = new ZipEntry( file.getName() );
            zos.putNextEntry( ze );
            BufferedInputStream bis = new BufferedInputStream( new FileInputStream( file ) );
            while ( ( len = bis.read( buf ) ) > 0 ) {
                zos.write( buf, 0, len );
            }
            zos.closeEntry();
            bis.close();
        }
//        zos.setEncoding("GBK");
        zos.closeEntry();
        zos.close();
        os.close();


        for(int i=0;i<files.length;i++){
            File file= new File(files[i] );
            file.delete();
        }
    }
}
