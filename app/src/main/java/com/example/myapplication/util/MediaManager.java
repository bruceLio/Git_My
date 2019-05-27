package com.example.myapplication.util;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import com.example.myapplication.TestApplication;


/**
 * @author lixiaolong
 * @date 2019/2/25
 */
public class MediaManager {
    private static MediaManager sInstance;
    private MediaPlayer player;

    private MediaManager() {
        initPlayer();
    }

    private void initPlayer() {
        player = new MediaPlayer();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                releasePlayer();
            }
        });
    }

    private void releasePlayer() {
        if (player != null) {
            player.stop();
            player.reset();
            player.release();
            player = null;
        }

    }

    public static MediaManager getInstance() {
        if (sInstance == null) {
            sInstance = new MediaManager();
        }
        return sInstance;
    }

    public void play(String name) {
        try {
            if (player == null) {
                initPlayer();
            }
            AssetFileDescriptor data = TestApplication.app.getAssets().openFd(name);
            player.setDataSource(data.getFileDescriptor(), data.getStartOffset(), data.getLength());
            player.prepare();
            player.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
