package com.modnakompanija.hci_modnakompanija.APIconnection;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.modnakompanija.hci_modnakompanija.Helper.Config;
import com.modnakompanija.hci_modnakompanija.Helper.Http.ApiRezultat;
import com.modnakompanija.hci_modnakompanija.Helper.Http.HttpManager;
import com.modnakompanija.hci_modnakompanija.Helper.MyRunnable;
import com.modnakompanija.hci_modnakompanija.Model.VrsteProizvodaVM;
/**
 * Created by Arza on 1.11.2015.
*/

public class getVrsteProizvoda {
 public static void getVrste(final Context context,final MyRunnable<VrsteProizvodaVM> onSuccess)
 {



final  String url= Config.Url +"VrsteProizvoda";

        new AsyncTask<Void, Void, ApiRezultat<VrsteProizvodaVM>>()
        {
@Override
protected ApiRezultat<VrsteProizvodaVM> doInBackground(Void... params)
        {
        return HttpManager.get(url, VrsteProizvodaVM.class);
        }

@Override
protected void onPostExecute(ApiRezultat<VrsteProizvodaVM> rezultat)
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
