package com.example.animation;

import android.app.Activity;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.dynamicanimation.animation.SpringAnimation;
import androidx.dynamicanimation.animation.SpringForce;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private Activity context;
    private ArrayList<Integer> list;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    SpringAnimation springAnim;

    // data is passed into the constructor
    public CustomAdapter(Activity context, ArrayList<Integer> list) {
        this.mInflater = LayoutInflater.from(context);
        this.list = list;
        this.context = context;

    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_item_images, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//
//                // condition to pass (sentinel == 1)
//                springAnim = new SpringAnimation(holder.c_main, SpringAnimation.TRANSLATION_Y);
//                SpringForce springForce = new SpringForce();
//                springForce.setFinalPosition(-40);
//                springForce.setStiffness(SpringForce.STIFFNESS_VERY_LOW);
//                springForce.setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY);
//                springAnim.setSpring(springForce);
//                springAnim.start();
//
//
//
//            }
//        };
//        Handler handler = new Handler();
//        handler.postDelayed(runnable, position*200);

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return list.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ConstraintLayout c_main;

        ViewHolder(View itemView) {
            super(itemView);

            c_main = itemView.findViewById(R.id.c_main);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public Integer getItem(int id) {
        return list.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;

    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

