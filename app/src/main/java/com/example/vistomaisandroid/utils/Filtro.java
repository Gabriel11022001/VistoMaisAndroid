package com.example.vistomaisandroid.utils;

import android.os.Parcelable;

import java.util.List;

public abstract class Filtro<T> implements Parcelable {

    public abstract List<T> filtrar();

}
