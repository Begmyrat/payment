package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.animation.databinding.ActivityDetailBinding;

import java.util.ArrayList;

public class ActivityDetail extends AppCompatActivity {

    View view;
    ActivityDetailBinding binding;
    Animation animation;
    ArrayList<Integer> list;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.white));// set status background white
        // remove focus to edittext
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);

        SpringAnimation springAnim = new SpringAnimation(binding.image, SpringAnimation.TRANSLATION_Y);
        SpringForce springForce = new SpringForce();
        springForce.setFinalPosition(-100f);
        springForce.setStiffness(SpringForce.STIFFNESS_VERY_LOW);
        springForce.setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
        springAnim.setSpring(springForce);
//        springAnim.start();
        springAnim.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
            @Override
            public void onAnimationEnd(DynamicAnimation anim, boolean canceled, float value, float velocity) {

            }
        });

        list = new ArrayList<>();
        for(int i=0;i<10;i++)
            list.add(i);
        adapter = new CustomAdapter(this, list);
        binding.recyclerview.setAdapter(adapter);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


    }
}