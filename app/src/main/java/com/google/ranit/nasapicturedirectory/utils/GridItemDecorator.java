package com.google.ranit.nasapicturedirectory.utils;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GridItemDecorator extends RecyclerView.ItemDecoration {
    private int spanCount;
    private int itemSpacing;

    public GridItemDecorator(int spanCount, int itemSpacing) {
        this.spanCount = spanCount;
        this.itemSpacing = itemSpacing;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildAdapterPosition(view);
        int column = position % spanCount;

        outRect.left = column * itemSpacing / spanCount;
        outRect.right = itemSpacing - (column + 1) * itemSpacing / spanCount;
        if (position >= spanCount) {
            outRect.top = itemSpacing;
        }
    }
}
