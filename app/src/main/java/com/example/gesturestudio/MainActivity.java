package com.example.gesturestudio;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.gesturestudio.presenter.MainPresenter;
import com.example.gesturestudio.presenter.MainPresenterIMP;
import com.example.gesturestudio.view.MainView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MainView{
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        actionBarDrawerToggle.syncState();
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        navigationView = (NavigationView)findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);
        mainPresenter = new MainPresenterIMP(this);
        switchGestureDetector();

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        item.setChecked(true);
        mainPresenter.switchTitle(item.getItemId());
        drawerLayout.closeDrawers();
        return false;
    }

    @Override
    public void switchGestureDetector() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, new GestureDetectorFragment()).commit();
        toolbar.setTitle(R.string.GestureDetector);

    }

    @Override
    public void switchGestureLibrary() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new GestureLibraryFragment()).commit();
        toolbar.setTitle(R.string.GestureLibrary);

    }

    @Override
    public void switchGestureTTL() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment,new TTL()).commit();
        toolbar.setTitle(R.string.TTS);

    }
}