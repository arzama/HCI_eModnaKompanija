package com.modnakompanija.hci_modnakompanija.Helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Arza on 10.10.2015.
 */
public class MyGson {
    public static Gson build(){

        GsonBuilder builder = new GsonBuilder();
        return builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();
    }
}
