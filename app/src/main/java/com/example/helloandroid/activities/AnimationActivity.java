package com.example.helloandroid.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.helloandroid.R;

public class AnimationActivity extends AppCompatActivity {

    private boolean animated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        frameAnimation();
        transactionAnimation();
    }

    private void frameAnimation() {
        RelativeLayout layout = findViewById(R.id.frame_animation);
        AnimationDrawable drawable = (AnimationDrawable)layout.getBackground();
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animated = !animated;
                if (animated) {
                    drawable.start();
                } else {
                    drawable.stop();
                }
            }
        });
    }

    private void transactionAnimation() {
        ImageView imageView = findViewById(R.id.transition_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this,
                        R.anim.transaction_animation);
                imageView.startAnimation(animation);
            }
        });
    }
}