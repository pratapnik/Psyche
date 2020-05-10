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
            R.drawable.one, R.drawable.two, R.drawable.three
    };

    public String[] slide_headings = {
            "Be Positive", "Be Strong", "Be optimistic"
    };

    public String[] slide_descs = {
            "It is not primarily our physical selves that limit us but rather our mindset about our physical limits",
            "With the new day comes new strength and new thoughts",
            "The past cannot be changed. The future is yet in your power"
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

