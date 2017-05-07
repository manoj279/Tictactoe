package com.example.jagadish.smarthemet;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

import static android.support.v4.app.ActivityCompat.startActivity;

public class connect
{
    TextView tv;
    TextView myLabel;
    EditText myTextbox;
    BluetoothAdapter mBluetoothAdapter;
    BluetoothSocket mmSocket;
    BluetoothDevice mmDevice;
    OutputStream mmOutputStream;
    static InputStream mmInputStream;
    Thread workerThread;
    byte[] readBuffer;
    boolean flag=true;
    int readBufferPosition;
    int counter;
    FileOutputStream fileOutputStream;
    volatile boolean stopWorker;
    View v;
    static String flag1="";
    public connect(View c)
    {
        v=c;
        tv=(TextView)v.findViewById(R.id.tv);
        Button openButton = (Button)v.findViewById(R.id.open);
        Button closeButton = (Button)v.findViewById(R.id.close);

        //Open Button
        openButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                try
                {
                    findBT();
                    openBT();
                    Toast.makeText(v.getContext(),"Device Connected",Toast.LENGTH_LONG).show();
                    sendData();

                }
                catch (IOException ex) { }
            }
        });

        //Close button
        closeButton.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                try
                {
                    closeBT();
                }
                catch (IOException ex) { }
            }
        });
    }

    void findBT()
    {
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if(mBluetoothAdapter == null)
        {
        }
       Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if(pairedDevices.size() > 0)
        {
            for(BluetoothDevice device : pairedDevices)
            {
                if(device.getName().equals("HC-05"))
                {
                    mmDevice = device;
                    break;
                }
            }
        }
    }

    void openBT() throws IOException
    {
        UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"); //Standard SerialPortService ID
        mmSocket = mmDevice.createRfcommSocketToServiceRecord(uuid);
        mmSocket.connect();
        mmInputStream = mmSocket.getInputStream();
        beginListenForData();
    }

    void beginListenForData()
    {
        final Handler handler = new Handler();
        final byte delimiter = 10; //This is the ASCII code for a newline character

        stopWorker = false;
        readBufferPosition = 0;
        readBuffer = new byte[1024];
        workerThread = new Thread(new Runnable()
        {
            public void run()
            {
                while(!Thread.currentThread().isInterrupted() && !stopWorker)
                {
                    try
                    {
                        int bytesAvailable = mmInputStream.available();
                        if(bytesAvailable > 0)
                        {
                            byte[] packetBytes = new byte[bytesAvailable];
                            mmInputStream.read(packetBytes);
                            for(int i=0;i<bytesAvailable;i++)
                            {
                                byte b = packetBytes[i];
                                if(b == delimiter)
                                {
                                    byte[] encodedBytes = new byte[readBufferPosition];
                                    System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                                    final String data = new String(encodedBytes, "US-ASCII");
                                    readBufferPosition = 0;

                                    handler.post(new Runnable()
                                    {
                                        public void run()
                                        {
                                        }
                                    });
                                }
                                else
                                {
                                    readBuffer[readBufferPosition++] = b;
                                }
                            }
                        }
                    }
                    catch (IOException ex)
                    {
                        stopWorker = true;
                    }
                }
            }
        });

        workerThread.start();
    }

    void sendData() {
        new A(v.getContext()).execute();
    }


    void closeBT() throws IOException
    {
        stopWorker = true;
        mmInputStream.close();
        mmSocket.close();
    }
}
class A extends AsyncTask<Void, String, Void>
{
    String flag;
    String t;
    Context c1;
    A(Context c)
    {
        this.c1=c;
    }
    @Override
    protected Void doInBackground(Void... params) {
        t="0";
        int i=0;
        while(true) {
            byte[] buffer = new byte[1];
            try {
                int c = connect.mmInputStream.read(buffer);//.read();
                flag = new String(buffer);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (flag.charAt(0) == '1')
            {       t = "1";
                Intent objIntent = new Intent(c1, PlayAudio.class);
                c1.startService(objIntent);

            }
        if (flag.charAt(0)=='2')
        {
            Intent objIntent = new Intent(c1, PlayAudio.class);
            c1.stopService(objIntent);
            MainActivity.play=0;
            }
        if(flag.charAt(0)=='3' )
        {       t="3";
                String phoneNo = "950214897";
                String sms = "Emergency";

                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNo, null, sms, null, null);
                } catch (Exception e1) {
                        e1.printStackTrace();
                }
            }
            SystemClock.sleep(3000);
        }
  }
}