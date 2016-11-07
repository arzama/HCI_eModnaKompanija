package com.modnakompanija.hci_modnakompanija.Area_Autentifikacija;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.modnakompanija.hci_modnakompanija.Area_Dizajner.HomeActivity;
import com.modnakompanija.hci_modnakompanija.Helper.MyRunnable;
import com.modnakompanija.hci_modnakompanija.Helper.Sesija;
import com.modnakompanija.hci_modnakompanija.Model.KorisniciVM;
import com.modnakompanija.hci_modnakompanija.R;

/**
 * Created by Arza on 15.11.2015.
 */
public class LoginActivity  extends AppCompatActivity {



    private TextView korisnickoIme;
    private TextView lozinka;
    private Button btnLogin;
    private Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        korisnickoIme = (TextView) findViewById(R.id.txtKorisnickoIme);
        lozinka = (TextView) findViewById(R.id.txtLozinka);

        btnLogin = (Button) findViewById(R.id.btnPrijava);




        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                do_btnLogini_Click();
            }

            private void do_btnLogini_Click() {
                MyRunnable<KorisniciVM> onSucces= new MyRunnable<KorisniciVM>() {
                    @Override
                    public void run(KorisniciVM response) {



                        if(response==null){

                            Toast.makeText(context, "Pogresni podaci!", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Sesija.setLogiraniKorisnik(context, response);
                            Toast.makeText(context, "Uspješno logiran !", Toast.LENGTH_LONG).show();

                            startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                        }
                    }
                };
                AutentifikacijaApi.Provjera2(context,korisnickoIme.getText().toString(),lozinka.getText().toString(),onSucces);

            }
        });




    }




}