package com.studyjams.color.Fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by lazyeo on 8/8/16.
 */
public class fragmentAdapter extends FragmentStatePagerAdapter {
    public fragmentAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public int getCount() {
        return 24;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return testImg.newInstance(position);
            case 1:
                return testImg.newInstance(position);
            case 2:
                return testImg.newInstance(position);
            case 3:
                return testImg.newInstance(position);
            case 4:
                return testImg.newInstance(position);
            case 5:
                return testImg.newInstance(position);
            case 6:
                return testImg.newInstance(position);
            case 7:
                return testImg.newInstance(position);
            case 8:
                return testImg.newInstance(position);
            case 9:
                return testImg.newInstance(position);
            case 10:
                return testImg.newInstance(position);
            case 11:
                return testImg.newInstance(position);
            case 12:
                return testImg.newInstance(position);
            case 13:
                return testImg.newInstance(position);
            case 14:
                return testImg.newInstance(position);
            case 15:
                return testImg.newInstance(position);
            case 16:
                return testImg.newInstance(position);
            case 17:
                return testImg.newInstance(position);
            case 18:
                return testImg.newInstance(position);
            case 19:
                return testImg.newInstance(position);
            case 20:
                return testImg.newInstance(position);
            case 21:
                return testImg.newInstance(position);
            case 22:
                return testImg.newInstance(position);
            case 23:
                return testImg.newInstance(position);
            default:
                return new Fragment();
        }
    }
}
