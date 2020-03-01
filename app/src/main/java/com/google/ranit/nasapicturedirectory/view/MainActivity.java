package com.google.ranit.nasapicturedirectory.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.ranit.nasapicturedirectory.R;
import com.google.ranit.nasapicturedirectory.data.DataManager;
import com.google.ranit.nasapicturedirectory.model.Image;
import com.google.ranit.nasapicturedirectory.model.ImageUrl;
import com.google.ranit.nasapicturedirectory.utils.Constants;
import com.google.ranit.nasapicturedirectory.utils.GridItemDecorator;
import com.google.ranit.nasapicturedirectory.utils.Response;
import com.google.ranit.nasapicturedirectory.utils.Status;

import java.util.Collection;
import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    DataManager managerInstance;
    private Response jsonFetchingResponse;
    private Collection<Image> sortedImageCollection;
    private TreeMap<Integer, ImageUrl> imageUrlTreeMap;

    private RecyclerView recyclerView;
    private ImageThumbnailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get Manager Instance
        managerInstance = DataManager.getInstance();
        jsonFetchingResponse = managerInstance.parseJsonData();

        if (jsonFetchingResponse.getStatus() == Status.SUCCESS) {
            sortedImageCollection = managerInstance.sortListBasedOnDate();
            imageUrlTreeMap = managerInstance.treeMapOfImageUrlData();
            prepareRecyclerView();
        } else if (jsonFetchingResponse.getStatus() == Status.ERROR) {

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        managerInstance.cleanManager();
    }

    // Prepare Recycler View
    private void prepareRecyclerView() {
        recyclerView = findViewById(R.id.recycler_view);
        adapter = new ImageThumbnailAdapter(imageUrlTreeMap);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, Constants.SPAN_COUNT);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new GridItemDecorator(Constants.SPAN_COUNT, Constants.ITEM_SPACING));
        recyclerView.setAdapter(adapter);
    }


}
