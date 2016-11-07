package com.modnakompanija.hci_modnakompanija.Area_Dizajner;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.modnakompanija.hci_modnakompanija.Model.KorisniciVM;
import com.modnakompanija.hci_modnakompanija.APIconnection.getKorisnik;
import com.modnakompanija.hci_modnakompanija.Helper.MyRunnable;
import com.modnakompanija.hci_modnakompanija.R;


/**
 * Created by Arza on 12.11.2015.
 */
public class RegistracijaDialog extends DialogFragment {
    private View rootView;
    private EditText txtIme;
    private EditText txtPrezime;
    private EditText txtAdresa;
    private EditText txtKontakt;
    private EditText txtEmail;
    private EditText txtKorisnickoIme;
    private EditText txtLozinka;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.layout_registracija,container,false);

        Dialog dialog =getDialog();
        if(dialog!=null)
            dialog.setTitle("Registracija");

        final Button btnRegistrujSe= (Button) rootView.findViewById(R.id.btnRegistrujSe);

        txtIme = (EditText) rootView.findViewById(R.id.txtnIme);
        txtPrezime = (EditText) rootView.findViewById(R.id.txtnPrezime);
        txtEmail = (EditText) rootView.findViewById(R.id.txtnEmail);
        txtAdresa = (EditText) rootView.findViewById(R.id.txtnAdresa);
        txtKontakt = (EditText) rootView.findViewById(R.id.txtnKontakt);
        txtKorisnickoIme = (EditText) rootView.findViewById(R.id.txtnKorisnickoIme);
        txtLozinka = (EditText) rootView.findViewById(R.id.txtnLozinka);

        btnRegistrujSe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnRegistrujSe_click();
            }
        });


        return rootView;
    }

    private void btnRegistrujSe_click() {

        boolean valid=true;
        if(txtIme.getText().toString().equals("") || txtPrezime.getText().toString().equals("") ||
                txtEmail.getText().toString().equals("") || txtKorisnickoIme.getText().toString().equals("") || txtLozinka.getText().toString().equals(""))
            valid=false;

        if(valid)
        {
            KorisniciVM korisnik=new KorisniciVM();

            korisnik.Ime=txtIme.getText().toString();
            korisnik.Prezime=txtPrezime.getText().toString();
            korisnik.Email=txtEmail.getText().toString();
            korisnik.KorisnickoIme=txtKorisnickoIme.getText().toString();
            korisnik.Lozinka=txtLozinka.getText().toString();
            korisnik.Adresa=txtAdresa.getText().toString();
            korisnik.Kontakt=txtKontakt.getText().toString();

            MyRunnable<KorisniciVM> onSuccess=new MyRunnable<KorisniciVM>() {
                @Override
                public void run(KorisniciVM response) {
                    Toast.makeText(getActivity().getBaseContext(), "Regostracija je uspješna !", Toast.LENGTH_LONG).show();
                    dismiss();
                }
            };
            getKorisnik.Registracija(getActivity().getBaseContext(),korisnik,onSuccess);
        }
        else
            Toast.makeText(getActivity().getBaseContext(),"Unesite podatke !",Toast.LENGTH_LONG).show();

    }

    public static android.support.v4.app.DialogFragment newInstance() {
        RegistracijaDialog fragment = new RegistracijaDialog();
        return  fragment;
    }
}
