package com.modnakompanija.hci_modnakompanija.Helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.modnakompanija.hci_modnakompanija.Model.KorisniciVM;
import com.modnakompanija.hci_modnakompanija.Model.NarudzbaNarudzbaStavkeVM;
import com.modnakompanija.hci_modnakompanija.Model.NarudzbaVM;

import java.util.List;

/**
 * Created by Arza on 10.10.2015.
 */
public class Sesija {



    public static List<NarudzbaNarudzbaStavkeVM> narudzbaStavke;
    public static NarudzbaVM NovaNarudzba;
    public static boolean KreiranaNovaNarudzba=false;


    private static String PREFS_NAME="DatotekaSharedPreferences";




    public static KorisniciVM getLogiraniKorisnik(Context context){

        SharedPreferences settings= context.getSharedPreferences(PREFS_NAME, 0);
        String logiraniKorisnikJson=settings.getString("logiraniKorisnikJson", "");
        if(logiraniKorisnikJson.length()==0)
            return  null;

        KorisniciVM vm = MyGson.build().fromJson(logiraniKorisnikJson, KorisniciVM.class);
        return vm;
    }
    public  static void setLogiraniKorisnik(Context context, KorisniciVM korisnik){
        String strLogiraniKorisnik=MyGson.build().toJson(korisnik);
        SharedPreferences settings=context.getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor=settings.edit();
        editor.putString("logiraniKorisnikJson",strLogiraniKorisnik);
        editor.commit();

    }

    public static void OdjavaKorisnik(Context context){
        SharedPreferences settings=context.getSharedPreferences(PREFS_NAME,0);
        SharedPreferences.Editor editor=settings.edit();
        editor.clear().commit();
    }


}
