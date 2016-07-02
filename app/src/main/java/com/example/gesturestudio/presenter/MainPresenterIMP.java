package com.example.gesturestudio.presenter;

import com.example.gesturestudio.R;
import com.example.gesturestudio.view.MainView;

/**
 * Created by Administrator on 2016/7/2.
 */
public class MainPresenterIMP implements MainPresenter {
    private MainView mainView;

    public MainPresenterIMP(MainView mainView) {
        this.mainView = mainView;
    }

    @Override
    public void switchTitle(int position) {
        switch (position){
            case R.id.main_item_gestureDetector:
                mainView.switchGestureDetector();
                break;
            case R.id.main_item_gestureLibrary:
                mainView.switchGestureLibrary();
                break;
            case R.id.main_item_ttl:
                mainView.switchGestureTTL();
                break;
            default:
                mainView.switchGestureDetector();
                break;
        }

    }
}
