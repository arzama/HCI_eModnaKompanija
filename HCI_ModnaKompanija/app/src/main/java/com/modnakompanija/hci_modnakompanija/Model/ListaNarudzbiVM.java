package com.modnakompanija.hci_modnakompanija.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Arza on 19.11.2015.
 */
public class ListaNarudzbiVM implements Serializable {

    public  class NarudzbeInfo
    {
        public int NarudzbaID ;

        public int KorisnikId ;
        public Date Datum ;
        public List<NarudzbaStavkeInfo> StavkeList ;

    }


    public  class NarudzbaStavkeInfo implements Serializable
    {
        public int NarudzbaStavkaID;
        public int NarudzbaID ;
        public int Kolicina ;
        public double CijenaMaterijala ;
        public String NazivMaterijala ;
    }
    public List<NarudzbeInfo> NarudzbeList ;
}
