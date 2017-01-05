package com.studyjams.color;

import android.app.Application;
import android.graphics.Bitmap;
import java.util.ArrayList;


import com.studyjams.color.video.TextureMovieEncoder;


public class App extends Application {
    public static App instance;
    public String imageAuthor ;
    public String imageInfo ;
    public Bitmap imageBitmap ;
    public ArrayList<Bitmap> imageArray = new ArrayList<Bitmap>();


    @Override
    public void onCreate() {
        super.onCreate();
        TextureMovieEncoder.initialize(getApplicationContext());
        instance = this;
    }

    public static App getInstance() {
        return instance;
    }




}
