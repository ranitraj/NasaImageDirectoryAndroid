package com.google.ranit.nasapicturedirectory.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.ranit.nasapicturedirectory.R;

public class GlideHelper {

    public static void loadThumbnailImage(ImageView imageView, String imageUrl) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_broken_image);

        Glide.with(imageView.getContext())
                .load(imageUrl)
                .apply(options)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView);
    }

    public static void loadHighResolutionImage(ImageView imageView, String imageUrl) {
        RequestOptions options = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.drawable.ic_image)
                .error(R.drawable.ic_broken_image);

        Glide.with(imageView.getContext())
                .load(imageUrl)
                .apply(options)
                .into(imageView);
    }
}
