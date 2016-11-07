package com.modnakompanija.hci_modnakompanija.Area_Dizajner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.modnakompanija.hci_modnakompanija.APIconnection.getKorisnik;
import com.modnakompanija.hci_modnakompanija.Helper.MyRunnable;
import com.modnakompanija.hci_modnakompanija.Helper.Sesija;
import com.modnakompanija.hci_modnakompanija.MainActivity;
import com.modnakompanija.hci_modnakompanija.Model.KorisniciVM;
import com.modnakompanija.hci_modnakompanija.R;

/**
 * Created by Arza on 15.11.2015.
 */
public class ProfilActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);


        setTitle("Korisnički profil");
        final KorisniciVM korisnik = Sesija.getLogiraniKorisnik(getBaseContext());

        Button btnSacuvaj= (Button) findViewById(R.id.btnSacuvaj_profil);
        Button btnOdustani= (Button) findViewById(R.id.btnOdustani);



        btnOdustani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetPodatke(korisnik);
                Intent intent=new Intent(ProfilActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });


        btnSacuvaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do_btnSacuvaj_click();
            }
        });

        ((TextView) findViewById(R.id.profil_ime)).setText(korisnik.Ime);
        ((TextView) findViewById(R.id.profil_prezime)).setText(korisnik.Prezime);
        ((TextView) findViewById(R.id.profil_adresa)).setText(korisnik.Adresa);
        ((TextView) findViewById(R.id.profil_kontakt)).setText(korisnik.Kontakt);
        ((TextView) findViewById(R.id.profil_email)).setText(korisnik.Email);
        ((TextView) findViewById(R.id.profil_korisnickoime)).setText(korisnik.KorisnickoIme);
        ((TextView) findViewById(R.id.profil_lozinka)).setText(korisnik.Lozinka);

    }

    private void do_btnSacuvaj_click()
    {


        KorisniciVM k=new KorisniciVM();

        k.Id=Sesija.getLogiraniKorisnik(getBaseContext()).Id;

        EditText a= (EditText) findViewById(R.id.profil_ime);
        k.Ime=a.getText().toString();
        k.Prezime=  ((TextView) findViewById(R.id.profil_prezime)).getText().toString();
        k.Email=((TextView) findViewById(R.id.profil_email)).getText().toString();
        k.Adresa=((TextView) findViewById(R.id.profil_adresa)).getText().toString();
        k.Kontakt=((TextView) findViewById(R.id.profil_kontakt)).getText().toString();
        k.KorisnickoIme=((TextView)findViewById(R.id.profil_korisnickoime)).getText().toString();
        k.Lozinka=((TextView)findViewById(R.id.profil_lozinka)).getText().toString();


        MyRunnable<KorisniciVM> onSuccess=new MyRunnable<KorisniciVM>() {
            @Override
            public void run(KorisniciVM response) {
                Toast.makeText(ProfilActivity.this, "Uspješna izmjena podataka!", Toast.LENGTH_SHORT).show();
                Sesija.OdjavaKorisnik(getBaseContext());
                Intent intent=new Intent(ProfilActivity.this, MainActivity.class);
                startActivity(intent);

            }
        };
        getKorisnik.IzmjenaPodataka(ProfilActivity.this, k, onSuccess);
    }

    private void SetPodatke(KorisniciVM korisnik) {
        ((TextView) findViewById(R.id.profil_ime)).setText(korisnik.Ime);
        ((TextView) findViewById(R.id.profil_prezime)).setText(korisnik.Prezime);
        ((TextView) findViewById(R.id.profil_email)).setText(korisnik.Email);
        ((TextView) findViewById(R.id.profil_adresa)).setText(korisnik.Adresa);
        ((TextView) findViewById(R.id.profil_kontakt)).setText(korisnik.Kontakt);
        ((TextView) findViewById(R.id.profil_korisnickoime)).setText(korisnik.KorisnickoIme);
        ((TextView) findViewById(R.id.profil_lozinka)).setText(korisnik.Lozinka);

    }
}
