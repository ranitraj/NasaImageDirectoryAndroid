package com.google.ranit.nasapicturedirectory.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.google.ranit.nasapicturedirectory.R;
import com.google.ranit.nasapicturedirectory.model.Image;
import com.google.ranit.nasapicturedirectory.model.ImageUrl;
import com.google.ranit.nasapicturedirectory.utils.GlideHelper;
import com.google.ranit.nasapicturedirectory.utils.GlobalUtilityClass;

import java.util.Collection;
import java.util.TreeMap;

public class FullscreenImageAdapter extends PagerAdapter {
    private TreeMap<Integer, ImageUrl> imageUrlTreeMap;
    private Collection<Image> sortedImageData;

    // Constructor
    FullscreenImageAdapter(TreeMap<Integer, ImageUrl> imageUrlTreeMap,
                           Collection<Image> sortedImageData) {
        this.imageUrlTreeMap = imageUrlTreeMap;
        this.sortedImageData = sortedImageData;
    }

    @Override
    public int getCount() {
        int size = sortedImageData.size();
        return Math.max(size, 0);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view ==  object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView enlargedImageView;

        View view = LayoutInflater.from(GlobalUtilityClass.context)
                .inflate(R.layout.viewpager_item_layout, container, false);

        enlargedImageView = view.findViewById(R.id.fullscreen_image);

        GlideHelper.loadHighResolutionImage(enlargedImageView,
                imageUrlTreeMap.get(position).getHdImageUrl());

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ConstraintLayout) object);

    }
}
