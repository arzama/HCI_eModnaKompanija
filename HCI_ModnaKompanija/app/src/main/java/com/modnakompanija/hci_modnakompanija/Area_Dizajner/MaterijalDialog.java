package com.modnakompanija.hci_modnakompanija.Area_Dizajner;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.modnakompanija.hci_modnakompanija.Helper.Sesija;
import com.modnakompanija.hci_modnakompanija.Model.MaterijaliVM;
import com.modnakompanija.hci_modnakompanija.Model.NarudzbaNarudzbaStavkeVM;
import com.modnakompanija.hci_modnakompanija.Model.NarudzbaVM;
import com.modnakompanija.hci_modnakompanija.R;

import java.util.ArrayList;

/**
 * Created by Arza on 19.11.2015.
 */
public class MaterijalDialog extends DialogFragment {

    private static MaterijaliVM.MaterijaliInfo materijal;
    private View rootview;
    private int izabranaKolicina;
    private static MaterijaliVM.MaterijaliInfo Materijal;

    @Nullable


    public  static MaterijalDialog newInstance( MaterijaliVM.MaterijaliInfo p) {
        MaterijalDialog f = new MaterijalDialog();


        Bundle args = new Bundle();
        args.putSerializable("model", p);
        f.setArguments(args);

        materijal = p;

        return f;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.layout_materijal_detalji, container, false);

        Bundle bundle = getArguments();
        Materijal = (MaterijaliVM.MaterijaliInfo) bundle.getSerializable("model");

        Dialog dialog = getDialog();
        if (dialog != null)
            dialog.setTitle("Materijal");

        Button btnNaruci = (Button) rootview.findViewById(R.id.btnNaruci_materijal);

        btnNaruci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do_btnNaruci_click();
            }
        });

        ((TextView) rootview.findViewById(R.id.txtMaterijal)).setText(Materijal.Naziv);

        ((TextView) rootview.findViewById(R.id.txtCijenaMaterijala)).setText(Materijal.Cijena + "" + " KM");

        Spinner spKolicina = (Spinner) rootview.findViewById(R.id.spinnerKolicina);

        String[] listaKolicina = {"Odaberite količinu", "1", "2", "3", "4", "5"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),R.layout.layout_spinner_item, listaKolicina);
        spKolicina.setAdapter(adapter);

        spKolicina.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                izabranaKolicina = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return rootview;
    }

    private void do_btnNaruci_click() {

        if (izabranaKolicina == 0) {
            Toast.makeText(getActivity(), "Odaberite kolicinu", Toast.LENGTH_LONG).show();
        } else if (Sesija.KreiranaNovaNarudzba == false) {
            Sesija.NovaNarudzba = new NarudzbaVM();
            Sesija.narudzbaStavke = new ArrayList<NarudzbaNarudzbaStavkeVM>();
            Sesija.KreiranaNovaNarudzba = true;
            Sesija.NovaNarudzba.KorisnikId = Sesija.getLogiraniKorisnik(getActivity().getBaseContext()).Id;
        } else {
            NarudzbaNarudzbaStavkeVM n = new NarudzbaNarudzbaStavkeVM();
            boolean postoji = false;
            for (int i = 0; i < Sesija.narudzbaStavke.size(); i++) {
                if (Sesija.narudzbaStavke.get(i).MaterijalId == materijal.Id) {
                    postoji = true;
                    Sesija.narudzbaStavke.get(i).kolicina += izabranaKolicina;
                }

            }
            if (postoji == false) {
                n.Cijena = materijal.Cijena;
                n.NazivProizvoda = materijal.Naziv;
                n.MaterijalId = materijal.Id;
                n.kolicina = izabranaKolicina;

                Sesija.narudzbaStavke.add(n);

                Sesija.NovaNarudzba.Iznos += n.Cijena * izabranaKolicina;

                Toast.makeText(getActivity(), "Dodali ste materijal u korpu !!!", Toast.LENGTH_LONG).show();
            }

            dismiss();
        }
    }
}