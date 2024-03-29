package com.eshore.demo.common;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.extra.ftp.Ftp;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.logging.LogFile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;

import java.io.*;
import java.net.SocketException;

/**
 * @author eshore
 */

public class FtpUtil {

    public static String host = "82.157.174.148";
    public static String port = "21";
    public static String username = "fu";
    public static String password = "fu";
    public static String basePath = "/var/ftp/test";
    public static String filePath = "/2021";


    /**
     * @param filename
     * @param input
     * @return
     */
    public static boolean uploadFile(String filename, InputStream input) {
        boolean result = false;
        FTPClient ftp = new FTPClient();
        try {
            ftp.setControlEncoding("UTF-8");
            int reply;
            ftp.connect(host, Integer.parseInt(port));// 连接FTP服务器
            // 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
            ftp.login(username, password);// 登录
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return result;
            }
            //切换到上传目录
            if (!ftp.changeWorkingDirectory(basePath + filePath)) {
                //如果目录不存在创建目录
                String[] dirs = filePath.split("/");
                String tempPath = basePath;
                for (String dir : dirs) {
                    if (null == dir || "".equals(dir)) {
                        continue;
                    }
                    tempPath += "/" + dir;
                    tempPath = new String(tempPath.getBytes("GBK"),"iso-8859-1");
                    if (!ftp.changeWorkingDirectory(tempPath)) {
                        if (!ftp.makeDirectory(tempPath)) {
                            return result;
                        } else {
                            ftp.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }
            //为了加大上传文件速度，将InputStream转成BufferInputStream
            BufferedInputStream in = new BufferedInputStream(input);
            //加大缓存区
            ftp.setBufferSize(1024 * 1024);
            //设置上传文件的类型为二进制类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //上传文件
//            if (!ftp.storeFile(new String(filename.getBytes("GBK"),"iso-8859-1"), in)) {
//                return result;
//            }
            if (!ftp.storeFile(filename, in)) {
                return result;
            }
            in.close();
            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }

    public static java.io.ByteArrayOutputStream downloadFileByFtp(String fileName) {
        Logger logger = LoggerFactory.getLogger(FtpUtil.class);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FTPClient ftp = new FTPClient();
        try {
            int reply;
            ftp.connect(host, Integer.parseInt(port));
            ftp.login(username, password);
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return null;
            }
            if (!ftp.changeWorkingDirectory(basePath+filePath)){
                LoggerFactory.getLogger(FtpUtil.class).info("连接失败，ftp目录不存在!");
                return null;
            }
            ftp.enterLocalPassiveMode();
            String[] fs = ftp.listNames();
//            String savePath = "E:/google_dowmload/"+fileName;
            for (String ff: fs
                 ) {
                if (ff.equals(fileName)){
                    ftp.retrieveFile(ff,baos);
                    byte[] bytes = baos.toByteArray();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ignored) {
                }
            }
        }
        return baos;
    }

}
