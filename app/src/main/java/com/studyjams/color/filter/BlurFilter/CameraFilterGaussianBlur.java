package com.studyjams.color.filter.BlurFilter;

import android.content.Context;

import com.studyjams.color.filter.CameraFilter;
import com.studyjams.color.filter.FilterGroup;


public class CameraFilterGaussianBlur extends FilterGroup<CameraFilter> {

    public CameraFilterGaussianBlur(Context context, float blur) {
        super();
        addFilter(new CameraFilterGaussianSingleBlur(context, blur, false));
        addFilter(new CameraFilterGaussianSingleBlur(context, blur, true));
    }
}
