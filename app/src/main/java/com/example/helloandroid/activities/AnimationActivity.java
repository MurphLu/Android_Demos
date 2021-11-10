package com.example.helloandroid.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.helloandroid.R;

public class AnimationActivity extends AppCompatActivity {

    private boolean animated = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        frameAnimation();
        transactionAnimation();
        propertiesAnimation();
    }

    private void frameAnimation() {
        RelativeLayout layout = findViewById(R.id.frame_animation);
        AnimationDrawable drawable = (AnimationDrawable)layout.getBackground();
        layout.setOnClickListener(view -> {
            animated = !animated;
            if (animated) {
                drawable.start();
            } else {
                drawable.stop();
            }
        });
    }

    private void transactionAnimation() {
        ImageView imageView = findViewById(R.id.transition_image);
        imageView.setOnClickListener(view -> {
            Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this,
                    R.anim.transaction_animation);
            imageView.startAnimation(animation);
        });
    }

    private void propertiesAnimation() {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(2000);
        animator.addUpdateListener(valueAnimator -> {
            float value = (float) valueAnimator.getAnimatedValue();
            Log.e("propertiesAnimator", "animatorUpdate:" + value);
        });
        animator.start();

        TextView textView = findViewById(R.id.text_view);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(textView, "alpha", 0f, 1f);
//        objectAnimator.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animator) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animator) {
//
//            }
//        });

        // 通过适配器可以只监听一种或几种状态
        objectAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation, boolean isReverse) {

            }
        });
        objectAnimator.setDuration(3000);
        objectAnimator.start();
    }
}