package com.example.jagadish.smarthemet;

import android.app.Service;
import android.content.Intent;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class PlayAudio extends Service{
    private static final String LOGCAT = null;
    public static MediaPlayer objPlayer;
    public void onCreate(){
        super.onCreate();
            objPlayer = MediaPlayer.create(this, R.raw.focus);
    }

    public int onStartCommand(Intent intent, int flags, int startId){
        onDestroy();
        String s=MainActivity.arrayList.get(MainActivity.play);
        objPlayer=MediaPlayer.create(this,Uri.parse("android.resource://" + this.getPackageName() + "/raw/" + s));
        objPlayer.start();
        if(objPlayer.isLooping() != true){
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public void onStop(){
        Toast.makeText(this,"Audio stopped",Toast.LENGTH_LONG).show();
        objPlayer.stop();
    }

    public void onPause(){
        objPlayer.stop();
        objPlayer.release();
    }

    public void onDestroy(){
        objPlayer.stop();
        objPlayer.release();
    }

    @Override
    public IBinder onBind(Intent objIndent) {
        return null;
    }
}
