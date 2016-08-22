package com.studyjams.color;

import android.app.Application;

import com.studyjams.color.video.TextureMovieEncoder;


public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        TextureMovieEncoder.initialize(getApplicationContext());
    }
}
