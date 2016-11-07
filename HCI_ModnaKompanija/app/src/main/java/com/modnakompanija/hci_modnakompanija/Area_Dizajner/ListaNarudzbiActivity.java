package com.modnakompanija.hci_modnakompanija.Area_Dizajner;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.modnakompanija.hci_modnakompanija.APIconnection.getNarudzba;
import com.modnakompanija.hci_modnakompanija.Helper.MyRunnable;
import com.modnakompanija.hci_modnakompanija.Helper.PomocnaKlasa;
import com.modnakompanija.hci_modnakompanija.Helper.Sesija;
import com.modnakompanija.hci_modnakompanija.Model.ListaNarudzbiVM;
import com.modnakompanija.hci_modnakompanija.R;

/**
 * Created by Arza on 19.11.2015.
 */
public class ListaNarudzbiActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_narudzba_list);

        setTitle("Narudzba");





        MyRunnable<ListaNarudzbiVM> onSuccess= new MyRunnable<ListaNarudzbiVM>() {
            @Override
            public void run(ListaNarudzbiVM response) {
                Toast.makeText(ListaNarudzbiActivity.this, "Kupac: " +  Sesija.getLogiraniKorisnik(getBaseContext()).Ime+ ", Broj narudzbi: " + response.NarudzbeList.size(), Toast.LENGTH_LONG).show();

                UcitajListu( response);
            }
        };
        getNarudzba.GetNarudzbeSaStavkama(ListaNarudzbiActivity.this, Sesija.getLogiraniKorisnik(getBaseContext()).Id,onSuccess);
    }






    private void UcitajListu(final ListaNarudzbiVM response) {
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.listViewEx);
        listView.setAdapter(new BaseExpandableListAdapter() {
            @Override
            public int getGroupCount() {
                return response.NarudzbeList.size(); }

            @Override
            public int getChildrenCount(int groupPosition) {
                return response.NarudzbeList.get(groupPosition).StavkeList.size();

            }

            @Override
            public Object getGroup(int groupPosition) {
                return null;
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return null;
            }

            @Override
            public long getGroupId(int groupPosition) {
                return 0;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return 0;
            }

            @Override
            public boolean hasStableIds() {
                return false;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {

                if (view == null) {

                    final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.layout_stavke_liste_narudzba, parent, false);

                    final ListaNarudzbiVM.NarudzbeInfo x = response.NarudzbeList.get(groupPosition);


                    PomocnaKlasa.findView(view, R.id.tvDatum, TextView.class).setText("Datum narudzbe: " + PomocnaKlasa.Date_ddMMyyyy(x.Datum));

                    float Total=0;

                    for (int i = 0;i < x.StavkeList.size(); i++ )
                    {
                        Total+= x.StavkeList.get(i).CijenaMaterijala *x.StavkeList.get(i).Kolicina;
                    }

                    ((TextView) view.findViewById(R.id.tvTotal)).setText("Ukupno: " + (Total));


                }
                return view;
            }

            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {
                if (view == null) {

                    final LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    view = inflater.inflate(R.layout.layout_stavka_narudzba, parent, false);}

                final ListaNarudzbiVM.NarudzbaStavkeInfo x = response.NarudzbeList.get(groupPosition).StavkeList.get(childPosition);



                ((TextView) view.findViewById(R.id.lvNazivProizvoda_stavka)).setText("Naziv proizvoda: " + x.NazivMaterijala);


               ((TextView) view.findViewById(R.id.lvCijena_stavka)).setText("Cijena: " + (x.CijenaMaterijala* x.Kolicina));

                ((TextView) view.findViewById(R.id.lvKolicina)).setText(String.valueOf("Naručena količina: " + x.Kolicina));



                return view;

            }



            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return false;
            }
        });
    }

}

