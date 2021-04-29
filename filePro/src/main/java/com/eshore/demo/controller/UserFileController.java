package com.eshore.demo.controller;

import com.eshore.demo.common.FtpUtil;
import com.eshore.demo.common.ResultJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;

/**
 * @author eshore
 */
@Controller
@RequestMapping("/file")
public class UserFileController {

    @GetMapping("/loadFiles")
    public ResultJson loadFiles(HttpServletRequest request){
        ResultJson rs = new ResultJson();
        /**
         * 先上传，上传成功把文件信息存入数据库
         */
        boolean uploadSuccess = FtpUtil.uploadFile("host",000,"username","password","bathPath","filePath","filename",null);
        if (uploadSuccess){
            /**
             * 把文件信息存入数据库
             */
        }else {
            rs.setCode(ResultJson.ERROR_CODE);
        }
        return rs;
    }

    @PostMapping("/uploadFiles")
    public ResultJson uploadFiles(HttpServletRequest request){
//        request.geth
        System.out.println();
        return null;
    }

}
