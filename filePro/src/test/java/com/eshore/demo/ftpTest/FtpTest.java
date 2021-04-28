package com.eshore.demo.ftpTest;

import com.eshore.demo.util.FtpUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 *
 */
public class FtpTest {

    /**
     *
     */
    @Test
    public void uploadFile() throws FileNotFoundException {
        String path = "C:/Users/eshore/Desktop/hello.txt";
        FileInputStream inputStream = new FileInputStream(new File(path));
        boolean b = FtpUtil.uploadFile("10.0.8.10", 21, "ftpuser", "ftpDeng2021", "/var/www/da", "/2021/6", "hello.txt", inputStream);
        System.out.println(b);
    }
}
