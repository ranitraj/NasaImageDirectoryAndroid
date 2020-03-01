package com.google.ranit.nasapicturedirectory.utils;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Status {
    public static final int SUCCESS = 0;
    public static final int LOADING = 1;
    public static final int ERROR = 2;

    @IntDef({SUCCESS, LOADING, ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface responseState {}
}
