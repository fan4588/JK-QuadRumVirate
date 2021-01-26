package com.nephew.jk.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * @author tanml
 * 将一串数据按照gzip方式压缩和解压缩
 */
public class GZipUtil {

    // 压缩
    public static void compress(File file) throws IOException {

        String property = System.getProperty("user.dir");
        String dir = property + "/compress/";
        String outFilePath = dir + file.getName().split("\\.")[0] + ".zip";
        File outFile = new File(outFilePath);
        if (!outFile.getParentFile().exists()) {  // 如果输出文件夹不存在
            outFile.getParentFile().mkdir();   // 创建文件夹
        }
        if (!outFile.exists()) {  // 判断输出文件是否存在
            outFile.createNewFile();   // 创建文件
        }
        InputStream input = new FileInputStream(file); // 定义文件的输入流
        ZipOutputStream zipOut = null; // 声明压缩流对象
        zipOut = new ZipOutputStream(new FileOutputStream(outFile));
        zipOut.putNextEntry(new ZipEntry(file.getName())); // 设置ZipEntry对象
        zipOut.setComment("");    // 设置注释
        int temp = 0;
        while ((temp = input.read()) != -1) { // 读取内容
            zipOut.write(temp);    // 压缩输出
        }
        input.close(); // 关闭输入流
        zipOut.close();    // 关闭输出流

        if(file!=null){
            file.delete();
        }

    }


    // 解压缩
    public static void uncompress(File file) throws IOException {

        String property = System.getProperty("user.dir");
        String dir = property + "/image/";

        ZipFile zipFile = new ZipFile(file);   // 实例化ZipFile对象
        ZipInputStream zipInput = null;    // 定义压缩输入流
        OutputStream out = null;   // 定义输出流，用于输出每一个实体内容
        InputStream input = null;  // 定义输入流，读取每一个ZipEntry
        ZipEntry entry = null; // 每一个压缩实体
        zipInput = new ZipInputStream(new FileInputStream(file));  // 实例化ZIpInputStream
        while ((entry = zipInput.getNextEntry()) != null) { // 得到一个压缩实体
            System.out.println("解压缩" + entry.getName() + "文件。");
            File outFile = new File(dir + entry.getName());   // 定义输出的文件路径
            if (!outFile.getParentFile().exists()) {  // 如果输出文件夹不存在
                outFile.getParentFile().mkdir();   // 创建文件夹
            }
            if (!outFile.exists()) {  // 判断输出文件是否存在
                outFile.createNewFile();   // 创建文件
            }
            input = zipFile.getInputStream(entry); // 得到每一个实体的输入流
            out = new FileOutputStream(outFile);   // 实例化文件输出流
            int temp = 0;
            while ((temp = input.read()) != -1) {
                out.write(temp);
            }
            input.close();     // 关闭输入流
            out.close();   // 关闭输出流
        }
        input.close();
    }

    //解压文件夹下的压缩文件
    public static void uncompress() throws IOException {
        String property = System.getProperty("user.dir");
        String dir = property + "/compress";
        File file = new File(dir);
        if (file.isDirectory()) {
            String[] fileList = file.list();
            for (int i = 0; i < fileList.length; i++) {
                File readFile = new File(dir + "/" + fileList[i]);
                if (!readFile.isDirectory()) {
                    System.out.println(readFile.getPath());
                    uncompress(readFile);
                }
            }
        }
    }

    //压缩文件到指定压缩包
    public static void compress() throws IOException {
        String property = System.getProperty("user.dir");
        String dir = property + "/compress";
        //先解压
        uncompress(new File(dir + "/compress.zip"));
        File file = new File(property + "/image");    // 定义要压缩的文件夹
        File zipFile = new File(dir + "/compress.zip");  // 定义压缩文件名称
        InputStream input = null;  // 定义文件输入流
        ZipOutputStream zipOut = null; // 声明压缩流对象
        zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
        zipOut.setComment("");   // 设置注释
        int temp = 0;
        if (file.isDirectory()) { // 判断是否是文件夹
            File lists[] = file.listFiles();   // 列出全部文件
            for (int i = 0; i < lists.length; i++) {
                input = new FileInputStream(lists[i]); // 定义文件的输入流
                zipOut.putNextEntry(new ZipEntry(file.getName()
                        + File.separator + lists[i].getName()));  // 设置ZipEntry对象
                while ((temp = input.read()) != -1) { // 读取内容
                    zipOut.write(temp);    // 压缩输出
                }
                input.close(); // 关闭输入流
            }
        }
        zipOut.close();    // 关闭输出流
    }


    public static void main(String[] args) {
        try {
            /*File scrFile = new File("F:\\zhangfan\\img\\1.jpg");
            File outFile = new File("F:\\zhangfan\\img\\1.zip");
            long startTime = System.currentTimeMillis();
            //compress(scrFile);
            //uncompress(outFile);
            uncompress();
            long endTime = System.currentTimeMillis();
            System.out.println((endTime - startTime) / 1000.00);
            System.out.println(outFile.getName().split("\\.")[1]);*/
            //File file = new File("F:\\zhangfan\\img\\11.jpg") ;
            //System.out.println(file.getPath());

            File file = new File("F:\\zhangfan\\img\\1.jpg");    // 定义要压缩的文件
            File zipFile = new File("F:\\zhangfan\\img\\1.zip"); // 定义压缩文件名称
            InputStream input = new FileInputStream(file); // 定义文件的输入流
            ZipOutputStream zipOut = null; // 声明压缩流对象
            zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
            zipOut.putNextEntry(new ZipEntry(file.getName())); // 设置ZipEntry对象
            System.out.println(file.getPath());
            zipOut.setComment("压缩流测试");    // 设置注释
            int temp = 0;
            while ((temp = input.read()) != -1) { // 读取内容
                zipOut.write(temp);    // 压缩输出
            }
            if (input != null) {
                input.close(); // 关闭输入流
            }
            if (input != null) {
                zipOut.close();    // 关闭输出流
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}