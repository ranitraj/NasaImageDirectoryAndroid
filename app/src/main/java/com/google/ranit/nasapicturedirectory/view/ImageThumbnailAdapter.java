package com.google.ranit.nasapicturedirectory.view;

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
    private LayoutInflater inflater;
    private TreeMap<Integer, ImageUrl> imageUrlTreeMap;

    public ImageThumbnailAdapter(TreeMap<Integer, ImageUrl> imageUrlTreeMap) {
        this.imageUrlTreeMap = imageUrlTreeMap;
    }

    public static class ImageThumbnailViewHolder extends RecyclerView.ViewHolder {
        private ImageView thumbnailImageView;

        public ImageThumbnailViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnailImageView = itemView.findViewById(R.id.image_thumbnail);
        }

        public ImageView getThumbnailImageView() {
            return thumbnailImageView;
        }

        public void setThumbnailImageView(ImageView thumbnailImageView) {
            this.thumbnailImageView = thumbnailImageView;
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
    }

    @Override
    public int getItemCount() {
        int treeMapSize = imageUrlTreeMap.size();
        return Math.max(treeMapSize, 0);
    }
}
