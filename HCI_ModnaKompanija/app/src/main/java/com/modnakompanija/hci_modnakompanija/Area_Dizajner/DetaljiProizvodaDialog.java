package com.modnakompanija.hci_modnakompanija.Area_Dizajner;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.modnakompanija.hci_modnakompanija.Model.ProizvodiVM;
import com.modnakompanija.hci_modnakompanija.R;

/**
 * Created by Arza on 18.11.2015.
 */
public class DetaljiProizvodaDialog extends DialogFragment {


    private static ProizvodiVM.proizvodi Proizvod;
    private View rootview;

    private ProizvodiVM.proizvodi proizvod;

    public static DetaljiProizvodaDialog newInstance(ProizvodiVM.proizvodi pom)
    {
        DetaljiProizvodaDialog fragment = new DetaljiProizvodaDialog();

        Bundle arg=new Bundle();
        arg.putSerializable("model",pom);
        fragment.setArguments(arg);
        Proizvod =pom;
        return fragment;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootview = inflater.inflate(R.layout.layout_detaljiproizvoda, container, false);
        Bundle bundle = getArguments();
        proizvod = (ProizvodiVM.proizvodi) bundle.getSerializable("model");


        Dialog dialog =getDialog();
        if(dialog!=null)
            dialog.setTitle("Detalji kreacije");

        final Button btnOk = (Button) rootview.findViewById(R.id.detalji_btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do_btnOk_click();
            }
        });

        ((TextView) rootview.findViewById(R.id.detalji_kolekcija)).setText(proizvod.Kolekcija);
        ((TextView) rootview.findViewById(R.id.detalji_boja)).setText(proizvod.Boja);
        ((TextView) rootview.findViewById(R.id.detalji_datum)).setText(proizvod.Datum);

        ((TextView)rootview.findViewById(R.id.detalji_opis)).setText(proizvod.OpisProizvoda);
        ((TextView) rootview.findViewById(R.id.detalji_materijal)).setText(proizvod.Materijal);




        return rootview;
    }
    private void do_btnOk_click() {


                    dismiss();
}

    public static android.support.v4.app.DialogFragment newInstance() {
        RegistracijaDialog fragment = new RegistracijaDialog();
        return  fragment;
    }


}
