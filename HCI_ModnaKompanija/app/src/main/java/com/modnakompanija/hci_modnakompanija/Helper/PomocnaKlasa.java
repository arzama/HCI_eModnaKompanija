package com.modnakompanija.hci_modnakompanija.Helper;

import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Arza on 19.11.2015.
 */
public class PomocnaKlasa {
    public static <T> T findView(View view, int id, Class<T> tClass)
    {
        final View viewById = view.findViewById(id);
        return (T) viewById;
    }

    public static String Date_ddMMyyyy(Date datum)
    {
        final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return sdf.format(datum);
    }


}

