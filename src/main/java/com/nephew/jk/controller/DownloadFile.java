package com.nephew.jk.controller;


import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@RestController
@Api("文件")
public class DownloadFile {
    private Logger logger = LoggerFactory.getLogger(DownloadFile.class);

    /**
     * 下载zip文件
     */
    @RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
    public String downloadZip(HttpServletResponse response, String fileName) throws UnsupportedEncodingException {

        String path = "E:\\25.zip";
        File file = new File(path);
        logger.info("压缩包文件路径:" + file.getPath());
        if (file.exists()) {
            logger.info("文件存在开始下载-------------------------------------------------");
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));
            response.setContentLength((int) file.length());
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream outputStream = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
                outputStream.flush();
                return "下载成功";
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        logger.error("文件不存在-------------------------------------------------");
        return "下载失败";
    }

}
