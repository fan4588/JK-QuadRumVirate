package com.nephew.jk.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
@Api("文件断点")
public class UploadFileController {

    public static void main(String[] args) {
        // 源文件与目标文件
        File sourceFile = new File("E:\\test", "test.txt");
        // 输入输出流
        FileInputStream fis = null;
        // 数据缓冲区
        RandomAccessFile randomAccessfile = null;
        String path = "E:\\test\\test1.txt";
        File file = new File(path);
        try {
            fis = new FileInputStream(sourceFile);
            // 存在文件
            if (file.exists()) {
                randomAccessfile = new RandomAccessFile(file, "rw");
            } else {
                // 不存在文件，根据文件标识创建文件
                randomAccessfile = new RandomAccessFile(path, "rw");
            }
            System.out.println(file.length());
            randomAccessfile.seek(randomAccessfile.length());
            byte[] b = new byte[1];
            int n;
            while ((n = fis.read(b)) != -1) {
                if(file.length()==100){
                    System.out.println("中断");
                    throw new IOException();
                }
                randomAccessfile.write(b, 0, n);
            }

            Long currentFileLength = randomAccessfile.length();

            // 关闭文件
            closeRandomAccessFile(randomAccessfile);
            System.out.println(currentFileLength);
        } catch (FileNotFoundException e) {
            System.out.println("指定文件不存在");
        } catch (IOException e) {
            // TODO: handle exception
        } finally {
            try {
                // 关闭输入输出流
                if (fis != null)
                    fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 关闭随机访问文件
     *
     * @param
     */
    public static void closeRandomAccessFile(RandomAccessFile rfile) {
        if (null != rfile) {
            try {
                rfile.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void keepGoing(File source, File target, int position) {
        System.out.println("续传");
        try {
            RandomAccessFile readFile = new RandomAccessFile(source, "rw");
            RandomAccessFile writeFile = new RandomAccessFile(target, "rw");
            System.out.println(target.length());
            readFile.seek(target.length());
            writeFile.seek(target.length());

            // 数据缓冲区
            byte[] buf = new byte[1];
            // 数据读写
            while (readFile.read(buf) != -1) {
                writeFile.write(buf);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    static class FileAccessException extends Exception {

    }
}



