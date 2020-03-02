package com.google.ranit.nasapicturedirectory.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.google.ranit.nasapicturedirectory.R;
import com.google.ranit.nasapicturedirectory.data.DataManager;
import com.google.ranit.nasapicturedirectory.model.Image;
import com.google.ranit.nasapicturedirectory.model.ImageUrl;
import com.google.ranit.nasapicturedirectory.utils.Constants;

import java.util.Collection;
import java.util.TreeMap;

public class FullscreenImageActivity extends AppCompatActivity {
    private static final String TAG = "FullscreenImageActivity";

    private Collection<Image> imageCollection;
    private TreeMap<Integer, ImageUrl> imageUrlTreeMap;

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_image);
        viewPager = findViewById(R.id.view_pager);

        DataManager managerInstance = DataManager.getInstance();
        imageCollection = managerInstance.getSortedList();
        imageUrlTreeMap = managerInstance.getImageUrlMap();

        int itemPosition = receiveIntent();
        prepareViewPager(itemPosition);
    }

    // Receive Intent
    private int receiveIntent() {
        Intent intent = getIntent();
        return intent.getIntExtra("position", Constants.DEFAULT_INTENT_VALUE);
    }

    // Prepare ViewPager
    private void prepareViewPager(int position) {
        FullscreenImageAdapter adapter = new FullscreenImageAdapter(imageUrlTreeMap, imageCollection);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(position);
    }

}
