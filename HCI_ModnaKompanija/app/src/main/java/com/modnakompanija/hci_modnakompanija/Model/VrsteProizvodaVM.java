package com.modnakompanija.hci_modnakompanija.Model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Arza on 1.11.2015.
 */
public class VrsteProizvodaVM implements Serializable{
    public List<Vrste> _VrsteProizvoda;

    public class  Vrste implements Serializable {
        public int Id ;
        public String NazivVrste;
    }

}
