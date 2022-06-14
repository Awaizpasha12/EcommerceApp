package com.example.ecommerce.Utils;

import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.ecommerce.R;

public class MyAnim {
    Activity activity;
    public MyAnim(Activity activity){
        this.activity = activity;
    }
    public void itemTap(View view){
        Animation animation = AnimationUtils.loadAnimation(activity,R.anim.tap);
        animation.setDuration(20);
        view.startAnimation(animation);
    }
}
