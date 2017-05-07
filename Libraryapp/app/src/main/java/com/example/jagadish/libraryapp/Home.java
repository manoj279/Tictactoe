package com.example.jagadish.libraryapp;


import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Properties;

public class Home implements View.OnClickListener {
    ArrayList<String>[] list=(ArrayList<String>[])new ArrayList[7];
    DatabaseReference database;
    FirebaseAuth firebase;
    View view;
    int cnt=0;
    ImageView im1,im2,im3,im4,im5,im6;
    EditText t;
    ListView lv;
    TextView tv;
    public static String data[]=new String[4];
    String str[]=new String[7];
    static AssetManager am;
    Home(ViewGroup view) {
        this.view = view;
        firebase = MainActivity.firebaseAuth;
        database = FirebaseDatabase.getInstance().getReference();
        lv=(ListView)view.findViewById(R.id.lv);
        int i;
        im1=(ImageView)view.findViewById(R.id.im1);
        im2=(ImageView)view.findViewById(R.id.im2);
        im3=(ImageView)view.findViewById(R.id.im3);
        im4=(ImageView)view.findViewById(R.id.im4);
        im5=(ImageView)view.findViewById(R.id.im5);
        im6=(ImageView)view.findViewById(R.id.im6);

        im1.setOnClickListener(this);
        im2.setOnClickListener(this);
        im3.setOnClickListener(this);
        im4.setOnClickListener(this);
        im6.setOnClickListener(this);
        im5.setOnClickListener(this);
        for(i=0;i<7;i++)
            str[i]="";
        for(i=0;i<7;i++)
        {
            list[i]=new ArrayList<String>();
        }
        data[3]="";
        database.addChildEventListener(new ChildEventListener() {
            // Retrieve new posts as they are added to Firebase
            @Override
            public void onChildAdded(DataSnapshot snapshot, String previousChildKey) {
                if (snapshot.getKey().equals("Books")) {
                    int c=0;
                    for (DataSnapshot ds : snapshot.getChildren()) {
                     //   try {
                       //     FileWriter f = new FileWriter(Environment.getExternalStorageDirectory()+"/cse.txt");
                            for(DataSnapshot ds1:ds.getChildren())
                            {
                                str[c]+=ds1.getKey().toString() +"   :"+ds1.getValue().toString()+"\n";
                      //          list[c].add(ds1.getKey()+":"+ds1.getValue());
                         //       f.write(ds1.getKey().toString()+"="+ds1.getValue().toString()+"\n");
                          //      list.add(ds1.getKey().toString());
//                                tv.getText()+ds1.getKey().toString()+"\n";
                            }
                        c++;
                           // f.close();
                       // } catch (Exception e) {
                         //   list.add("errorwrite" +ds.getKey().toString());
                        //}
                    }
                    //c++;
                }
                else if(snapshot.getKey().toString().equals(MainActivity.emailid))
                {
                    for(DataSnapshot ds:snapshot.getChildren())
                    {
                        if(ds.getKey().equals("Renewal Books"))
                        {
                            //Toast.makeText(..this, "", Toast.LENGTH_SHORT).show();
                            for(DataSnapshot ds1:ds.getChildren()) {
                                if (ds1.getKey().toString().equals("No of Books")) {
                                    Profile.f = Integer.parseInt(ds1.getValue().toString());
                                } else {
                                    data[3] += ds1.getKey() + ":" + ds1.getValue()+"\n   ";
                                }
                            }
                        }
                        else
                        {
                            data[cnt]=ds.getKey()+":"+ds.getValue();
                            cnt++;
                            //Toast.makeText(,data[cnt],Toast.LENGTH_LONG).show();
                        }
                    }
                    Profile.set();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
            //... ChildEventListener also defines onChildChanged, onChildRemoved,
            //    onChildMoved and onCanceled, covered in later sections.
        });
    }

    @Override
    public void onClick(View v) {
        int k=0;
        switch(v.getId())
        {
            case R.id.im1:k=2;break;
            case R.id.im2:k=0;break;
            case R.id.im3:k=3;break;
            case R.id.im4:k=4;break;
            case R.id.im5:k=1;break;
            case R.id.im6:k=5;break;
        }
/*
        int j=-1;//String str=database.child("Person").child("name").;
        try {
            FileReader f=new FileReader(Environment.getExternalStorageDirectory()+"/cse.txt");
            j=1;
            String bname="",val="";
            char c[]=new char[200];
            j=f.read(c);
            int i=0;
            while(i<j){
            while(c[i]!='=') {
                bname=bname+c[i];
                i++;
            }
            list.add(bname+"456"+j);
            while(c[i]!='\n') {
                val+=c[i];
                i++;
            }}
            list.add(bname + val);
        } catch (Exception e) {
            list.add("error"+j);
        }
        list.add("manoj");
  */
        //tv1=(TextView)view.findViewById(R.id.tv);
//        tv.setText(str);
        AlertDialog.Builder ad=new AlertDialog.Builder(view.getContext());
       // ad.setView(tv);
       // ad.setTitle("Books          "+"Available");
        try {
            //View view1 = View.inflate(view.getContext(), R.layout.display, null);

         //   tv=(TextView)view.findViewById(R.id.tv);
        //    tv.setText(str);
           // ad.setView(tv);
            if(str[k].isEmpty())
            {
                ad.setMessage("Loading");
            }
            else
                ad.setMessage(str[k]);
            AlertDialog ad1 = ad.create();
            ad1.show();
        //    ArrayAdapter a = new ArrayAdapter(view.getContext(), android.R.layout.simple_list_item_1, list[1]);
          //  lv.setAdapter(a);
        }catch(Exception e){
            Toast.makeText(view.getContext(),"Loading",Toast.LENGTH_LONG).show();
        }
    }
     public static void getast(AssetManager am1)
    {
        am=am1;
    }
}