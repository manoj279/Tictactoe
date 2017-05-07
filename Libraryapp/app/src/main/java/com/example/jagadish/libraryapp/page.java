package com.example.jagadish.libraryapp;

import android.content.Intent;
import android.content.res.AssetManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerTitleStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TableLayout;

import com.example.jagadish.libraryapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class page extends AppCompatActivity implements ActionBar.TabListener {
    ActionBar actionBar;
    ActionBar.Tab t1,t2,t3;
    Toolbar toolbar;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user);
        AssetManager am=getAssets();
        Home.getast(am);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CustomPagerAdapter(this));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
     //           actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageSelected(int position) {
            actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        t1 = actionBar.newTab();
        t1.setText("Home");
        t1.setTabListener(this);
        t2 = actionBar.newTab();
        t2.setText("Profile");
        t2.setTabListener(this);
        t3 = actionBar.newTab();
        t3.setText("Logout");
        t3.setTabListener(this);

        actionBar.addTab(t1);
        actionBar.addTab(t2);
        actionBar.addTab(t3);

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
 //       viewPager.setCurrentItem(tab.getPosition());
       // String s = tab.getText().toString();
        // tab.setText(tab.getPosition()+s);
        switch (tab.getPosition()) {
            //   viewPager.set
            //tab.
            case 1:
                viewPager.setCurrentItem(1);
                tab.setText(tab.getText());
                break;
            case 0:
                viewPager.setCurrentItem(0);
                break;
            case 2:
                FirebaseAuth firebase;
                firebase = MainActivity.firebaseAuth;
                firebase.signOut();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
    }
    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
