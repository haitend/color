package com.studyjams.color;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
//import android.widget.Button;

//import com.studyjams.color.camera.CameraRecordRenderer;
import com.studyjams.color.filter.FilterManager;
//import com.studyjams.color.video.EncoderConfig;
//import com.studyjams.color.video.TextureMovieEncoder;
import com.studyjams.color.widget.CameraSurfaceView;

import java.io.File;

/**
 * Created by lazyeo on 8/3/16.
 */
public class CameraView extends AppCompatActivity implements View.OnClickListener {
    private CameraSurfaceView mCameraSurfaceView;
//    private Button mRecordButton;
//    private boolean mIsRecordEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cameralayout);
        mCameraSurfaceView = (CameraSurfaceView) findViewById(R.id.camera);
        mCameraSurfaceView.setAspectRatio(9, 16);

        findViewById(R.id.filter_normal).setOnClickListener(this);
        findViewById(R.id.filter_tone_curve).setOnClickListener(this);
        findViewById(R.id.filter_soft_light).setOnClickListener(this);

//        mRecordButton = (Button) findViewById(R.id.record);
//        mRecordButton.setOnClickListener(this);

//        mIsRecordEnabled = TextureMovieEncoder.getInstance().isRecording();
//        updateRecordButton();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCameraSurfaceView.onResume();
//        updateRecordButton();
    }

    @Override
    protected void onPause() {
        mCameraSurfaceView.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mCameraSurfaceView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.filter_normal:
                mCameraSurfaceView.changeFilter(FilterManager.FilterType.Normal);
                break;
            case R.id.filter_tone_curve:
                mCameraSurfaceView.changeFilter(FilterManager.FilterType.ToneCurve);
                break;
            case R.id.filter_soft_light:
                mCameraSurfaceView.changeFilter(FilterManager.FilterType.ToneCurve);
                break;
        }
    }


    public void onBackPressed() {
        startActivity(MainActivity.class);
    }

    private void startActivity(Class targetClass) {
        Intent intent = new Intent(CameraView.this, targetClass);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
        finish();
    }

}
