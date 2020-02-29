package com.google.ranit.nasapicturedirectory.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.ranit.nasapicturedirectory.R;
import com.google.ranit.nasapicturedirectory.data.DataManager;
import com.google.ranit.nasapicturedirectory.model.Image;

import java.util.Collection;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Collection<Image> jsonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get Manager Instance
        DataManager managerInstance = DataManager.getInstance();

        jsonData = managerInstance.parseJsonData();
    }
}
