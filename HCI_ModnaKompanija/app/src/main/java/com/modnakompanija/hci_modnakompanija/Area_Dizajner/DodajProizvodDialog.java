package com.modnakompanija.hci_modnakompanija.Area_Dizajner;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.modnakompanija.hci_modnakompanija.APIconnection.getProizvodi;
import com.modnakompanija.hci_modnakompanija.Helper.MyRunnable;
import com.modnakompanija.hci_modnakompanija.Model.DodajVM;
import com.modnakompanija.hci_modnakompanija.Model.ProizvodiVM;
import com.modnakompanija.hci_modnakompanija.R;

/**
 * Created by Arza on 17.11.2015.
 */
public class DodajProizvodDialog extends DialogFragment {
    private View rootView;
    private EditText txtNaziv;

    private EditText txtOpis;
    private EditText txtDatum;
    private EditText txtMaterijal;
    private EditText txtBoja;
    private EditText txtKolekcija;
    RadioButton myOption1, myOption2, myOption3;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.layout_dodaj,container,false);


        Dialog dialog =getDialog();
        if(dialog!=null)
            dialog.setTitle("Nova kreacija");

        final Button btnDodaj= (Button) rootView.findViewById(R.id.btnDodaj);


        myOption1 = (RadioButton)rootView.findViewById(R.id.Man_dodaj);
        myOption2 = (RadioButton)rootView.findViewById(R.id.Woman_dodaj);
        myOption3 = (RadioButton)rootView.findViewById(R.id.Kid_dodaj);
        myOption1.setOnClickListener(myOptionOnClickListener);
        myOption2.setOnClickListener(myOptionOnClickListener);
        myOption3.setOnClickListener(myOptionOnClickListener);
        myOption1.setChecked(true);




        txtNaziv = (EditText) rootView.findViewById(R.id.Naziv_dodaj);
        txtBoja= (EditText) rootView.findViewById(R.id.Boja_dodaj);
        txtMaterijal = (EditText) rootView.findViewById(R.id.Materijal_dodaj);
        txtDatum = (EditText) rootView.findViewById(R.id.Datum_dodaj);
        txtKolekcija = (EditText) rootView.findViewById(R.id.Kolekcija_dodaj);
        txtOpis = (EditText) rootView.findViewById(R.id.Opis_dodaj);



        btnDodaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnDodaj_click();
            }
        });


        return rootView;
    }

    final DodajVM proizvod=new DodajVM();

    RadioButton.OnClickListener myOptionOnClickListener =
            new RadioButton.OnClickListener()
            {

                @Override
                public void onClick(View v) {
                    if(myOption1.isChecked())
                    {   proizvod.VrstaProizvodaId=1;  }

                    if(myOption2.isChecked())
                    {   proizvod.VrstaProizvodaId=2;  }
                    if(myOption3.isChecked())
                    {   proizvod.VrstaProizvodaId=3;  }
                }



            };


    private void btnDodaj_click() {

        boolean valid=true;
        if(txtNaziv.getText().toString().equals("") || txtBoja.getText().toString().equals("") ||
                txtMaterijal.getText().toString().equals("") || txtDatum.getText().toString().equals("") ||
                txtOpis.getText().toString().equals("") || txtKolekcija.getText().toString().equals("") )
            valid=false;





        if(valid)
        {



            proizvod.NazivProizvoda=txtNaziv.getText().toString();
           proizvod.Datum=txtDatum.getText().toString();
            proizvod.Materijal= txtMaterijal.getText().toString();
            proizvod.Boja= txtBoja.getText().toString();
            proizvod.OpisProizvoda=txtOpis.getText().toString();
            proizvod.Kolekcija=txtKolekcija.getText().toString();



            MyRunnable<ProizvodiVM.proizvodi> onSuccess=new MyRunnable<ProizvodiVM.proizvodi>() {
                @Override
                public void run(ProizvodiVM.proizvodi response) {
                    Toast.makeText(getActivity().getBaseContext(), "Uspješno dodana kreacija!", Toast.LENGTH_LONG).show();
                    dismiss();
                }
            };
            getProizvodi.NoviProizvod(getActivity().getBaseContext(), proizvod, onSuccess);
        }
        else
            Toast.makeText(getActivity().getBaseContext(),"Unesite podatke!",Toast.LENGTH_LONG).show();

    }

    public static android.support.v4.app.DialogFragment newInstance() {
        DodajProizvodDialog fragment = new DodajProizvodDialog();
        return  fragment;
    }
}
