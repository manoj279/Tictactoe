package com.example.jagadish.smarthemet;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener{
    static ArrayList<String> arrayList;
    static int play=1;
    ActionBar actionBar;
    ActionBar.Tab t1,t2,t3;
    Toolbar toolbar;

    ViewPager viewPager;
    ListView l;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BluetoothAdapter ba=BluetoothAdapter.getDefaultAdapter();
        if(!ba.isEnabled())
        {
            Toast.makeText(this,"enabling bluetooth",Toast.LENGTH_LONG).show();
            Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBluetooth, 0);
        }
        else
        {
            Toast.makeText(this,"Bluetooth is enabled",Toast.LENGTH_LONG).show();
        }
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CustomPagerAdapter(this));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
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
        t1.setText("Connectivity");
        t1.setTabListener(this);
        t2 = actionBar.newTab();
        t2.setText("Music");
        t2.setTabListener(this);

        actionBar.addTab(t1);
        actionBar.addTab(t2);

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        switch (tab.getPosition()) {
            case 1:
                viewPager.setCurrentItem(1);
                tab.setText(tab.getText());

                int i=0;
                l=(ListView)findViewById(R.id.lst);
                arrayList=new ArrayList<String>();
                for(Field fields : R.raw.class.getFields())
                {
                    i++;
                    arrayList.add(fields.getName());
                }
                ArrayAdapter<String> a=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList.subList(1,i));
                l.setAdapter(a);
                l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        play=position+1;
                        Intent objIntent = new Intent(getApplicationContext(), PlayAudio.class);
                        getApplicationContext().startService(objIntent);

                    }
                });


                break;
            case 0:
                viewPager.setCurrentItem(0);
                break;
        }
    }
    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    public void playAudio(View view) {
        Intent objIntent = new Intent(this, PlayAudio.class);
        startService(objIntent);
    }

    public void stopAudio(View view) {
        Intent objIntent = new Intent(this, PlayAudio.class);
        stopService(objIntent);
    }


}


