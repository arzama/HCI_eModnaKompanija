package com.modnakompanija.hci_modnakompanija.Helper;

import java.io.Serializable;

/**
 * Created by Arza on 10.10.2015.
 */
public abstract class MyRunnable<T> implements Serializable {

    public abstract void run(T response);
}
