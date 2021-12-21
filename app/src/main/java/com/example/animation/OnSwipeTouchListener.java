package com.example.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;

import com.example.animation.databinding.ActivityMainBinding;

public class OnSwipeTouchListener implements View.OnTouchListener {

    private final GestureDetector gestureDetector;
    View mainView;
    Context context;
    Animation animation;
    SpringAnimation springAnim;
    int startElevation, finalElevation;
    Boolean isEnabledUp = true, isEnabledDown = false;
    ActivityMainBinding binding;

    OnSwipeTouchListener(Context ctx, View mainView, int startElevation, int finalElevation, ActivityMainBinding binding) {
        gestureDetector = new GestureDetector(ctx, new GestureListener());
        this.mainView = mainView;
        mainView.setOnTouchListener(this);
        context = ctx;
        this.startElevation = startElevation;
        this.finalElevation = finalElevation;
        animation = AnimationUtils.loadAnimation(ctx,
                R.anim.fade_in);
        this.binding = binding;

    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
    public class GestureListener extends
            GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            onSwipeRight();
                        } else {
                            onSwipeLeft();
                        }
                        result = true;
                    }
                }
                else if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeBottom();
                    } else {
                        onSwipeTop();
                    }
                    result = true;
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }
    void onSwipeRight() {
        Toast.makeText(context, "Swiped Right", Toast.LENGTH_SHORT).show();
        this.onSwipe.swipeRight();
    }
    void onSwipeLeft() {
        Toast.makeText(context, "Swiped Left", Toast.LENGTH_SHORT).show();
        this.onSwipe.swipeLeft();
    }
    void onSwipeTop() {
        if(isEnabledUp){

            isEnabledUp = false;
            MainActivity.vImage = mainView;

//            if(finalElevation<3)
            if(startElevation>finalElevation)
                mainView.setElevation(startElevation-finalElevation);

            mainView.animate().setDuration(400).rotationXBy(-mainView.getRotationX()).translationYBy(-700).scaleX(1.3f).scaleY(1.15f).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mainView.setElevation(finalElevation);
                    springAnim = new SpringAnimation(mainView, SpringAnimation.TRANSLATION_Y);
                    SpringForce springForce = new SpringForce();
                    springForce.setFinalPosition(-600);
                    springForce.setStiffness(SpringForce.STIFFNESS_VERY_LOW);
                    springForce.setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
                    springAnim.setSpring(springForce);
                    springAnim.start();

                    binding.tBalance.animate().setDuration(100).translationXBy(100).alpha(1);
                    binding.tUsd.animate().setDuration(100).translationXBy(100).alpha(1);
                    binding.tBalanceTitle.animate().setDuration(100).translationXBy(100).alpha(1);
                    binding.tHolder.animate().setDuration(100).translationXBy(100).alpha(1);
                    binding.tCardHolder.animate().setDuration(100).translationXBy(100).alpha(1);
                    binding.tDate.animate().setDuration(100).translationXBy(-100).alpha(1);
                    binding.tExpires.animate().setDuration(100).translationXBy(-100).alpha(1);
                    binding.tCvc.animate().setDuration(100).translationXBy(-100).alpha(1);
                    binding.tCvcTitle.animate().setDuration(100).translationXBy(-100).alpha(1);

                    springAnim.addEndListener(new DynamicAnimation.OnAnimationEndListener() {
                        @Override
                        public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                            isEnabledUp = false;
                            isEnabledDown = true;
                        }
                    });

                    super.onAnimationEnd(animation);
                }
            });

            binding.tBalance.animate().setDuration(100).translationXBy(-100).alpha(0);
            binding.tUsd.animate().setDuration(100).translationXBy(-100).alpha(0);
            binding.tBalanceTitle.animate().setDuration(100).translationXBy(-100).alpha(0);
            binding.tHolder.animate().setDuration(100).translationXBy(-100).alpha(0);
            binding.tCardHolder.animate().setDuration(100).translationXBy(-100).alpha(0);
            binding.tDate.animate().setDuration(100).translationXBy(100).alpha(0);
            binding.tExpires.animate().setDuration(100).translationXBy(100).alpha(0);
            binding.tCvc.animate().setDuration(100).translationXBy(100).alpha(0);
            binding.tCvcTitle.animate().setDuration(100).translationXBy(100).alpha(0);

            this.onSwipe.swipeTop();
        }
    }
    void onSwipeBottom() {
        if(isEnabledDown){

            if(startElevation>=3)
                mainView.setElevation(startElevation);

            isEnabledDown = false;

            mainView.animate().setDuration(400).rotationXBy(40).translationYBy(600).scaleX(1f).scaleY(1f).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mainView.setElevation(startElevation);
                    isEnabledUp = true;

                    binding.tBalance.animate().setDuration(100).translationXBy(100).alpha(1);
                    binding.tUsd.animate().setDuration(100).translationXBy(100).alpha(1);
                    binding.tBalanceTitle.animate().setDuration(100).translationXBy(100).alpha(1);
                    binding.tHolder.animate().setDuration(100).translationXBy(100).alpha(1);
                    binding.tCardHolder.animate().setDuration(100).translationXBy(100).alpha(1);
                    binding.tDate.animate().setDuration(100).translationXBy(-100).alpha(1);
                    binding.tExpires.animate().setDuration(100).translationXBy(-100).alpha(1);
                    binding.tCvc.animate().setDuration(100).translationXBy(-100).alpha(1);
                    binding.tCvcTitle.animate().setDuration(100).translationXBy(-100).alpha(1);

                    super.onAnimationEnd(animation);
                }
            });

            binding.tBalance.animate().setDuration(100).translationXBy(-100).alpha(0);
            binding.tUsd.animate().setDuration(100).translationXBy(-100).alpha(0);
            binding.tBalanceTitle.animate().setDuration(100).translationXBy(-100).alpha(0);
            binding.tHolder.animate().setDuration(100).translationXBy(-100).alpha(0);
            binding.tCardHolder.animate().setDuration(100).translationXBy(-100).alpha(0);
            binding.tDate.animate().setDuration(100).translationXBy(100).alpha(0);
            binding.tExpires.animate().setDuration(100).translationXBy(100).alpha(0);
            binding.tCvc.animate().setDuration(100).translationXBy(100).alpha(0);
            binding.tCvcTitle.animate().setDuration(100).translationXBy(100).alpha(0);

            this.onSwipe.swipeBottom();
        }
    }
    interface onSwipeListener {
        void swipeRight();
        void swipeTop();
        void swipeBottom();
        void swipeLeft();
    }
    onSwipeListener onSwipe;
}