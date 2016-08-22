package com.studyjams.color.filter.BlurFilter;

import android.content.Context;

import com.studyjams.color.filter.CameraFilter;
import com.studyjams.color.filter.FilterGroup;


public class ImageFilterGaussianBlur extends FilterGroup<CameraFilter> {

    public ImageFilterGaussianBlur(Context context, float blur) {
        super();
        addFilter(new ImageFilterGaussianSingleBlur(context, blur, false));
        addFilter(new ImageFilterGaussianSingleBlur(context, blur, true));
    }
}
