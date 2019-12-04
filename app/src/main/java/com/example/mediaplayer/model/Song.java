package com.example.mediaplayer.model;

public class Song {
    private Long mSongId;
    private String mSongName;


    public Long getSongId() {
        return mSongId;
    }

    public String getName() {
        return mSongName;
    }

    public void setName(String mSongName) {
        this.mSongName = mSongName;
    }


    public Song(String mSongName,Long mSongId) {
        this.mSongName =mSongName ;
        this.mSongId=mSongId;

    }

    public Song(Long mSongId) {
        this.mSongId=mSongId;
    }

    public Song() {
    }
}
