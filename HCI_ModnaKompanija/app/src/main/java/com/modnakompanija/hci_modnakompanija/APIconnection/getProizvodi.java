package com.modnakompanija.hci_modnakompanija.APIconnection;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.modnakompanija.hci_modnakompanija.Helper.Config;
import com.modnakompanija.hci_modnakompanija.Helper.Http.ApiRezultat;
import com.modnakompanija.hci_modnakompanija.Helper.Http.HttpManager;
import com.modnakompanija.hci_modnakompanija.Helper.MyRunnable;
import com.modnakompanija.hci_modnakompanija.Model.ProizvodiVM;

import org.apache.http.message.BasicNameValuePair;

/**
 * Created by Arza on 1.11.2015.
 */
public class getProizvodi {


    public static void getProizvode(final Context context,final String vrstaId,final MyRunnable<ProizvodiVM> onSuccess) {


        final String url = Config.Url+ "VrsteProizvoda/ProizvodiByVrsta";



        new AsyncTask<Void, Void, ApiRezultat<ProizvodiVM>>() {
            @Override
            protected ApiRezultat<ProizvodiVM> doInBackground(Void... params) {
                return HttpManager.get(url, ProizvodiVM.class,
                        new BasicNameValuePair("vrstaId", vrstaId));
            }

            @Override
            protected void onPostExecute(ApiRezultat<ProizvodiVM> rezultat) {

                if (!rezultat.isError)
                    onSuccess.run(rezultat.value);
                else {
                    Toast.makeText(context, "Greška: " + rezultat.errorMessage, Toast.LENGTH_LONG).show();
                }
            }
        }.execute();
    }

    public static void NoviProizvod(final Context baseContext, final Object inputObject, final MyRunnable<ProizvodiVM.proizvodi> onSuccess)
    {

        final String url = Config.Url + "VrsteProizvoda/NoviProizvod";



        new AsyncTask<Void, Void, ApiRezultat<ProizvodiVM.proizvodi>>()
        {
            @Override
            protected ApiRezultat<ProizvodiVM.proizvodi> doInBackground(Void... params)
            {
                return HttpManager.post(url, ProizvodiVM.proizvodi.class, inputObject);
            }

            @Override
            protected void onPostExecute(ApiRezultat<ProizvodiVM.proizvodi> rezultat)
            {

                if (!rezultat.isError)
                    onSuccess.run(rezultat.value);
                else
                {
                    Toast.makeText(baseContext,"Greska neka "+rezultat.errorMessage,Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

}
