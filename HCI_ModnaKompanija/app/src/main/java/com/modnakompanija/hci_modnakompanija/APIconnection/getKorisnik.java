package com.modnakompanija.hci_modnakompanija.APIconnection;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;


import com.modnakompanija.hci_modnakompanija.Helper.Config;
import com.modnakompanija.hci_modnakompanija.Helper.Http.ApiRezultat;
import com.modnakompanija.hci_modnakompanija.Helper.Http.HttpManager;
import com.modnakompanija.hci_modnakompanija.Helper.MyRunnable;
import com.modnakompanija.hci_modnakompanija.Model.KorisniciVM;

/**
 * Created by Arza on 1.11.2015.
 */
public class getKorisnik {
    public static void IzmjenaPodataka(final Context baseContext, final Object inputObject, final MyRunnable<KorisniciVM> onSuccess)
    {


        final String url = Config.Url + "Korisnik/IzmjeniPodatke";



        new AsyncTask<Void, Void, ApiRezultat<KorisniciVM>>()
        {
            @Override
            protected ApiRezultat<KorisniciVM> doInBackground(Void... params)
            {
                return HttpManager.post(url, KorisniciVM.class, inputObject);
            }

            @Override
            protected void onPostExecute(ApiRezultat<KorisniciVM> rezultat)
            {

                if (!rezultat.isError)
                    onSuccess.run(rezultat.value);
                else
                {
                    Toast.makeText(baseContext, "Greska neka " + rezultat.errorMessage, Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }


    public static void Registracija(final Context baseContext, final Object inputObject, final MyRunnable<KorisniciVM> onSuccess)
    {

        final String url = Config.Url + "Korisnik/NoviKorisnik";



        new AsyncTask<Void, Void, ApiRezultat<KorisniciVM>>()
        {
            @Override
            protected ApiRezultat<KorisniciVM> doInBackground(Void... params)
            {
                return HttpManager.post(url, KorisniciVM.class, inputObject);
            }

            @Override
            protected void onPostExecute(ApiRezultat<KorisniciVM> rezultat)
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
