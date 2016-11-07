package com.modnakompanija.hci_modnakompanija;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.modnakompanija.hci_modnakompanija.Area_Autentifikacija.LoginActivity;
import com.modnakompanija.hci_modnakompanija.Area_Dizajner.RegistracijaDialog;

/**
 * Created by Arza on 18.11.2015.
 */
public class PocetnaActivity  extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetna);


        TextView registracija = (TextView) findViewById(R.id.btnRegistracija_pocetna);
        TextView prijava = (TextView) findViewById(R.id.btnPrijava_pocetna);

        registracija.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do_registracija_click();
            }
        });

        prijava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do_prijava_click();
            }
        });

    }


            private void do_registracija_click() {
                DialogFragment f = RegistracijaDialog.newInstance();
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                f.show(fragmentManager, "key");
            }




    private void do_prijava_click() {

        final Intent intent = new Intent(new Intent(PocetnaActivity.this, LoginActivity.class));

        startActivity(intent);
    }

}



