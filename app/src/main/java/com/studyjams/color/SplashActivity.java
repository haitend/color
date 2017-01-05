package com.studyjams.color;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;

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
    App instance = App.getInstance();

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
//            splashImg.setImageBitmap(BitmapFactory.decodeFile(imgFile.getAbsolutePath()));
//            author.setText(preferences.getString("author","author"));
//            info.setText(preferences.getString("info","info"));
            instance.imageAuthor =preferences.getString("author","author");
            instance.imageInfo = preferences.getString("info","info");
            instance.imageBitmap =BitmapFactory.decodeFile(imgFile.getAbsolutePath());

        } else {
            instance.imageAuthor = "见你所未见";
            instance.imageInfo = "(◜◔。◔◝)";
            instance.imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.start);
//            splashImg.setImageResource(R.drawable.start);
//            info.setText("见你所未见");
//            author.setText("(◜◔。◔◝)");
//            info.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//            author.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
//            info.setGravity(Gravity.CENTER);
//            author.setGravity(Gravity.CENTER);
        }

        splashImg.setImageBitmap(instance.imageBitmap);
        info.setText(instance.imageInfo);
        author.setText(instance.imageAuthor);


        instance.imageArray.add(handleImage((instance.imageBitmap),0));
//        instance.imageArray.add(handleImage((instance.imageBitmap),1));
        instance.imageArray.add(handleImage((instance.imageBitmap),2));
//        instance.imageArray.add(handleImage((instance.imageBitmap),3));
        instance.imageArray.add(handleImage((instance.imageBitmap),4));
//        instance.imageArray.add(handleImage((instance.imageBitmap),5));
        instance.imageArray.add(handleImage((instance.imageBitmap),6));
//        instance.imageArray.add(handleImage((instance.imageBitmap),7));





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

    public Bitmap handleImage(Bitmap bitmap, int color) {

        Bitmap updateBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());//定义与原图同尺寸的新图区域
        Canvas canvas = new Canvas(updateBitmap);//定义画布
        Paint paint = new Paint();//定义画笔
        final ColorMatrix colorMatrix = new ColorMatrix();//定义色相矩阵
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true); // 设置抗锯齿,也即是边缘做平滑处理

        final Matrix matrix = new Matrix();
        canvas.drawBitmap(bitmap, matrix, paint);

//        Bitmap bmp = Bitmap.createBitmap(img.getWidth(), img.getHeight(), Bitmap.Config.ARGB_8888);
//        // 创建一个相同尺寸的可变的位图区,用于绘制调色后的图片
//        Canvas canvas = new Canvas(bmp); // 得到画笔对象
//        Paint paint = new Paint(); // 新建paint
//        paint.setAntiAlias(true); // 设置抗锯齿,也即是边缘做平滑处理

        if (color == 0) {//红色盲
            colorMatrix.set(new float[]{(float) 0.567, (float) 0.433, 0, 0, 0,// 红色值
                    (float) 0.558, (float) 0.442, 0, 0, 0,// 绿色值
                    0, (float) 0.242, (float) 0.758, 0, 0,// 蓝色值
                    0, 0, 0, 1, 0 // 透明度
            });
        } else if (color == 1) {//红色弱
            colorMatrix.set(new float[]{(float) 0.817, (float) 0.183, 0, 0, 0,// 红色值
                    (float) 0.333, (float) 0.667, 0, 0, 0,// 绿色值
                    0, (float) 0.125, (float) 0.875, 0, 0, // 蓝色值
                    0, 0, 0, 1, 0 // 透明度
            });

        } else if (color == 2) {//绿色盲
            colorMatrix.set(new float[]{(float) 0.625, (float) 0.375, 0, 0, 0,// 红色值
                    (float) 0.7, (float) 0.3, 0, 0, 0,// 绿色值
                    0, (float) 0.3, (float) 0.7, 0, 0,// 蓝色值
                    0, 0, 0, 1, 0 // 透明度
            });

        } else if (color == 3) {//绿色弱
            colorMatrix.set(new float[]{(float) 0.8, (float) 0.2, 0, 0, 0,// 红色值
                    (float) 0.258, (float) 0.742, 0, 0, 0,// 绿色值
                    0, (float) 0.142, (float) 0.858, 0, 0,// 蓝色值
                    0, 0, 0, 1, 0 // 透明度
            });

        } else if (color == 4) {//蓝色盲
            colorMatrix.set(new float[]{(float) 0.95, (float) 0.05, 0, 0, 0,// 红色值
                    0, (float) 0.433, (float) 0.567, 0, 0,// 绿色值
                    0, (float) 0.475, (float) 0.525, 0, 0,// 蓝色值
                    0, 0, 0, 1, 0 // 透明度
            });

        } else if (color == 5) {//蓝色弱
            colorMatrix.set(new float[]{(float) 0.967, (float) 0.033, 0, 0, 0,// 红色值
                    0, (float) 0.733, (float) 0.267, 0, 0,// 绿色值
                    0, (float) 0.183, (float) 0.817, 0, 0,// 蓝色值
                    0, 0, 0, 1, 0 // 透明度
            });

        } else if (color == 6) {//全色盲
            colorMatrix.set(new float[]{(float) 0.299, (float) 0.587, (float) 0.114, 0, 0,// 红色值
                    (float) 0.299, (float) 0.587, (float) 0.114, 0, 0,// 绿色值
                    (float) 0.299, (float) 0.587, (float) 0.114, 0, 0,// 蓝色值
                    0, 0, 0, 1, 0 // 透明度
            });

        } else if (color == 7) {//全色弱
            colorMatrix.set(new float[]{(float) 0.618, (float) 0.320, (float) 0.062, 0, 0,// 红色值
                    (float) 0.163, (float) 0.775, (float) 0.062, 0, 0,// 绿色值
                    (float) 0.163, (float) 0.320, (float) 0.516, 0, 0,// 蓝色值
                    0, 0, 0, 1, 0 // 透明度
            });

        }


        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));// 设置颜色变换效果
        canvas.drawBitmap(bitmap, matrix, paint); // 将颜色变化后的图片输出到新创建的位图区
        // 返回新的位图，也即调色处理后的图片
        return updateBitmap;
    }


    private void startActivity() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
        finish();


    }




}