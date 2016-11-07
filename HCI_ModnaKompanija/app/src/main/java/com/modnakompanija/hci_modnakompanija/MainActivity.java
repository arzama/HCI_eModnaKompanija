package com.modnakompanija.hci_modnakompanija;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
/**
 * Created by Arza on 21.10.2015.
 */
import com.modnakompanija.hci_modnakompanija.Area_Autentifikacija.LoginActivity;
import com.modnakompanija.hci_modnakompanija.Area_Dizajner.HomeActivity;
import com.modnakompanija.hci_modnakompanija.Helper.Sesija;
public class MainActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        if (Sesija.getLogiraniKorisnik( getApplication().getBaseContext()) == null){
            Intent i=new Intent(this, LoginActivity.class);

            startActivity(i);
        }else
        {



            Intent i=new Intent(MainActivity.this,HomeActivity.class);

            startActivity(i);
        }

        finish();
    }



}
