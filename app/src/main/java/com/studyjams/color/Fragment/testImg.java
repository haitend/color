package com.studyjams.color.Fragment;

//import android.content.Context;
//import android.net.Uri;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.studyjams.color.R;

public class testImg extends Fragment {


    private static int[] testAnswer = new int[] {
            R.string.answer01,R.string.answer02,R.string.answer03,R.string.answer04,
            R.string.answer05,R.string.answer06,R.string.answer07,R.string.answer08,
            R.string.answer09,R.string.answer10,R.string.answer11,R.string.answer12,
            R.string.answer13,R.string.answer14,R.string.answer15,R.string.answer16,
            R.string.answer17,R.string.answer18,R.string.answer19,R.string.answer20,
            R.string.answer21,R.string.answer22,R.string.answer23,R.string.answer24,
    };

    private static int[] testImg = new int[] {
            R.raw.p01, R.raw.p02, R.raw.p03, R.raw.p04, R.raw.p05,
            R.raw.p06, R.raw.p07, R.raw.p08, R.raw.p09, R.raw.p10,
            R.raw.p11,R.raw.p12,R.raw.p13,R.raw.p14,R.raw.p15,R.raw.p16,
            R.raw.p17,R.raw.p18,R.raw.p19,R.raw.p20,R.raw.p21,R.raw.p22,
            R.raw.p23,R.raw.p24,
    };

    int pageNum;

//    private OnFragmentInteractionListener mListener;

    public testImg() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment testImg.
     */
    // TODO: Rename and change types and number of parameters
    public static testImg newInstance(int num) {
        testImg fragment = new testImg();
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

        View view =  inflater.inflate(R.layout.fragment_test_img, container, false);
        ImageView img = (ImageView)view.findViewById(R.id.testImg);
        img.setImageResource(testImg[pageNum]);
        TextView text = (TextView)view.findViewById(R.id.answer);
        text.setText(testAnswer[pageNum]);
//        Log.e("测试啦测试啦", "onCreateView: "+pageNum+"以及"+testImg[pageNum] );
        return view ;
    }


//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
}
