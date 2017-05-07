package com.example.jagadish.libraryapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.CharBuffer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {//implements View.OnClickListener{
    public static FirebaseAuth firebaseAuth;
    EditText email,pass;
    Button log;
    static String emailid;
    public static final String url="https://library-app-14a0b.firebaseio.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth =FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null) {

            char[] ch=new char[12];
            try {
                //char[] ch=new char[12];
                InputStream inputStream = getApplicationContext().openFileInput("temp1.txt");

                if (inputStream != null) {
                    // InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    // BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String receiveString = "";
                    StringBuilder stringBuilder = new StringBuilder();
                    byte [] ch1=new byte[12];
                    //while ((receiveString = bufferedReader.readLine()) != null) {
                    inputStream.read(ch1);
                    //    bufferedReader.read(ch);
                    //}

                    inputStream.close();
                    String i= new String(ch1);//stringBuilder.toString();
                    emailid=i;
                    //emailid=ch.toString();
                    //  Toast.makeText(getApplicationContext(), emailid + "123"+ch, Toast.LENGTH_LONG).show();

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(getApplicationContext(),"already logged in"+emailid,Toast.LENGTH_LONG).show();
            finish();
            startActivity(new Intent(getApplicationContext(),page.class));
        }
        email=(EditText)findViewById(R.id.email);
        pass=(EditText)findViewById(R.id.Password);
        log=(Button)findViewById(R.id.submit);
        log.setOnClickListener(this);
    }
    @Override
    public void onClick(View v)
    {
        String em=email.getText().toString().trim();
        emailid=em;
        int l=emailid.length();
        emailid=emailid.substring(0,l-7);
        try {

      //      emailid="12";
            //char c[]=emailid.toCharArray();
           //OutputStreamWriter fileWriter = new OutputStreamWriter(getApplicationContext().openFileOutput("temp1.txt", Context.MODE_PRIVATE));
          //  FileWriter fileWriter=new FileWriter(String.valueOf(getApplicationContext().openFileOutput("temp.txt", Context.MODE_PRIVATE)));
            FileOutputStream fileWriter=getApplicationContext().openFileOutput("temp1.txt",Context.MODE_PRIVATE);
            PrintWriter p=new PrintWriter(fileWriter,false);
            p.flush();
            p.close();
            fileWriter.close();

            FileOutputStream fileWriter1=getApplicationContext().openFileOutput("temp1.txt",Context.MODE_PRIVATE);
            fileWriter1.write(emailid.getBytes());//.write(c);
            fileWriter1.close();
            //String s=new String(emailid.getBytes());
            //Toast.makeText(getApplicationContext(),s +"#"+emailid,Toast.LENGTH_LONG).show();
        }catch (Exception f){
            Toast.makeText(getApplicationContext(), emailid , Toast.LENGTH_LONG).show();
                f.printStackTrace();
            }
        String pwd=pass.getText().toString().trim();
        firebaseAuth.signInWithEmailAndPassword(em,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
            //if the task is successfull
            if(task.isSuccessful()){
                InputStream inputStream = null;
                try {
                    inputStream = getApplicationContext().openFileInput("temp1.txt");
                    if (inputStream != null) {
                        byte[] ch = new byte[12];
                        inputStream.read(ch);
                        inputStream.close();
                        String i = new String(ch);//stringBuilder.toString();
                        emailid = i;
                    }
                }catch(Exception e)
                    {

                    }
                //start the profile activity

                Toast.makeText(getApplicationContext(),"successfully logged in"+emailid,Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(getApplicationContext(),page.class));
            }
            else
            {   Toast.makeText(getApplicationContext(),"error",Toast.LENGTH_LONG).show();}
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
    });

    }
}