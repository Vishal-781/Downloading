package com.example.downloading;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public class DownoadTask extends AsyncTask<String,Void,String>
    {


        @Override
        protected String doInBackground(String... strings) {

            Log.i("output",strings[0]);

            String result = "";
            URL url;
            HttpURLConnection urlConnection=null;
            try {
                url = new URL(strings[0]); //Inside catch as all strings cant converted into strings
                 urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();
                while(data != -1)
                {
                    char current =(char) data;
                    result +=current;

                    data =reader.read();
                }
           return result;

            }
            catch (Exception e)
            {
                e.printStackTrace();
                return "Failed";
            }


        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DownoadTask task = new DownoadTask();
        String result = null;
        try {
            result = task.execute("https://www.ecowebhosting.co.uk/").get();

        } catch (ExecutionException e) {

            e.printStackTrace();

        } catch (InterruptedException e) {

            e.printStackTrace();

        }
        Log.i("Contents of Url",result);

    }
}