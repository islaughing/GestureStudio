package com.example.gesturestudio;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

/**
 * Created by Administrator on 2016/7/2.
 */
public class GestureDetectorFragment extends Fragment implements GestureDetector.OnGestureListener, View.OnTouchListener {
    private ViewFlipper viewFlipper;
    private Animation[] animations;
    private GestureDetector gestureDetector;
    private final static int FILP_DISTANCE= 50;

    public GestureDetectorFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        animations = new Animation[4];
        animations[0] = AnimationUtils.loadAnimation(getActivity(),R.anim.left_in);
        animations[0] = AnimationUtils.loadAnimation(getActivity(),R.anim.left_out);
        animations[0] = AnimationUtils.loadAnimation(getActivity(),R.anim.right_in);
        animations[0] = AnimationUtils.loadAnimation(getActivity(),R.anim.right_out);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.gesturedetector,container,false);
        viewFlipper = (ViewFlipper)v.findViewById(R.id.viewflipper);
        viewFlipper.addView(addImageView(R.drawable.c));
        viewFlipper.addView(addImageView(R.drawable.l));
        viewFlipper.addView(addImageView(R.drawable.lyc));
        viewFlipper.addView(addImageView(R.drawable.lyca));
        viewFlipper.addView(addImageView(R.drawable.y));
        viewFlipper.addView(addImageView(R.drawable.me));
        gestureDetector = new GestureDetector(getActivity(),this);
        v.setOnTouchListener(this);
        return v;
    }

    private View addImageView(int c) {
        ImageView imageView = new ImageView(getActivity());
        imageView.setImageResource(c);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        return imageView;
    }


    @Override
    public boolean onDown(MotionEvent e) {
        Log.d("Ontouchdown", "gesturefliper");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if(e1.getX() - e2.getX() > FILP_DISTANCE){
            viewFlipper.setInAnimation(animations[2]);
            viewFlipper.setOutAnimation(animations[1]);
            viewFlipper.showPrevious();
            return true;
        }else if(e2.getX() - e1.getX() > FILP_DISTANCE){
            viewFlipper.setInAnimation(animations[0]);
            viewFlipper.setOutAnimation(animations[3]);
            viewFlipper.showNext();
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
}
