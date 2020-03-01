package com.google.ranit.nasapicturedirectory.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.ranit.nasapicturedirectory.R;
import com.google.ranit.nasapicturedirectory.data.DataManager;
import com.google.ranit.nasapicturedirectory.model.Image;
import com.google.ranit.nasapicturedirectory.model.ImageUrl;
import com.google.ranit.nasapicturedirectory.utils.Response;

import java.util.Collection;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Response jsonFetchingResponse;
    private Collection<Image> sortedImageCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get Manager Instance
        DataManager managerInstance = DataManager.getInstance();

        jsonFetchingResponse = managerInstance.parseJsonData();

        sortedImageCollection = managerInstance.sortListBasedOnDate();

    }
}
