package com.eshore.demo.service;

import com.eshore.demo.common.ResultJson;

/**
 * @author eshore
 */
public interface MusicService {

    public ResultJson getMusicList();

    public ResultJson getMusicInfoById(String id);

    public ResultJson uploadMusic(Object o);

    public ResultJson addMusic(Object o);
}
