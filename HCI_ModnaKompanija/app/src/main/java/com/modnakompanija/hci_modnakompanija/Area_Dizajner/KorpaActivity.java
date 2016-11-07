package com.modnakompanija.hci_modnakompanija.Area_Dizajner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.modnakompanija.hci_modnakompanija.APIconnection.getNarudzba;
import com.modnakompanija.hci_modnakompanija.Helper.MyRunnable;
import com.modnakompanija.hci_modnakompanija.Helper.Sesija;
import com.modnakompanija.hci_modnakompanija.MainActivity;
import com.modnakompanija.hci_modnakompanija.Model.NarudzbaNarudzbaStavkeVM;
import com.modnakompanija.hci_modnakompanija.Model.NarudzbaVM;
import com.modnakompanija.hci_modnakompanija.R;
/**
 * Created by Arza on 19.11.2015.
 */
public class KorpaActivity extends AppCompatActivity {

    private ListView lvKorpa;
    private Button btnOtkazi;
    private Button btnPotvrdi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korpa);

        lvKorpa = (ListView) findViewById(R.id.lvKorpa);
        btnOtkazi = (Button) findViewById(R.id.btnOtkazi);
        btnPotvrdi = (Button) findViewById(R.id.btnPotvrdiNarudzbu);
        btnOtkazi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do_btnOtkazi_click();
            }
        });
        btnPotvrdi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                do_btnPotvrdi_click();
            }
        });
        double ukupanIznos=0;
        if(Sesija.narudzbaStavke.size()>0)
        {
            for(int i=0;i<Sesija.narudzbaStavke.size();i++)
                ukupanIznos+=((Sesija.narudzbaStavke.get(i).Cijena)*(Sesija.narudzbaStavke.get(i).kolicina));
        }
        ((TextView)findViewById(R.id.txtUkupanIznos)).setText(ukupanIznos + "" + " KM");

        SetujListu();

    }

    private void SetujListu() {
        lvKorpa.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return Sesija.narudzbaStavke.size();
            }

            @Override
            public Object getItem(int position) {
                return Sesija.narudzbaStavke.get(position);
            }

            @Override
            public long getItemId(int position) {
                return Sesija.narudzbaStavke.get(position).MaterijalId;
            }

            @Override
            public View getView(int position, View view, ViewGroup parent) {
                LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.layout_korpa_item, parent, false);
                NarudzbaNarudzbaStavkeVM x=Sesija.narudzbaStavke.get(position);
                ((TextView) view.findViewById(R.id.txtProizvod)).setText(x.NazivProizvoda);
                ((TextView) view.findViewById(R.id.txtProizvodCijena)).setText(x.Cijena+""+" KM");
                ((TextView) view.findViewById(R.id.txtKolicinaProizvod)).setText(x.kolicina+"");

                return view;
            }
        });
    }

    private void do_btnPotvrdi_click() {

        MyRunnable<NarudzbaVM> onSuccess=new MyRunnable<NarudzbaVM>() {
            @Override
            public void run(NarudzbaVM response) {
                DodajStavke();
            }
        };
        getNarudzba.DodajNarudzbu(KorpaActivity.this, Sesija.NovaNarudzba, onSuccess);

    }

    private void DodajStavke() {
        MyRunnable<NarudzbaNarudzbaStavkeVM> onSuccess=new MyRunnable<NarudzbaNarudzbaStavkeVM>() {
            @Override
            public void run(NarudzbaNarudzbaStavkeVM response) {
                Toast.makeText(KorpaActivity.this, "Uspijesno ste zakljucili narudzbu !!", Toast.LENGTH_LONG).show();

                Sesija.KreiranaNovaNarudzba=false;
                Sesija.narudzbaStavke=null;
                Sesija.NovaNarudzba=null;

                Intent i=new Intent(KorpaActivity.this,MainActivity.class);
                startActivity(i);
            }
        };
        getNarudzba.DodajStavke(KorpaActivity.this, Sesija.narudzbaStavke, onSuccess);

    }

    private void do_btnOtkazi_click() {
        Intent i=new Intent(KorpaActivity.this,HomeActivity.class);
        Sesija.KreiranaNovaNarudzba=false;
        Sesija.narudzbaStavke=null;
        Sesija.NovaNarudzba=null;
        startActivity(i);

    }

}

