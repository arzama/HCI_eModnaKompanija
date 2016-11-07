package com.modnakompanija.hci_modnakompanija.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Arza on 21.10.2015.
 */
public class ProizvodiVM implements Serializable {

    public List<proizvodi> _Proizvodi ;

    public class proizvodi implements  Serializable
    {
        public int Id ;
        public String NazivProizvoda ;
        public int VrstaProizvodaId ;
        public String Kolekcija ;
        public String Boja;
        public String Materijal;
        public String Datum;
        public String OpisProizvoda ;
    }

}
