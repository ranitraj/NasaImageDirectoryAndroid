package com.google.ranit.nasapicturedirectory.utils;

import com.google.ranit.nasapicturedirectory.model.Image;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * This Utility Class is used to Sort the Collection<Image> object based on Date.
 */
public class SortCollectionBasedOnDate {
    private Collection<Image> sortedImageCollection = new ArrayList<>();

    public SortCollectionBasedOnDate(final Collection<Image> imageCollection) {

        Collections.sort((List<Image>) imageCollection, new Comparator<Image>() {
            @Override
            public int compare(Image object1, Image object2) {
                String firstDate = object1.getDate().replace("-", "");
                String secondDate = object2.getDate().replace("-", "");

                return -1 * firstDate.compareTo(secondDate);
            }
        });

        for (int i = 0; i < imageCollection.size(); i++) {
            sortedImageCollection.add(((List<Image>) imageCollection).get(i));
        }
    }

    public Collection<Image> getSortedImageCollection() {
        return sortedImageCollection;
    }
}
