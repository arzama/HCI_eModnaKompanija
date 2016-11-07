package com.modnakompanija.hci_modnakompanija.Helper.Http;

import java.io.Serializable;

/**
 * Created by Arza on 10.10.2015.
 */
public class ApiRezultat<T> implements Serializable {
    public T value;
    public boolean isError;
    public String errorMessage;
}
