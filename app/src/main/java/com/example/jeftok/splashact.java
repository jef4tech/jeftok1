package com.example.jeftok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Timer;
import java.util.TimerTask;

public class splashact extends AppCompatActivity {
    long delay=4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView mImageView = findViewById(R.id.image);
        Glide.with(splashact.this).load(R.drawable.sd).thumbnail(0.1f).into(mImageView);
        Timer t=new Timer();
        TimerTask tt=new TimerTask()
        {
            @Override
            public void run()
            {
                finish();
                Intent i=new Intent(splashact.this,Main.class);
                startActivity(i);
            }
        };
        t.schedule(tt,delay);
    }
}
