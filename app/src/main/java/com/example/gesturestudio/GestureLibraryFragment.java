package com.example.gesturestudio;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016/7/2.
 */
public class GestureLibraryFragment extends Fragment implements GestureOverlayView.OnGesturePerformedListener{
    private GestureOverlayView gestureOverlayView;
    private GestureLibrary gestureLibrary;
    private EditText editText;
    public GestureLibraryFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.gesturelibrary,container,false);
        gestureOverlayView = (GestureOverlayView) v.findViewById(R.id.gestureOverlay);
        gestureOverlayView.setGestureStrokeWidth(4);
        gestureOverlayView.setGestureColor(Color.BLUE);
        gestureOverlayView.addOnGesturePerformedListener(this);
        return v;
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, final Gesture gesture) {
       final View view = getLayoutInflater(null).inflate(R.layout.ongestureperformed, null);
        editText = (EditText)view.findViewById(R.id.edittext);
        ImageView imageView = (ImageView)view.findViewById(R.id.imageview);
        Bitmap bitmap = gesture.toBitmap(128,128,10,Color.BLUE);
        imageView.setImageBitmap(bitmap);
        new AlertDialog.Builder(getActivity()).setView(view).setPositiveButton("保存", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                gestureLibrary = GestureLibraries.fromFile("/mnt/sdcard/gesture");
                gestureLibrary.addGesture(editText.getText().toString(), gesture);
                gestureLibrary.save();
            }
        }).setNegativeButton("取消",null).show();

    }

}
