package com.studyjams.color;



import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.studyjams.color.Fragment.fragmentAdapter;

public class ColorBlindTest extends AppCompatActivity {

    private ViewPager mViewPager;
    private fragmentAdapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_blind_test);
        mViewPager = (ViewPager)findViewById(R.id.ViewPager);
        //这里因为是3.0一下版本，所以需继承FragmentActivity，通过getSupportFragmentManager()获取FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        //3.0及其以上版本，只需继承Activity，通过getFragmentManager获取事物
        //初始化自定义适配器
        mAdapter =  new fragmentAdapter(fragmentManager);
        //绑定自定义适配器
        mViewPager.setAdapter(mAdapter);

        TextView tags = (TextView)findViewById(R.id.tags);
        tags.setText((mViewPager.getCurrentItem()+1)+"/24");

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                TextView tags = (TextView)findViewById(R.id.tags);
                tags.setText((mViewPager.getCurrentItem()+1)+"/24");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    public void onBackPressed() {
        startActivity(MainActivity.class);
    }

    private void startActivity(Class targetClass) {
        Intent intent = new Intent(ColorBlindTest.this, targetClass);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in,
                android.R.anim.fade_out);
        finish();
    }
}
