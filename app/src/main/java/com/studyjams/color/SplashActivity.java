package com.studyjams.color;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.File;


/**
 * Created by lazyeo on 7/30/16.
 */
public class SplashActivity extends Activity {
    private ImageView splashImg;
    private TextView author,info;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash);
        splashImg = (ImageView) findViewById(R.id.splashImg);
        preferences = getSharedPreferences("SplashSettings",Activity.MODE_PRIVATE);
        initSplash();


    }

    private void initSplash() {

        author = (TextView)findViewById(R.id.author);
        info = (TextView)findViewById(R.id.info);

        File dir = getFilesDir();
        final File imgFile = new File(dir, "splash.jpg");
        if (imgFile.exists()) {
            splashImg.setImageBitmap(BitmapFactory.decodeFile(imgFile.getAbsolutePath()));
            author.setText(preferences.getString("author","author"));
            info.setText(preferences.getString("info","info"));
        } else {
            splashImg.setImageResource(R.drawable.start);
            info.setText("见你所未见");
            author.setText("(◜◔。◔◝)");
            info.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            author.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//            info.setGravity(Gravity.CENTER);
//            author.setGravity(Gravity.CENTER);
        }

        final ScaleAnimation scaleAnim = new ScaleAnimation(1.3f, 1.0f, 1.3f, 1.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        scaleAnim.setFillAfter(true);
        scaleAnim.setDuration(3000);
        scaleAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity();
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        splashImg.startAnimation(scaleAnim);

    }


    private void startActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
        finish();
    }




}