package com.eshore.demo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eshore.demo.common.FtpUtil;
import com.eshore.demo.common.ResultJson;
import com.eshore.demo.service.FileService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;

/**
 * @author eshore
 */
@Service
public class FileServiceImpl implements FileService {

    @Override
    public ResultJson downloadFiles(String fileName) {
        ResultJson rs = new ResultJson();
        OutputStream os = FtpUtil.downloadFileByFtp(fileName);
        if (os!=null){
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(os));
            rs.setResponseEntity(jsonObject);
            rs.setCode(ResultJson.SUCCESS_CODE);
        }else {
            rs.setCode(ResultJson.ERROR_CODE);
        }
        return rs;
    }
    /**
     * 登入后的文件目录:/var/ftp/test
     */


}
