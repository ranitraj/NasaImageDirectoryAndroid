package com.google.ranit.nasapicturedirectory.data;

import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.ranit.nasapicturedirectory.model.Image;
import com.google.ranit.nasapicturedirectory.utils.GlobalUtilityClass;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Collection;

/*
 * Singleton Class
 */
public class DataManager {
    private static final String FILE_NAME = "data.json";
    private Collection<Image> responseData;

    private static DataManager instance;

    // Private Constructor
    private DataManager() {
    }

    public static synchronized DataManager getInstance() {
        return (instance == null) ? new DataManager() : instance;
    }

    /*
     * This method does the following:
     * 1. Parse JSON Data from assets folder using GSON
     * 2. Store the JSON response into a Collection<Image> object
     * 3. Return the parsed JSON response
     */
    public Collection<Image> parseJsonData() {
        try {
            AssetManager assetManager = GlobalUtilityClass.context.getAssets();
            InputStream stream = assetManager.open(FILE_NAME);

            Gson gson = new Gson();
            Reader reader = new InputStreamReader(stream);

            Type collectionType = new TypeToken<Collection<Image>>(){}.getType();
            responseData = gson.fromJson(reader, collectionType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return responseData;
    }

}
