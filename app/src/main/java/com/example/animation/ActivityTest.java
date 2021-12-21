package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.view.View;

import com.example.animation.databinding.ActivityTestBinding;

import java.util.ArrayList;
import java.util.List;

public class ActivityTest extends AppCompatActivity implements CustomAdapter.ItemClickListener{

    ActivityTestBinding binding;
    View view;
    ArrayList<Integer> list;
    CustomAdapter adapter;
    SpringAnimation springAnim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTestBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);

        prepareMe();


    }

    private void prepareMe() {
        list = new ArrayList<>();
        getData();
        adapter = new CustomAdapter(this, list);
        binding.recyclerview.setAdapter(adapter);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter.setClickListener(this);
    }

    private void getData() {
        list.add(R.drawable.card);
        list.add(R.drawable.card2);
        list.add(R.drawable.card3);
        list.add(R.drawable.card);
        list.add(R.drawable.card2);
        list.add(R.drawable.card3);
    }

    @Override
    public void onItemClick(View mainView, int position) {

        mainView.animate().setDuration(1000).rotationXBy(-40).scaleX(1.3f).scaleY(1.15f).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mainView.setElevation(1);
                springAnim = new SpringAnimation(mainView, SpringAnimation.TRANSLATION_Y);
                SpringForce springForce = new SpringForce();
                springForce.setFinalPosition(100);
                springForce.setStiffness(SpringForce.STIFFNESS_VERY_LOW);
                springForce.setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
                springAnim.setSpring(springForce);
                springAnim.start();

                springAnim.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                    @Override
                    public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {

                    }
                });

                super.onAnimationEnd(animation);
            }
        });
    }
}