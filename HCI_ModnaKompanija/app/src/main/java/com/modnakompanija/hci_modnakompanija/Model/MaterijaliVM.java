package com.modnakompanija.hci_modnakompanija.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Arza on 19.11.2015.
 */
public class MaterijaliVM implements Serializable {

    public class MaterijaliInfo  implements Serializable
    {
        public int Id;
        public String Naziv;

        public String Sifra;
        public double Cijena;
        public String Opis;

    }
    public List<MaterijaliInfo> MaterijaliLista;
}