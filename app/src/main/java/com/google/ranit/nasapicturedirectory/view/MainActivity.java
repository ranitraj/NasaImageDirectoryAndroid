package com.google.ranit.nasapicturedirectory.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.ranit.nasapicturedirectory.R;
import com.google.ranit.nasapicturedirectory.data.DataManager;
import com.google.ranit.nasapicturedirectory.model.ImageUrl;
import com.google.ranit.nasapicturedirectory.utils.Constants;
import com.google.ranit.nasapicturedirectory.utils.GridItemDecorator;
import com.google.ranit.nasapicturedirectory.utils.Response;
import com.google.ranit.nasapicturedirectory.utils.Status;

import java.util.TreeMap;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private DataManager managerInstance;
    private TreeMap<Integer, ImageUrl> imageUrlTreeMap;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        TextView errorMessageTextView = findViewById(R.id.error_text_view);

        // Get Manager Instance
        managerInstance = DataManager.getInstance();
        Response jsonFetchingResponse = managerInstance.parseJsonData();

        if (jsonFetchingResponse.getStatus() == Status.SUCCESS) {
            managerInstance.sortListBasedOnDate();
            imageUrlTreeMap = managerInstance.treeMapOfImageUrlData();
            prepareRecyclerView();

        } else if (jsonFetchingResponse.getStatus() == Status.ERROR) {
            recyclerView.setVisibility(View.GONE);
            errorMessageTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        managerInstance.cleanManager();
    }

    // Prepare Recycler View
    private void prepareRecyclerView() {
        ImageThumbnailAdapter adapter = new ImageThumbnailAdapter(MainActivity.this, imageUrlTreeMap);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, Constants.SPAN_COUNT);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new GridItemDecorator(Constants.SPAN_COUNT, Constants.ITEM_SPACING));
        recyclerView.setAdapter(adapter);
    }


}
