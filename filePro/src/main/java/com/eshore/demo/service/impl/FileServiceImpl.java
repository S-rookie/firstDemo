package com.eshore.demo.service.impl;

import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eshore.demo.common.FtpUtil;
import com.eshore.demo.common.ResultJson;
import com.eshore.demo.mapper.FilerMapper;
import com.eshore.demo.service.FileService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

/**
 * @author eshore
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    FilerMapper filerMapper;

    @Override
    public ResultJson downloadFiles(String fileName) {
        ResultJson rs = new ResultJson();
        ByteArrayOutputStream baos = FtpUtil.downloadFileByFtp(fileName);
        if (baos!=null){
            byte[] bytes = baos.toByteArray();
            String encode = Base64Encoder.encode(bytes);
            rs.setResponseEntity(encode);
            rs.setCode(ResultJson.SUCCESS_CODE);
        }else {
            rs.setCode(ResultJson.ERROR_CODE);
        }
        return rs;
    }

    @Override
    public int delFilesById(String file_id) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("file_id",file_id);
        return filerMapper.delete(wrapper);
    }
    /**
     * 登入后的文件目录:/var/ftp/test
     */


}
