package com.eshore.demo.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONObject;
import com.eshore.demo.common.ResultJson;
import com.eshore.demo.entity.UserFiles;
import com.eshore.demo.entity.UserMusic;
import com.eshore.demo.mapper.MusicMapper;
import com.eshore.demo.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * @author eshore
 */
@Controller
@RequestMapping("/music")
public class UserMusicController {

    @Autowired
    MusicService musicService;

    @Autowired
    MusicMapper musicMapper;

    @RequestMapping("/insertMusic")
    @ResponseBody
    public ResultJson insertMusic() throws SQLException {
        ResultJson rs = new ResultJson();
        File file = FileUtil.file("E:/mp3/thz.mp3");
        byte[] bytes = FileUtil.readBytes(file);
        UserMusic um = new UserMusic();
        um.setMusicId(String.valueOf(DateUtil.current()));
//        um.setMusicFile(bytes);
        int insert = musicMapper.insert(um);
        if (insert > 0){
            rs.setCode(ResultJson.SUCCESS_CODE);
        }else {
            rs.setCode(ResultJson.ERROR_CODE);
        }
        return rs;
    }

    @RequestMapping("/loadMusic")
    @ResponseBody
    public ResultJson loadTableData(){
        return musicService.getMusicList();
    }

    @RequestMapping("/getMusicInfo")
    @ResponseBody
    public ResultJson getMusicInfo(HttpServletRequest request){
        String row = request.getParameter("row");
        JSONObject music = JSONObject.parseObject(row);
        return musicService.getMusicInfoById((String) music.get("musicId"));
    }

    @RequestMapping("uploadMusic")
    @ResponseBody
    public ResultJson uploadMusic(HttpServletRequest request){
        return null;
    }

    @RequestMapping("/addMusic")
    @ResponseBody
    public ResultJson addMusic(HttpServletRequest request){
        return null;
    }
}
