package com.eshore.demo.ftpTest;

import com.eshore.demo.util.FtpUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

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
        boolean b = FtpUtil.uploadFile("82.157.174.148", 21, "fu", "fu", "/var/ftp/test", "/2021", "man.txt", inputStream);
        System.out.println(b);
    }

    @Test
    public void test1() throws FileNotFoundException {
        File file = new File("E:/google_dowmload");
        InputStream is = new FileInputStream(file);
    }
}
