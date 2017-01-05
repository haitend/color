package com.studyjams.color;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by lazyeo on 8/2/16.
 */
public class ImageLib extends Activity{
    ImageView splashImg;
    TextView author,info;
    App instance = App.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.imagelib);

        initSplash();


    }

    private void initSplash() {

        splashImg = (ImageView) findViewById(R.id.splashImg);
        author = (TextView)findViewById(R.id.author);
        info = (TextView)findViewById(R.id.info);

        splashImg.setImageBitmap(instance.imageBitmap);
        info.setText(instance.imageInfo);
        author.setText(instance.imageAuthor);


    }

    public void saveImageToGallery(Context context, Bitmap bmp) {
        if (bmp == null) {
            Toast.makeText(ImageLib.this, "保存出错了！", Toast.LENGTH_LONG).show();
            return;
        }
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(), "WallPaper");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = instance.imageInfo + ".jpg";
        File file = new File(appDir, fileName);
        if (file.exists()){
            Toast.makeText(ImageLib.this, "已存过本图", Toast.LENGTH_LONG).show();
            return;
        }else{
            try {
                FileOutputStream fos = new FileOutputStream(file);
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                fos.flush();
                fos.close();
                Toast.makeText(ImageLib.this, "已保存至本地", Toast.LENGTH_LONG).show();
            } catch (FileNotFoundException e) {
                Toast.makeText(ImageLib.this, "文件未发现！", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            } catch (IOException e) {
                Toast.makeText(ImageLib.this, "保存出错了..！", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            } catch (Exception e) {
                Toast.makeText(ImageLib.this, "保存出错了！", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

//            插入图库并保存到Picture文件夹
//            try {
//                MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
            //通知图库更新
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + file.getAbsolutePath())));

        }

    }


    private void startActivity() {
        Intent intent = new Intent(ImageLib.this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
        finish();
    }

    protected void downloadButton(View button){
        saveImageToGallery(ImageLib.this,instance.imageBitmap);
    }

    protected void close(View image){
        startActivity();
    }

    public void onBackPressed() {
        startActivity();
    }


}
