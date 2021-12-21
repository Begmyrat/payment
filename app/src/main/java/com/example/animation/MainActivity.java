package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Pair;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.animation.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ActivityMainBinding binding;
    View view;
    OnSwipeTouchListener onSwipeTouchListener, onSwipeTouchListener2, onSwipeTouchListener3;
    ConstraintLayout parentLayout;
    ConstraintSet set;
    List<Integer> list;
    static View vImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.white));// set status background white
        // remove focus to edittext
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        parentLayout = (ConstraintLayout)binding.main;
        set = new ConstraintSet();
        list = new ArrayList<>();
        list.add(R.drawable.card);
        list.add(R.drawable.card2);
        list.add(R.drawable.card3);
        list.add(R.drawable.card);
        list.add(R.drawable.card2);
        list.add(R.drawable.card3);

        for(int i=0;i<6;i++){
            ImageView childView = new ImageView(this);
            // set view id, else getId() returns -1
            childView.setId(View.generateViewId());

            childView.setImageResource(list.get(i));
            childView.setTransitionName("bankCard");

            childView.setLayoutParams(new ConstraintLayout.LayoutParams((int) getDP(300), (int) getDP(150)));
            new OnSwipeTouchListener(this, childView, i, 5-i, binding);
            childView.setRotationX(40);
//            childView.setElevation(i);
            parentLayout.addView(childView, i);

            System.out.println("start: " + i + "    end: " + (5-i));

            set.clone(parentLayout);
            // connect start and end point of views, in this case top of child to top of parent.
            set.connect(childView.getId(), ConstraintSet.TOP, binding.tCardHolder.getId(), ConstraintSet.BOTTOM, 700);
            set.connect(childView.getId(), ConstraintSet.LEFT, parentLayout.getId(), ConstraintSet.LEFT);
            set.connect(childView.getId(), ConstraintSet.RIGHT, parentLayout.getId(), ConstraintSet.RIGHT);
            // ... similarly add other constraints
            set.applyTo(parentLayout);
        }

        binding.tDone.setOnClickListener(this);
//        binding.iBottom2.setOnClickListener(this);
//        binding.iBottom1.setOnClickListener(this);

//
//        onSwipeTouchListener = new OnSwipeTouchListener(this, binding.iBottom2, 2, 0);
//        onSwipeTouchListener2 = new OnSwipeTouchListener(this, binding.iBottom1, 1,1);
//        onSwipeTouchListener3 = new OnSwipeTouchListener(this, binding.iBottom0, 0,2);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.t_done:
                Intent intent = new Intent(getApplicationContext(), ActivityDetail.class);
                Pair[] pairs = new Pair[1];
                pairs[0] = new Pair<View, String>(vImage, "bankCard");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, pairs);
                startActivity(intent, options.toBundle());
                break;
        }
    }

    public float getDP(float dip){
        Resources r = getResources();
        float px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                r.getDisplayMetrics()
        );
        return px;
    }

}