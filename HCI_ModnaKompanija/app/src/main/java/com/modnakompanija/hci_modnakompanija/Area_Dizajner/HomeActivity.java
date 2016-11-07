package com.modnakompanija.hci_modnakompanija.Area_Dizajner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.modnakompanija.hci_modnakompanija.MainActivity;
import com.modnakompanija.hci_modnakompanija.Helper.Sesija;

import com.modnakompanija.hci_modnakompanija.R;
/**
 * Created by Arza on 15.11.2015.
 */
public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnProizvod = (Button)  findViewById(R.id.btnKatalog);
        btnProizvod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Intent intent = new Intent(new Intent(HomeActivity.this, KatalogActivity.class));

                startActivity(intent);
            }
        });
        Button btnMaterijali = (Button)  findViewById(R.id.btnMaterijali);

        btnMaterijali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Intent intent = new Intent(new Intent(HomeActivity.this, MaterijalActivity.class));

                startActivity(intent);
            }
        });

        Button btnAktivnaNarudzba = (Button)  findViewById(R.id.btnAktivnaNarudzba_home);
        btnAktivnaNarudzba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Sesija.KreiranaNovaNarudzba == true) {
                    final Intent intent = new Intent(new Intent(HomeActivity.this, KorpaActivity.class));

                    startActivity(intent);
                } else {
                    Toast.makeText(HomeActivity.this, "Korpa je prazna!", Toast.LENGTH_SHORT).show();

                }
            }
        });
        Button btnNarudzba = (Button)  findViewById(R.id.btnNarudzba_home);
        btnNarudzba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Intent intent = new Intent(new Intent(HomeActivity.this, ListaNarudzbiActivity.class));

                startActivity(intent);
            }
        });

        Button btnDodaj = (Button)  findViewById(R.id.btnDodaj);
        btnDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                DialogFragment f = DodajProizvodDialog.newInstance();
                android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
                f.show(fragmentManager, "key");
            }
        });
        Button btnKorisnickiProfil = (Button)  findViewById(R.id.bntKorisnickiProfil);
        btnKorisnickiProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Intent intent = new Intent(new Intent(HomeActivity.this, ProfilActivity.class));

                startActivity(intent);
            }
        });
        Button btnOdjava = (Button) findViewById(R.id.btnOdjava);
        btnOdjava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sesija.OdjavaKorisnik(getBaseContext());
                final Intent intent = new Intent(getBaseContext(), MainActivity.class);

                startActivity(intent);
            }
        });
    }}


