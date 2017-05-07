package com.example.jagadish.libraryapp;
import android.content.Context;
import android.content.res.AssetManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.PagerTitleStrip;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomPagerAdapter extends PagerAdapter {

    private Context mContext;
    public EditText t;
    private Button b1,b2,b3;
    public CustomPagerAdapter(Context context) {
        mContext = context;
    }
    @Override
    public Object instantiateItem(ViewGroup collection, final int position) {

        ModelObject modelObject = ModelObject.values()[position];
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(modelObject.getLayoutResId(), collection, false);

        collection.addView(layout);
        if(position==0)
        {
             Home h=new Home(layout);

        }
        if(position==1)
        {
            Profile p=new Profile(layout);
        }
        return layout;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return ModelObject.values().length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}