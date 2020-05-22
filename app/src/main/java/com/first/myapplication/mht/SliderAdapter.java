package com.first.myapplication.mht;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    public int[] slide_images = {
            R.drawable.ic_vp_login, R.drawable.ic_vp_answer, R.drawable.ic_vp_analyze
    };

    public String[] slide_headings = {
            "LOGIN", "ANSWER", "ANALYZE"
    };

    public String[] slide_descs = {
            "Login using Google account or using Email",
            "Answer few questions to get your Results",
            "Analyze your Mental Health with the help of your results"
    };


    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.jarvis_view_pager_layout, container, false);

        ImageView slideImageView = view.findViewById(R.id.slideimage);
        TextView slideHeading = view.findViewById(R.id.head);
        TextView slideDescription = view.findViewById(R.id.about);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}

