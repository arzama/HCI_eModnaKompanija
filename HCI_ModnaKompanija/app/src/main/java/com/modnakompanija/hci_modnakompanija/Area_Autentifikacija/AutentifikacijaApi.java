package com.modnakompanija.hci_modnakompanija.Area_Autentifikacija;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.modnakompanija.hci_modnakompanija.Helper.Http.ApiRezultat;
import com.modnakompanija.hci_modnakompanija.Helper.Http.HttpManager;
import com.modnakompanija.hci_modnakompanija.Helper.MyRunnable;
import com.modnakompanija.hci_modnakompanija.Model.KorisniciVM;

import org.apache.http.message.BasicNameValuePair;
/**
 * Created by Arza on 10.10.2015.
 */
public class AutentifikacijaApi {
    public static void Provjera2(final Context context, final String username, final String password, final MyRunnable<KorisniciVM> onSuccess)
    {

        final String url = "http://hci106.app.fit.ba/api/Autentifikacija/Provjera";
        final ProgressDialog dialog = new ProgressDialog(context);
        dialog.setTitle("Čitanje podataka je u toku");
        dialog.show();

        new AsyncTask<Void, Void, ApiRezultat<KorisniciVM>>()
        {
            @Override
            protected ApiRezultat<KorisniciVM> doInBackground(Void... params)
            {
                return HttpManager.get(url, KorisniciVM.class,
                        new BasicNameValuePair("username", username),
                        new BasicNameValuePair("password", password));
            }

            @Override
            protected void onPostExecute(ApiRezultat<KorisniciVM> rezultat)
            {
                dialog.dismiss();
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
