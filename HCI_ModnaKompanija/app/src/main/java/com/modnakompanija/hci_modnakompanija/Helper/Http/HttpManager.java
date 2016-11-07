package com.modnakompanija.hci_modnakompanija.Helper.Http;

import android.util.Log;

import com.modnakompanija.hci_modnakompanija.Helper.MyGson;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPInputStream;

/**
 * Created by Arza on 10.10.2015.
 */
public class HttpManager {
    public static <T> ApiRezultat<T> get(String urlStr, Class<T> outputType,NameValuePair...inputParams)
    {
        ApiRezultat<T> rezultat = new ApiRezultat<>();

        HttpURLConnection urlConnection=null;
        try
        {

            String parametri=new String();
            if(inputParams.length>0){

                for (int i=0;i<inputParams.length;i++)
                    parametri+=inputParams[i].getValue()+"/";
            }


            URL url = new URL(urlStr + "/" + parametri);
            urlConnection = (HttpURLConnection) url.openConnection();


            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("Accept-Encoding", "gzip");

            int responseCode = urlConnection.getResponseCode();

            if(responseCode == HttpStatus.SC_OK)
            {
                InputStream InputStream = new BufferedInputStream(urlConnection.getInputStream());

                String sHeaderValue = urlConnection.getHeaderField("Content-Encoding");
                if (sHeaderValue != null && sHeaderValue.equalsIgnoreCase("gzip"))
                {
                    InputStream = new GZIPInputStream(InputStream);
                }

                if (outputType != null)
                {
                    String strJson = convertStreamToString(InputStream);
                    T t = MyGson.build().fromJson(strJson, outputType);

                    rezultat.value = t;
                    Log.i("HttpManager", strJson);
                }
            }else{
                throw  new Exception("Response code: "+ responseCode);
            }

            urlConnection.disconnect();
        }
        catch (Exception e)
        {
            Log.e("HttpManager", urlStr + ": " + e.getMessage());
            rezultat.errorMessage = e.getMessage();
            rezultat.isError = true;
        }
        finally
        {
            if (urlConnection != null)
                urlConnection.disconnect();
        }
        return rezultat;
    }

    public static <T> ApiRezultat<T> post(String urlStr, Class<T> outputType, final Object inputObject)
    {
        String strJsonInput = MyGson.build().toJson(inputObject);

        ApiRezultat<T> rezultat = new ApiRezultat<>();
        try
        {
            URL url = new URL(urlStr);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");

            //add request header
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("Content-type", "application/json");
            urlConnection.setRequestProperty("Accept-Encoding", "gzip");

            // Send post request
            urlConnection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(urlConnection.getOutputStream());
            wr.writeBytes(strJsonInput);
            wr.flush();
            wr.close();

            // call
            int responseCode = urlConnection.getResponseCode();

            if(responseCode == HttpStatus.SC_OK)
            {
                InputStream InputStream = new BufferedInputStream(urlConnection.getInputStream());

                String sHeaderValue = urlConnection.getHeaderField("Content-Encoding");
                if (sHeaderValue != null && sHeaderValue.equalsIgnoreCase("gzip"))
                {
                    InputStream = new GZIPInputStream(InputStream);
                }

                if (outputType != null)
                {
                    String strJson = convertStreamToString(InputStream);
                    T t = MyGson.build().fromJson(strJson, outputType);

                    rezultat.value = t;
                    Log.i("HttpManager", strJson);
                }
            }else{

                throw new Exception("responseCode " + responseCode);
            }
            urlConnection.disconnect();
        }
        catch (Exception e)
        {
            Log.e("HttpManager", urlStr + ": " + e.getMessage());
            rezultat.errorMessage = e.getMessage();
            rezultat.isError = true;
        }
        return rezultat;
    }


    public static String convertStreamToString(InputStream inputStream) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        while ((line = bufferedReader.readLine()) != null)
        {
            stringBuilder.append(line + "\n");
        }
        return stringBuilder.toString();


    }

}
