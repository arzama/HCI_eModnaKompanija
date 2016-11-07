package com.modnakompanija.hci_modnakompanija.APIconnection;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.modnakompanija.hci_modnakompanija.Helper.Http.ApiRezultat;
import com.modnakompanija.hci_modnakompanija.Helper.Http.HttpManager;
import com.modnakompanija.hci_modnakompanija.Helper.Http.MyAppContext;
import com.modnakompanija.hci_modnakompanija.Helper.MyRunnable;
import com.modnakompanija.hci_modnakompanija.Model.NarudzbaNarudzbaStavkeVM;
import com.modnakompanija.hci_modnakompanija.Model.NarudzbaVM;
import com.modnakompanija.hci_modnakompanija.Model.ListaNarudzbiVM;

import org.apache.http.message.BasicNameValuePair;

/**
 * Created by Arza on 12.11.2015.
 */
public class getNarudzba {
    public static void GetNarudzbeSaStavkama(final Context context, final int kupacId, final MyRunnable<ListaNarudzbiVM> onSuccess) {

        final String url = "http://hci106.app.fit.ba/api/Narudzba/GetNarudzbeSaStavkama";

        new AsyncTask<Void, Void, ApiRezultat<ListaNarudzbiVM>>() {

            @Override
            protected ApiRezultat<ListaNarudzbiVM> doInBackground(Void... params) {
                String s = Integer.toString(kupacId);
                return HttpManager.get(url, ListaNarudzbiVM.class,
                        new BasicNameValuePair("kupacId", s));

            }

            @Override
            protected void onPostExecute(ApiRezultat<ListaNarudzbiVM> rezultat) {

                if (rezultat.isError)
                    Toast.makeText(MyAppContext.getContext(), "Greška u komunikaciji sa serverom" + rezultat.errorMessage, Toast.LENGTH_LONG).show();
                else {
                    onSuccess.run(rezultat.value);
                }


            }
        }.execute();
    }

    public static void DodajNarudzbu(final Context context,final Object inputObject, final MyRunnable<NarudzbaVM> onSuccess)
    {


        final String url = "http://hci106.app.fit.ba/api/Narudzba/Post";



        new AsyncTask<Void, Void, ApiRezultat<NarudzbaVM>>()
        {
            @Override
            protected ApiRezultat<NarudzbaVM> doInBackground(Void... params)
            {
                return HttpManager.post(url, NarudzbaVM.class, inputObject);
            }

            @Override
            protected void onPostExecute(ApiRezultat<NarudzbaVM> rezultat)
            {

                if (!rezultat.isError)
                    onSuccess.run(rezultat.value);
                else
                {
                    Toast.makeText(context, "Greška: " + rezultat.errorMessage, Toast.LENGTH_LONG).show();
                }
            }
        }.execute();
    }
    public static void DodajStavke(final Context context,final Object inputObject, final MyRunnable<NarudzbaNarudzbaStavkeVM> onSuccess)
    {


        final String url ="http://hci106.app.fit.ba/api/Narudzba/DodajStavke";


        new AsyncTask<Void, Void, ApiRezultat<NarudzbaNarudzbaStavkeVM>>()
        {
            @Override
            protected ApiRezultat<NarudzbaNarudzbaStavkeVM> doInBackground(Void... params)
            {
                return HttpManager.post(url, NarudzbaNarudzbaStavkeVM.class,inputObject);
            }

            @Override
            protected void onPostExecute(ApiRezultat<NarudzbaNarudzbaStavkeVM> rezultat)
            {

                if (!rezultat.isError)
                    onSuccess.run(rezultat.value);
                else
                {
                    Toast.makeText(context, "Greška: " + rezultat.errorMessage, Toast.LENGTH_LONG).show();
                }
            }
        }.execute();
    }

}
