package com.example.gesturestudio;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.util.Locale;

/**
 * Created by Administrator on 2016/7/2.
 */
public class TTL extends Fragment implements View.OnClickListener {
    private EditText editText;
    private Button read;
    private Button record;
    private TextToSpeech textToSpeech;
    public TTL() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.ttl,container,false);
        editText = (EditText)v.findViewById(R.id.ttl_edit);
        read = (Button)v.findViewById(R.id.read);
        record = (Button)v.findViewById(R.id.record);
        textToSpeech = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                int result = textToSpeech.setLanguage(Locale.US);
                if(result != TextToSpeech.LANG_COUNTRY_AVAILABLE && result != TextToSpeech.LANG_AVAILABLE){
                    Toast.makeText(getActivity(),"不支持该语言",Toast.LENGTH_SHORT).show();
                }
            }
        });
        read.setOnClickListener(this);
        record.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.read:
                read();
                break;
            case R.id.record:
                record();
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void record() {
        textToSpeech.synthesizeToFile(editText.getText().toString(),null,new File("/mnt/sdcard/sound.wav"),"record");
        Toast.makeText(getActivity(),"声音记录成功",Toast.LENGTH_SHORT).show();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void read() {
        textToSpeech.speak(editText.getText().toString(),TextToSpeech.QUEUE_ADD,null,"read");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(textToSpeech != null){
            textToSpeech.shutdown();
        }
    }
}
