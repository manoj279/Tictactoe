package com.example.jagadish.tictactoe;

import android.app.Application;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ContentFrameLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    ImageButton i[];
    int n=0;
    int c[][];
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start();
    }
    void start()
    {
        n=0;
        i = new ImageButton[9];
        c = new int[3][3];
        for(int j=0;j<3;j++)
        {
            for(int k=0;k<3;k++)
            {
                c[j][k]=0;
            }
        }
        i[0]= (ImageButton) findViewById(R.id.imageButton0);
        i[0].setOnClickListener(image);
        i[0].setTag(R.id.imageButton0);
        i[1]= (ImageButton) findViewById(R.id.imageButton1);
        i[1].setTag(R.id.imageButton1);
        i[1].setOnClickListener(image);
        i[2]= (ImageButton) findViewById(R.id.imageButton2);
        i[3]= (ImageButton) findViewById(R.id.imageButton10);
        i[4]= (ImageButton) findViewById(R.id.imageButton11);
        i[5]= (ImageButton) findViewById(R.id.imageButton12);
        i[6]= (ImageButton) findViewById(R.id.imageButton20);
        i[7]= (ImageButton) findViewById(R.id.imageButton21);
        i[8]= (ImageButton) findViewById(R.id.imageButton22);
        i[2].setOnClickListener(image);
        i[3].setOnClickListener(image);
        i[4].setOnClickListener(image);
        i[5].setOnClickListener(image);
        i[6].setOnClickListener(image);
        i[7].setOnClickListener(image);
        i[8].setOnClickListener(image);

        i[2].setTag(R.id.imageButton2);
        i[3].setTag(R.id.imageButton10);
        i[4].setTag(R.id.imageButton11);
        i[5].setTag(R.id.imageButton12);
        i[6].setTag(R.id.imageButton20);
        i[7].setTag(R.id.imageButton21);
        i[8].setTag(R.id.imageButton22);
    }
    View.OnClickListener image=new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            if(R.drawable.x!=(int)v.getTag() && R.drawable.o!=(int)v.getTag()) {
                switch(v.getId())
                {
                    case R.id.imageButton0:if(n%2==0)
                                            {
                                                c[0][0]=1;
                                            }
                                            else
                                            {
                                                c[0][0]=2;
                                            }
                                            break;

                    case R.id.imageButton1:if(n%2==0)
                                            {
                                                c[0][1]=1;
                                            }
                                            else
                                            {
                                                c[0][1]=2;
                                            }
                                                break;

                    case R.id.imageButton2:if(n%2==0)
                    {
                        c[0][2]=1;
                    }
                    else
                    {
                        c[0][2]=2;
                    }
                        break;
                    case R.id.imageButton10:if(n%2==0)
                    {
                        c[1][0]=1;
                    }
                    else
                    {
                        c[1][0]=2;
                    }
                        break;
                    case R.id.imageButton11:if(n%2==0)
                    {
                        c[1][1]=1;
                    }
                    else
                    {
                        c[1][1]=2;
                    }
                        break;
                    case R.id.imageButton12:if(n%2==0)
                    {
                        c[1][2]=1;
                    }
                    else
                    {
                        c[1][2]=2;
                    }
                        break;
                    case R.id.imageButton20:if(n%2==0)
                    {
                        c[2][0]=1;
                    }
                    else
                    {
                        c[2][0]=2;
                    }
                        break;
                    case R.id.imageButton21:if(n%2==0)
                    {
                        c[2][1]=1;
                    }
                    else
                    {
                        c[2][1]=2;
                    }
                        break;
                    case R.id.imageButton22:if(n%2==0)
                    {
                        c[2][2]=1;
                    }
                    else
                    {
                        c[2][2]=2;
                    }
                        break;
                }
                if (n % 2 == 0) {
                    ImageButton j = (ImageButton) findViewById(v.getId());
                    j.setImageResource(R.drawable.x);
                    j.setTag(R.drawable.x);
                } else {
                    ImageButton j = (ImageButton) findViewById(v.getId());
                    j.setImageResource(R.drawable.o);
                }
                n++;
                if(n>-1)
                {
                    game();
                }
            }
        }};
    void game()
    {
        int x,o;
        int x1,o1,x2=0,o2=0,x3=0,o3=0;
        for(int j=0;j<3;j++) {
            x = o = x1 = o1 = 0;
            if (c[j][j] == 1) {
                x2++;
            } else if (c[j][j] == 2) {
                o2++;
            } if (c[2-j][j] == 1) {
                x3++;
            } else if (c[j][2 - j] == 2) {
                o3++;
            }
            for (int k = 0; k < 3; k++) {
                if (c[j][k] == 1) {
                    x++;
                }
                if (c[j][k] == 2) {
                    o++;
                }
                if (c[k][j] == 1) {
                    x1++;
                }
                if (c[k][j] == 2) {
                    o1++;
                }
            }
            if (x == 3 || x1 == 3 || x2 == 3 || x3 == 3) {
                Toast.makeText(getApplicationContext(), "player1 wins", Toast.LENGTH_SHORT).show();
                setContentView(R.layout.endgame);
                b = (Button) findViewById(R.id.button);
                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        setContentView(R.layout.activity_main);
                        start();
                    }
                });
            } else if (o == 3 || o1 == 3 || o2 == 3 || o3 == 3) {
                Toast.makeText(getApplicationContext(), "player2 wins", Toast.LENGTH_SHORT).show();
                setContentView(R.layout.endgame);
                b = (Button) findViewById(R.id.button);
                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        setContentView(R.layout.activity_main);
                        start();
                    }
                });
            } else if (n == 9) {
                Toast.makeText(getApplicationContext(), "draw", Toast.LENGTH_SHORT).show();
                setContentView(R.layout.endgame);
                b = (Button) findViewById(R.id.button);
                b.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        setContentView(R.layout.activity_main);
                        start();
                    }
                });
            }
        }
    }
    void im(View v) {
        if (n % 2 == 0) {
            ImageButton j = (ImageButton) findViewById(v.getId());
            j.setImageResource(R.drawable.x);
        } else {
            ImageButton j = (ImageButton) findViewById(v.getId());
            j.setImageResource(R.drawable.o);
        }
        n++;
    }
}


