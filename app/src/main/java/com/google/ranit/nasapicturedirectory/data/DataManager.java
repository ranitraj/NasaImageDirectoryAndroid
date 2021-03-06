package com.google.ranit.nasapicturedirectory.data;

import android.content.res.AssetManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.ranit.nasapicturedirectory.model.Image;
import com.google.ranit.nasapicturedirectory.model.ImageUrl;
import com.google.ranit.nasapicturedirectory.utils.GlobalUtilityClass;
import com.google.ranit.nasapicturedirectory.utils.Response;
import com.google.ranit.nasapicturedirectory.utils.SortCollectionBasedOnDate;
import com.google.ranit.nasapicturedirectory.utils.Status;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.TreeMap;

/*
 * Singleton Class which manages all data
 */
public class DataManager {
    private static final String FILE_NAME = "data.json";
    private Collection<Image> responseData;
    private Response response;
    private Collection<Image> sortedList;
    private TreeMap<Integer, ImageUrl> imageUrlMap;

    private static DataManager instance;

    // Private Constructor
    private DataManager() {
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    /*
     * This method does the following:
     * 1. Parse JSON Data from assets folder using GSON
     * 2. Store the JSON response into a Collection<Image> object
     * 3. Return the Response Object
     *
     * NOTE: Initially - Data [null] & Status [LOADING]
     *       Successful parsing of Data - Data [Collection<Image>] & Status [SUCCESS]
     *       Error - Data [null] & Status [ERROR]
     */
    public Response parseJsonData() {
        try {
            response = new Response(null, Status.LOADING);

            AssetManager assetManager = GlobalUtilityClass.context.getAssets();
            InputStream stream = assetManager.open(FILE_NAME);

            Gson gson = new Gson();
            Reader reader = new InputStreamReader(stream);

            Type collectionType = new TypeToken<Collection<Image>>() {
            }.getType();
            responseData = gson.fromJson(reader, collectionType);

            response.setJsonImageData(responseData);
            response.setStatus(Status.SUCCESS);

        } catch (IOException e) {
            response.setStatus(Status.ERROR);
            e.printStackTrace();
        }
        return response;
    }

    /*
     * This method does the following:
     * 1. Receives the sorted list from SortCollectionBasedOnDate utility class
     * 2. Returns the sorted list
     */
    public void sortListBasedOnDate() {
        SortCollectionBasedOnDate sortedCollection = new SortCollectionBasedOnDate(responseData);
        sortedList = sortedCollection.getSortedImageCollection();
    }

    /*
     * This method does the following:
     * 1. Creates a new TreeMap<Integer, ImageUrl>
     * 2. Assigns unique id to every ImageUrl object
     * 3. Returns the TreeMap object
     */
    public TreeMap<Integer, ImageUrl> treeMapOfImageUrlData() {
        imageUrlMap = new TreeMap<>();
        Collection<Image> images = sortedList;
        int imageId = 0;

        for (Image imageObject : images) {
            imageUrlMap.put(imageId, new ImageUrl(imageObject.getUrl(), imageObject.getHdUrl()));
            imageId++;
        }
        return imageUrlMap;
    }

    /*
     * This method returns the Sorted Collection
     */
    public Collection<Image> getSortedList() {
        return sortedList;
    }

    /*
     * This method returns the ImageUrl TreeMap
     */
    public TreeMap<Integer, ImageUrl> getImageUrlMap() {
        return imageUrlMap;
    }

    /*
     * Remove Data Manager Instance
     */
    public void cleanManager() {
        instance = null;
    }

}
