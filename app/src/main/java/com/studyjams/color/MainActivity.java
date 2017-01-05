package com.studyjams.color;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;


import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.common.collect.Lists;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.studyjams.color.Fragment.imageAdapter;
import com.studyjams.color.Fragment.imagePage;
import com.xgc1986.parallaxPagerTransformer.ParallaxPagerTransformer;


import cz.msebera.android.httpclient.Header;
import github.chenupt.multiplemodel.viewpager.ModelPagerAdapter;
import github.chenupt.multiplemodel.viewpager.PagerManager;
import github.chenupt.springindicator.SpringIndicator;
import github.chenupt.springindicator.viewpager.ScrollerViewPager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

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
    ScrollerViewPager mViewPager;
    App instance = App.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        backgroundImg = (ImageView) findViewById(R.id.background);

        //给配置赋值
        preferences = getSharedPreferences("SplashSettings", Activity.MODE_PRIVATE);

        mViewPager = (ScrollerViewPager)findViewById(R.id.ViewPager);

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        mAdapter =  new imageAdapter(fragmentManager);
//        mViewPager.setAdapter(mAdapter);
//

        ParallaxPagerTransformer x = new ParallaxPagerTransformer(R.id.background) ;
        x.setSpeed(1f);
        mViewPager.setPageTransformer(false, x);

        PagerManager manager = new PagerManager();
        manager.setTitles(getTitles());//设置指示器的文字
        //添加4个ViewPager页面
        manager.addFragment(imagePage.newInstance(0));
        manager.addFragment(imagePage.newInstance(1));
        manager.addFragment(imagePage.newInstance(2));
        manager.addFragment(imagePage.newInstance(3));
//        manager.addFragment(imagePage.newInstance(4));
//        manager.addFragment(imagePage.newInstance(5));
//        manager.addFragment(imagePage.newInstance(6));
//        manager.addFragment(imagePage.newInstance(7));

        ModelPagerAdapter adapter = new ModelPagerAdapter(getSupportFragmentManager(), manager);
        mViewPager.setAdapter(adapter);
        mViewPager.fixScrollSpeed();


        SpringIndicator springIndicator = (SpringIndicator) findViewById(R.id.indicator);
        springIndicator.setViewPager(mViewPager);



        //请求接口数据，并将数据更新至配置文件
        initSelf();

        //


    }

    private List<String> getTitles(){
        return Lists.newArrayList("红绿一型", "红绿二型2", "蓝黄色盲", "全色盲");
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
//
//        public void test(View button) {
//
//
//        if (button.getId() == R.id.test0) {
//            mViewPager.setCurrentItem(0);
//        }else if(button.getId() == R.id.test1) {
//            mViewPager.setCurrentItem(1);
//        }else if(button.getId() == R.id.test2) {
//            mViewPager.setCurrentItem(2);
//        }else if(button.getId() == R.id.test3) {
//            mViewPager.setCurrentItem(3);
//        }else if(button.getId() == R.id.test4) {
//            mViewPager.setCurrentItem(4);
//        }else if(button.getId() == R.id.test5) {
//            mViewPager.setCurrentItem(5);
//        }else if(button.getId() == R.id.test6) {
//            mViewPager.setCurrentItem(6);
//        }else if(button.getId() == R.id.test7) {
//            mViewPager.setCurrentItem(7);
//        }
//
//
//
//    }

//原处理图片的代码，现在不用咯
//    public void test(View button) {
//        File dir = getFilesDir();
//        final File imgFile = new File(dir, "splash.jpg");
//
//        if (button.getId() == R.id.test0) {
//            if (imgFile.exists()) {
//                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeFile(imgFile.getAbsolutePath()), 0)); //获取输入文件的路径转化为bitmap流
//
//            } else {
//                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeResource(getResources(), R.drawable.start), 0));
//            }
//
//        } else if (button.getId() == R.id.test1) {
//            if (imgFile.exists()) {
//                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeFile(imgFile.getAbsolutePath()), 1)); //获取输入文件的路径转化为bitmap流
//
//            } else {
//                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeResource(getResources(), R.drawable.start), 1));
//            }
//        } else if (button.getId() == R.id.test2) {
//            if (imgFile.exists()) {
//                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeFile(imgFile.getAbsolutePath()), 2)); //获取输入文件的路径转化为bitmap流
//
//            } else {
//                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeResource(getResources(), R.drawable.start), 2));
//            }
//        } else if (button.getId() == R.id.test3) {
//            if (imgFile.exists()) {
//                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeFile(imgFile.getAbsolutePath()), 3)); //获取输入文件的路径转化为bitmap流
//
//            } else {
//                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeResource(getResources(), R.drawable.start), 3));
//            }
//        } else if (button.getId() == R.id.test4) {
//            if (imgFile.exists()) {
//                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeFile(imgFile.getAbsolutePath()), 4)); //获取输入文件的路径转化为bitmap流
//
//            } else {
//                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeResource(getResources(), R.drawable.start), 4));
//            }
//        } else if (button.getId() == R.id.test5) {
//            if (imgFile.exists()) {
//                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeFile(imgFile.getAbsolutePath()), 5)); //获取输入文件的路径转化为bitmap流
//
//            } else {
//                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeResource(getResources(), R.drawable.start), 5));
//            }
//        } else if (button.getId() == R.id.test6) {
//            if (imgFile.exists()) {
//                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeFile(imgFile.getAbsolutePath()), 6)); //获取输入文件的路径转化为bitmap流
//
//            } else {
//                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeResource(getResources(), R.drawable.start), 6));
//            }
//        } else if (button.getId() == R.id.test7) {
//            if (imgFile.exists()) {
//                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeFile(imgFile.getAbsolutePath()), 7)); //获取输入文件的路径转化为bitmap流
//
//            } else {
//                backgroundImg.setImageBitmap(handleImage(BitmapFactory.decodeResource(getResources(), R.drawable.start), 7));
//            }
//        }
//    }

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
