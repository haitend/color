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
import android.support.v7.app.AppCompatActivity;


import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;


import cz.msebera.android.httpclient.Header;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * Created by lazyeo on 7/30/16.
 */
public class MainActivity extends AppCompatActivity {
    ImageView backgroundImg;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        backgroundImg = (ImageView) findViewById(R.id.background);

        //给配置赋值
        preferences = getSharedPreferences("SplashSettings", Activity.MODE_PRIVATE);

        File dir = getFilesDir();
        final File imgFile = new File(dir, "splash.jpg");

        //判断是否下载过图片，如果下载过则显示下载的背景图，没有则显示默认图
        if (imgFile.exists()) {
            backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeFile(imgFile.getAbsolutePath()), 0)); //获取输入文件的路径转化为bitmap流

        } else {
            backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeResource(getResources(), R.drawable.start), 0));
        }

        //请求接口数据，并将数据更新至配置文件
        initSelf();

        //


    }

    //输入图片，处理成不同色相的新图片
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

    //初始化接口请求
    public void initSelf() {
        if (HttpUtils.isNetworkConnected(MainActivity.this)) {
            HttpUtils.get("http://www.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1&mkt=en-US", new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, byte[] bytes) {
                    try {
                        JSONObject jsonObject = new JSONObject(new String(bytes));
                        JSONArray images = jsonObject.getJSONArray("images");
                        JSONObject array = images.getJSONObject(0);
                        String imgUrl = "http://www.bing.com" + array.getString("urlbase") + "_1920x1080.jpg"; //鉴于bing的接口喜欢变，干脆纯拼算了
                        String copyright = array.getString("copyright");
                        String tempString1 = copyright.replace("(", "");
                        String tempString2 = tempString1.replace(")", "");
                        String[] temp = tempString2.split("©");

                        //判断url与原本存储在配置文件中的是否一致，不一致则更新数据并下载新的图片
                        if (imgUrl.equals(preferences.getString("url", "default"))) {
                        } else {
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("info", temp[0]);
                            editor.putString("author", "By " + "©" + temp[1]);
                            editor.putString("url", imgUrl);
                            editor.commit();
                            downSplashImg(imgUrl);
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                    Toast.makeText(MainActivity.this, "请求异常!", Toast.LENGTH_LONG).show();
//                            Log.d("gg", "onFailure: 啥也不说了");
                }
            });

        } else {
            Toast.makeText(MainActivity.this, "没有网络连接!", Toast.LENGTH_LONG).show();
//                    Log.e("注意了注意了！", "onAnimationEnd:失败失败！");
        }

    }


    //下载splash图片，固定方法，以后可以将下载方法抽离
    public void downSplashImg(String imgUrl) {
        File dir = getFilesDir();
        final File imgFile = new File(dir, "splash.jpg");
        HttpUtils.get(imgUrl, new BinaryHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] binaryData) {
                saveImage(imgFile, binaryData);
//                                        Log.e("来吧", "onSuccess: 为啥不来！");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] binaryData, Throwable error) {
//                                        Log.e("失败？", "onFailure: 呵呵哒！");
            }
        });
    }


    //输入文件路径&文件名的file参数和bytes的文件流，保存文件/图片
    public void saveImage(File file, byte[] bytes) {
        try {
            if (file.exists()) {
                file.delete();
//                Log.e("呵呵", "删除文件一枚 ");
            }
            OutputStream fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.flush();
            fos.close();
            Toast.makeText(MainActivity.this, "缓存完成！", Toast.LENGTH_LONG).show();
//            Log.e("OK", "saveImage: 保存成功");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void test(View button) {
        File dir = getFilesDir();
        final File imgFile = new File(dir, "splash.jpg");

        if (button.getId() == R.id.test0) {
            if (imgFile.exists()) {
                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeFile(imgFile.getAbsolutePath()), 0)); //获取输入文件的路径转化为bitmap流

            } else {
                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeResource(getResources(), R.drawable.start), 0));
            }

        } else if (button.getId() == R.id.test1) {
            if (imgFile.exists()) {
                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeFile(imgFile.getAbsolutePath()), 1)); //获取输入文件的路径转化为bitmap流

            } else {
                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeResource(getResources(), R.drawable.start), 1));
            }
        } else if (button.getId() == R.id.test2) {
            if (imgFile.exists()) {
                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeFile(imgFile.getAbsolutePath()), 2)); //获取输入文件的路径转化为bitmap流

            } else {
                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeResource(getResources(), R.drawable.start), 2));
            }
        } else if (button.getId() == R.id.test3) {
            if (imgFile.exists()) {
                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeFile(imgFile.getAbsolutePath()), 3)); //获取输入文件的路径转化为bitmap流

            } else {
                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeResource(getResources(), R.drawable.start), 3));
            }
        } else if (button.getId() == R.id.test4) {
            if (imgFile.exists()) {
                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeFile(imgFile.getAbsolutePath()), 4)); //获取输入文件的路径转化为bitmap流

            } else {
                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeResource(getResources(), R.drawable.start), 4));
            }
        } else if (button.getId() == R.id.test5) {
            if (imgFile.exists()) {
                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeFile(imgFile.getAbsolutePath()), 5)); //获取输入文件的路径转化为bitmap流

            } else {
                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeResource(getResources(), R.drawable.start), 5));
            }
        } else if (button.getId() == R.id.test6) {
            if (imgFile.exists()) {
                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeFile(imgFile.getAbsolutePath()), 6)); //获取输入文件的路径转化为bitmap流

            } else {
                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeResource(getResources(), R.drawable.start), 6));
            }
        } else if (button.getId() == R.id.test7) {
            if (imgFile.exists()) {
                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeFile(imgFile.getAbsolutePath()), 7)); //获取输入文件的路径转化为bitmap流

            } else {
                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeResource(getResources(), R.drawable.start), 7));
            }
        }
    }

    public void go(View button) {
        if (button.getId() == R.id.imageInfo) {
            startActivity(ImageLib.class);
        } else if (button.getId() == R.id.cameraPreview) {
            startActivity(CameraView.class);
        } else if (button.getId() == R.id.colorTest) {
            startActivity(ColorBlindTest.class);
        }

    }


    private void startActivity(Class targetClass) {
        Intent intent = new Intent(MainActivity.this, targetClass);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
        finish();
    }


}
