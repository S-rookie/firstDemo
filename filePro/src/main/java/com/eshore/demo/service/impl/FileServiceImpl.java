package com.eshore.demo.service.impl;

import com.eshore.demo.common.FtpUtil;
import com.eshore.demo.common.ResultJson;
import com.eshore.demo.service.FileService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author eshore
 */
@Service
public class FileServiceImpl implements FileService {

    @Override
    public ResultJson downloadFiles(String fileName) {
        ResultJson rs = new ResultJson();
        boolean b = FtpUtil.downloadFileByFtp(fileName);
        if (b){
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
