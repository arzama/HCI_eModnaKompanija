package com.modnakompanija.hci_modnakompanija.Area_Dizajner;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.modnakompanija.hci_modnakompanija.APIconnection.getMaterijali;
import com.modnakompanija.hci_modnakompanija.Helper.MyRunnable;
import com.modnakompanija.hci_modnakompanija.Model.MaterijaliVM;
import com.modnakompanija.hci_modnakompanija.R;

/**
 * Created by Arza on 19.11.2015.
 */
public class MaterijalActivity   extends AppCompatActivity {


    private ListView lvMaterijali;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materijali);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("Materijali");


        lvMaterijali= (ListView) findViewById(R.id.lvMaterijali);
        MyRunnable<MaterijaliVM> onSuccess=new MyRunnable<MaterijaliVM>() {
            @Override
            public void run(MaterijaliVM response) {
                NapuniListu(response);
            }
        };
        getMaterijali.getMaterijale(getBaseContext(),onSuccess);
  }


    private void NapuniListu(final MaterijaliVM response) {

        lvMaterijali.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return response.MaterijaliLista.size();
            }

            @Override
            public Object getItem(int position) {
                return response.MaterijaliLista.get(position);
            }

            @Override
            public long getItemId(int position) {
                return response.MaterijaliLista.get(position).Id   ;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater layoutInflater= (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView=layoutInflater.inflate(R.layout.layout_materijali_item, parent, false);

                MaterijaliVM.MaterijaliInfo materijalInfo=response.MaterijaliLista.get(position);
                ((TextView) convertView.findViewById(R.id.txtMaterijalNaziv)).setText(materijalInfo.Naziv);



                return  convertView;

            }
        });

        lvMaterijali.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int i, long id) {

                    Object materijal=lvMaterijali.getItemAtPosition(i);
                    MaterijaliVM.MaterijaliInfo p= (MaterijaliVM.MaterijaliInfo ) materijal;

                    DialogFragment dialog = MaterijalDialog.newInstance(p);
                    android.support.v4.app.FragmentManager fm = getSupportFragmentManager();

                    dialog.show(fm, "dialog");
                }
            });

    }

}


