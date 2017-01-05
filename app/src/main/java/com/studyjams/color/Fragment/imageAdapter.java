package com.studyjams.color.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.studyjams.color.App;

/**
 * Created by lazyeo on 8/8/16.
 */
public class imageAdapter extends FragmentStatePagerAdapter {

    App instance = App.getInstance();

    public imageAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public int getCount() {
        return instance.imageArray.size();
    }

    @Override
    public Fragment getItem(int position) {
        return imagePage.newInstance(position);
    }
}
