package com.eshore.demo.entity;
//
//import cn.hutool.core.date.DateUtil;
//import com.baomidou.mybatisplus.annotation.TableField;
//import com.baomidou.mybatisplus.annotation.TableId;
//import com.baomidou.mybatisplus.annotation.TableName;
//
//
//import java.sql.Blob;
//import java.util.Date;
//
///**
// * @author eshore
// */
//@TableName("user_music")
//public class UserMusic {
//
//    @TableId("music_id")
//    private String music_id;
//
//    @TableField("music_file")
//    private byte[] music_file;
//
//    @TableField("create_date")
//    private Date create_date;
//
//    @TableField("music_name")
//    private String music_name;
//
//    @TableField("music_time")
//    private String music_time;
//
//    @TableField("music_singer")
//    private String music_singer;
//
//    public String getMusic_id() {
//        return music_id;
//    }
//
//    public void setMusic_id(String music_id) {
//        this.music_id = music_id;
//    }
//
//    public byte[] getMusic_file() {
//        return music_file;
//    }
//
//    public void setMusic_file(byte[] music_file) {
//        this.music_file = music_file;
//    }
//
//    public Date getCreate_date() {
//        return DateUtil.date();
//    }
//
//    public void setCreate_date(Date create_date) {
//        this.create_date = create_date;
//    }
//
//    public String getMusic_name() {
//        return music_name;
//    }
//
//    public void setMusic_name(String music_name) {
//        this.music_name = music_name;
//    }
//
//    public String getMusic_time() {
//        return music_time;
//    }
//
//    public void setMusic_time(String music_time) {
//        this.music_time = music_time;
//    }
//
//    public String getMusic_singer() {
//        return music_singer;
//    }
//
//    public void setMusic_singer(String music_singer) {
//        this.music_singer = music_singer;
//    }
//}


public class UserMusic {

    private String musicId;
    private String musicName;
    private String musicTime;
    private String musicSinger;
    private String musicFile;
    private java.sql.Timestamp createDate;
    private String url;


    public String getMusicId() {
        return musicId;
    }

    public void setMusicId(String musicId) {
        this.musicId = musicId;
    }


    public String getMusicName() {
        return musicName;
    }

    public void setMusicName(String musicName) {
        this.musicName = musicName;
    }


    public String getMusicTime() {
        return musicTime;
    }

    public void setMusicTime(String musicTime) {
        this.musicTime = musicTime;
    }


    public String getMusicSinger() {
        return musicSinger;
    }

    public void setMusicSinger(String musicSinger) {
        this.musicSinger = musicSinger;
    }


    public String getMusicFile() {
        return musicFile;
    }

    public void setMusicFile(String musicFile) {
        this.musicFile = musicFile;
    }


    public java.sql.Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(java.sql.Timestamp createDate) {
        this.createDate = createDate;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
