package com.eshore.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eshore.demo.common.FtpUtil;
import com.eshore.demo.common.ResultJson;
import com.eshore.demo.entity.User;
import com.eshore.demo.entity.UserFiles;
import com.eshore.demo.mapper.FilerMapper;
import com.eshore.demo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author eshore
 */
@Controller
@RequestMapping("/file")
public class UserFileController {

    @Autowired
    FilerMapper filerMapper;

    @Autowired
    FileService fileService;

    @GetMapping("/searchFiles")
    @ResponseBody
    public ResultJson searchFiles(HttpServletRequest request){
        ResultJson rs = new ResultJson();
        User loginUser = (User)request.getAttribute("loginUser");
//        request.getParameter("userId");
        loginUser = new User();
        loginUser.setId("1");
        QueryWrapper<UserFiles> wrapper = new QueryWrapper();
        wrapper.eq("user_id",loginUser.getId());
        List<UserFiles> userFiles = filerMapper.selectList(wrapper);
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(userFiles));
        rs.setResponseEntity(jsonArray);
        return rs;
    }

    /**
     * 上传文件有中文名称会乱码
     * @param request
     * @return
     */
    @PostMapping("/uploadFiles")
    @ResponseBody
    public ResultJson uploadFiles(HttpServletRequest request) {
        ResultJson rs = new ResultJson();
        String userId = request.getParameter("id");
        /**
         * 先上传，上传成功把文件信息存入数据库
         */
        MultipartHttpServletRequest mulReq = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = mulReq.getFileMap();
        /**
         * 如果有多个文件
         */
        fileMap.forEach((k, v) -> {
            try {
                boolean uploadSuccess = FtpUtil.uploadFile(v.getOriginalFilename(), v.getInputStream());
                if (uploadSuccess) {
                    /**
                     * 把文件信息存入数据库
                     */
                    UserFiles file = new UserFiles();
                    file.setFileSize(v.getSize() + "");
                    file.setCur_name(v.getOriginalFilename());
                    file.setOriginalName(v.getOriginalFilename());
                    file.setFileType(v.getContentType());
                    file.setCreateDate(new Date());
                    file.setFileId(System.currentTimeMillis() + "");
                    file.setUserId(userId);
                    int insert = filerMapper.insert(file);
                    if (insert > 0) {
                        rs.setCode(ResultJson.SUCCESS_CODE);
                        rs.setResponseEntity("" + v.getName() + "上传成功");
                    } else {
                        rs.setCode(ResultJson.ERROR_CODE);
                        rs.setResponseEntity("" + v.getName() + "上传失败");
                    }
                } else {
                    rs.setCode(ResultJson.ERROR_CODE);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return rs;
    }

    @PostMapping("/downloadFiles")
    @ResponseBody
    public ResultJson downloadFiles(HttpServletRequest request){
        String row = request.getParameter("row");
        JSONObject jsonObject = JSON.parseObject(row);
        String originalName = (String) jsonObject.get("originalName");
        ResultJson resultJson = fileService.downloadFiles(originalName);
        return resultJson;
    }

    @PostMapping("/deleteFiles")
    @ResponseBody
    public ResultJson deleteFiles(HttpServletRequest request){
        ResultJson resultJson = new ResultJson();
        String row = request.getParameter("row");
        JSONObject rowJson = JSONObject.parseObject(row);
        String fileId = (String)rowJson.get("fileId");
        int result = fileService.delFilesById(fileId);
        if (result > 0) {
            resultJson.setCode(ResultJson.SUCCESS_CODE);
        } else {
            resultJson.setCode(ResultJson.ERROR_CODE);
        }
        resultJson.setResponseEntity(result);
        return resultJson;
    }
}
