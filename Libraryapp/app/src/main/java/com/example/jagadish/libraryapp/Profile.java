package com.example.jagadish.libraryapp;

import android.app.PendingIntent;
import android.app.NotificationManager;
import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//import android.app.
import com.google.android.gms.common.api.PendingResult;
import com.google.firebase.auth.FirebaseAuth;

public class Profile
{
    static TextView t1,t2,t3,t4;
    static View view;
    static int f=0;
    static Button b;
    static String renewal[]=new String[4];
    public Profile(ViewGroup layout) {
        view=layout;
    }
    public static void set()
    {
        renewal[0] = Home.data[0];
        renewal[1] = Home.data[1];
        renewal[2] = Home.data[2];
        renewal[3] = Home.data[3];
        t1 = (TextView) view.findViewById(R.id.t1);
        t2 = (TextView) view.findViewById(R.id.t2);
        t3 = (TextView) view.findViewById(R.id.t3);
        t4 = (TextView) view.findViewById(R.id.t4);
        t1.setText(renewal[0]);
        t2.setText(renewal[1]);
        t3.setText(renewal[2]);
        if(f==0)
        {
            t4.setText("Renewal Books :None");
        }
        else
        {
            t4.setText("Renewal Books :\n  "+renewal[3]);
            b=(Button)view.findViewById(R.id.remind);
            b.setVisibility(View.VISIBLE);
            b.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(view.getContext());
                    builder.setContentTitle("Library App");
                    builder.setContentText(t4.getText());
                    builder.setSmallIcon(R.drawable.name);


                    Intent notificationIntent = new Intent(view.getContext(), Notifications.class);
                    PendingIntent contentIntent = PendingIntent.getActivity(view.getContext(), 0, notificationIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT);
                  //  AlarmManager am = (AlarmManager)view.getContext().getSystemService(Context.ALARM_SERVICE);
                    //am.cancel(contentIntent);
                    //am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+1000, contentIntent);
                    builder.setContentIntent(contentIntent);
                    NotificationManager manager = (NotificationManager)view.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
                    manager.notify(0, builder.build());
                }
            });
        }
    }

}
