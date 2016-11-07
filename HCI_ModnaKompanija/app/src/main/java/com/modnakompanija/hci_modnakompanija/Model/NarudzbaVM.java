package com.modnakompanija.hci_modnakompanija.Model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Arza on 11.11.2015.
 */
public class NarudzbaVM  implements Serializable {
    public int NarudzbaID ;
    public int Kolicina;

    public double Iznos;
  public Date Datum;
    public int KorisnikId;
}


