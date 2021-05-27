package com.eshore.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.eshore.demo.common.ResultJson;
import com.eshore.demo.entity.UserMusic;
import com.eshore.demo.mapper.MusicMapper;
import com.eshore.demo.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author eshore
 */
@Service
public class MusicServiceImpl implements MusicService {

    @Autowired
    MusicMapper musicMapper;

    @Override
    public ResultJson getMusicList() {
        ResultJson resultJson = new ResultJson();
        List<UserMusic> userMusics = musicMapper.selectList(null);
        resultJson.setCode(ResultJson.SUCCESS_CODE);
        resultJson.setResponseEntity(userMusics);
        return resultJson;
    }

    @Override
    public ResultJson getMusicInfoById(String id) {
        ResultJson rs = new ResultJson();
        QueryWrapper<UserMusic> wrapper = new QueryWrapper<>();
        wrapper.eq("music_id",id);
        UserMusic userMusic = musicMapper.selectOne(wrapper);
        rs.setCode(ResultJson.SUCCESS_CODE);
        rs.setResponseEntity(userMusic);
        return rs;
    }

    @Override
    public ResultJson uploadMusic(Object o) {
        return null;
    }

    @Override
    public ResultJson addMusic(Object o) {
        return null;
    }
}
