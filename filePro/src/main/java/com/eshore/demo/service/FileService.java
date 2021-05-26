package com.eshore.demo.service;

import com.eshore.demo.common.ResultJson;

/**
 * @author eshore
 */
public interface FileService {
    public ResultJson downloadFiles(String fileName);

    public int delFilesById(String file_id);
}
