package com.modnakompanija.hci_modnakompanija.Area_Dizajner;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.modnakompanija.hci_modnakompanija.APIconnection.getProizvodi;
import com.modnakompanija.hci_modnakompanija.APIconnection.getVrsteProizvoda;
import com.modnakompanija.hci_modnakompanija.Helper.MyRunnable;
import com.modnakompanija.hci_modnakompanija.Model.ProizvodiVM;
import com.modnakompanija.hci_modnakompanija.Model.VrsteProizvodaVM;
import com.modnakompanija.hci_modnakompanija.R;

import java.text.SimpleDateFormat;
/**
 * Created by Arza on 15.11.2015.
 */
public class KatalogActivity extends AppCompatActivity {


    private Spinner lista;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katalog);

        lista = (Spinner) findViewById(R.id.VrsteProivodaList);


        MyRunnable<VrsteProizvodaVM> onSuccess = new MyRunnable<VrsteProizvodaVM>() {
            public void run(VrsteProizvodaVM response) {

                SetujSpinner(response);
            }
        };
        getVrsteProizvoda.getVrste(getBaseContext(), onSuccess);

    }

    private void SetujSpinner(VrsteProizvodaVM response) {

        final String[] listaVrsta = new String[response._VrsteProizvoda.size()];
        for (int i = 0; i < response._VrsteProizvoda.size(); i++) {
            listaVrsta[i] = response._VrsteProizvoda.get(i).NazivVrste;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(KatalogActivity.this, R.layout.layout_spinner_item, listaVrsta);
        lista.setAdapter(adapter);


        lista.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String selected = lista.getSelectedItem().toString();
                SetujListuProizvoda(selected);

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }


    private void SetujListuProizvoda(String selected) {

        MyRunnable<ProizvodiVM> onSuccess = new MyRunnable<ProizvodiVM>() {
            @Override
            public void run(final ProizvodiVM response) {
                final ListView lvProizvodi = (ListView) findViewById(R.id.lvProizvodi);


                lvProizvodi.setOnItemClickListener(   new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        Object a= lvProizvodi.getItemAtPosition(i);


                        ProizvodiVM.proizvodi pom= (ProizvodiVM.proizvodi) a;



                        DialogFragment f = DetaljiProizvodaDialog.newInstance(pom);
                        android.support.v4.app.FragmentManager fragmentManager= getSupportFragmentManager();
                        f.show(fragmentManager,"key");

                    }
                });




                lvProizvodi.setAdapter(new BaseAdapter() {
                    @Override

                    public int getCount() {
                        return response._Proizvodi.size();
                    }

                    @Override
                    public Object getItem(int position) {
                        return response._Proizvodi.get(position);
                    }

                    @Override
                    public long getItemId(int position) {
                        return response._Proizvodi.get(position).Id;
                    }


                    @Override
                    public View getView(int position, View view, ViewGroup viewGroup) {
                        if (view == null) {
                            LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            view = inflater.inflate(R.layout.layout_proizvodi_item, viewGroup, false);
                        }
                        ProizvodiVM.proizvodi x = response._Proizvodi.get(position);

                        ((TextView) view.findViewById(R.id.pro_Naziv)).setText(x.NazivProizvoda);



                        return view;
                    }
                });

            }
        };
        getProizvodi.getProizvode(getBaseContext(), selected, onSuccess);
    }

}
