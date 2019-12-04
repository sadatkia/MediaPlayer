package com.example.mediaplayer.repository;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import androidx.annotation.RequiresApi;

import com.example.mediaplayer.model.Song;

import java.util.ArrayList;
import java.util.List;

public class SongRepository {
    private static SongRepository instance;
    List<Song> mSongs = new ArrayList<>();
    private String songName;
    Long songId;
    Uri SongUri;


    protected Context mContext;

    public static SongRepository getInstance(Context mContext) {
        if (instance == null)
            instance = new SongRepository(mContext);

        return instance;

    }

    public SongRepository(Context context) {
        mContext = context.getApplicationContext();
    }

    public Uri getSongUri(Long mSongId) {
        //  SongUri= MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        SongUri = ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, mSongId);
        return SongUri;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<Song> getSongs() {
        ContentResolver songResolver = mContext.getContentResolver();
        Cursor songCursor = songResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null);
        try {
            songCursor.moveToFirst();
            while (!songCursor.isAfterLast()) {
                songId = songCursor.getLong(songCursor.getColumnIndex(MediaStore.Audio.Media._ID));
                songName = songCursor.getString(songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE));

                Song song = new Song(songId);
                song.setName(songName);
                mSongs.add(song);
                songCursor.moveToNext();

            }
        } finally {
            songCursor.close();
        }

        return mSongs;
    }

    public void setSongs(List<Song> mSongs) {
        this.mSongs = mSongs;
    }


}
