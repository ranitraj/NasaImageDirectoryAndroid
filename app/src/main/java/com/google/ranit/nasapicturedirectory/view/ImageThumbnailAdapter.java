package com.google.ranit.nasapicturedirectory.view;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.ranit.nasapicturedirectory.R;
import com.google.ranit.nasapicturedirectory.model.ImageUrl;
import com.google.ranit.nasapicturedirectory.utils.GlideHelper;
import com.google.ranit.nasapicturedirectory.utils.GlobalUtilityClass;

import java.util.TreeMap;

public class ImageThumbnailAdapter extends RecyclerView.Adapter<ImageThumbnailAdapter.ImageThumbnailViewHolder> {
    private Activity activity;
    private TreeMap<Integer, ImageUrl> imageUrlTreeMap;

    ImageThumbnailAdapter(Activity activity,
                          TreeMap<Integer, ImageUrl> imageUrlTreeMap) {
        this.activity = activity;
        this.imageUrlTreeMap = imageUrlTreeMap;
    }

     static class ImageThumbnailViewHolder extends RecyclerView.ViewHolder {
        private ImageView thumbnailImageView;


        private ImageThumbnailViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnailImageView = itemView.findViewById(R.id.image_thumbnail);
        }

        private ImageView getThumbnailImageView() {
            return thumbnailImageView;
        }
    }

    @NonNull
    @Override
    public ImageThumbnailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(GlobalUtilityClass.context)
                .inflate(R.layout.recycler_view_item_layout, parent, false);
        return new ImageThumbnailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageThumbnailViewHolder holder, int position) {
        GlideHelper.loadThumbnailImage(holder.getThumbnailImageView(),
                imageUrlTreeMap.get(position).getImageUrl());
        holder.getThumbnailImageView().setOnClickListener(new OnImageClickListener(position));
    }

    @Override
    public int getItemCount() {
        int treeMapSize = imageUrlTreeMap.size();
        return Math.max(treeMapSize, 0);
    }

    // Click Listener for View Holder
    class OnImageClickListener implements View.OnClickListener {
        int position;

        private OnImageClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            // Launch FullscreenImageActivity
            Intent intent = new Intent(activity, FullscreenImageActivity.class);
            intent.putExtra("position", position);
            activity.startActivity(intent);
        }
    }
}
