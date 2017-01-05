package com.studyjams.color.Fragment;

//import android.content.Context;
//import android.net.Uri;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.studyjams.color.App;
import com.studyjams.color.R;

import java.io.File;
import java.io.IOException;

public class imagePage extends Fragment {
    App instance = App.getInstance();
    int pageNum;

//    private OnFragmentInteractionListener mListener;

    public imagePage() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment testImg.
     */
    // TODO: Rename and change types and number of parameters
    public static imagePage newInstance(int num) {
        imagePage fragment = new imagePage();
        Bundle args = new Bundle();
        args.putInt("num", num);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pageNum = getArguments().getInt("num");
        }
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.image_page, container, false);
        ImageView img = (ImageView)view.findViewById(R.id.background);

//        try {
//            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), Uri.parse(instance.imageUri.get(pageNum)));
//            img.setImageBitmap(bitmap);
//        }catch (Exception e)
//        {//handle exception
//        }
//
//

        img.setImageBitmap(instance.imageArray.get(pageNum));


//        Log.e("测试啦测试啦", "onCreateView: "+pageNum+"以及"+testImg[pageNum] );
        return view ;
    }



}
