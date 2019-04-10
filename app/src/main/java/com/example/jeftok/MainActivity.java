package com.example.jeftok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.DrawableImageViewTarget;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    long delay=8000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView mImageView = findViewById(R.id.image);
        Glide.with(MainActivity.this).load(R.drawable.sd).thumbnail(0.1f).into(mImageView);
        Timer t=new Timer();
        TimerTask tt=new TimerTask()
        {
            @Override
            public void run()
            {
                finish();
                Intent i=new Intent(MainActivity.this,verchk.class);
                startActivity(i);
            }
        };
        t.schedule(tt,delay);
    }
}
