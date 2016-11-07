package com.modnakompanija.hci_modnakompanija.APIconnection;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.modnakompanija.hci_modnakompanija.Helper.Http.ApiRezultat;
import com.modnakompanija.hci_modnakompanija.Helper.Http.HttpManager;
import com.modnakompanija.hci_modnakompanija.Helper.MyRunnable;
import com.modnakompanija.hci_modnakompanija.Model.MaterijaliVM;

/**
 * Created by Arza on 19.11.2015.
 */
public class getMaterijali {
    public static void getMaterijale(final Context context,final MyRunnable<MaterijaliVM> onSuccess) {


        final String url = "http://hci106.app.fit.ba/api/Materijal/GetMaterijale";



        new AsyncTask<Void, Void, ApiRezultat<MaterijaliVM>>() {
            @Override
            protected ApiRezultat<MaterijaliVM> doInBackground(Void... params) {
                return HttpManager.get(url, MaterijaliVM.class );
            }

            @Override
            protected void onPostExecute(ApiRezultat<MaterijaliVM> rezultat) {

                if (!rezultat.isError)
                    onSuccess.run(rezultat.value);
                else {
                    Toast.makeText(context, "Greška: " + rezultat.errorMessage, Toast.LENGTH_LONG).show();
                }
            }
        }.execute();
    }
}
