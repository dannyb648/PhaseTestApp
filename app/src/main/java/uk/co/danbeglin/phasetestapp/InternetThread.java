package uk.co.danbeglin.phasetestapp;

import android.os.AsyncTask;
import android.util.Log;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by danny on 29/04/16.
 */
public class InternetThread extends AsyncTask<String, Void, String> {

    public String doInBackground(String... urlToRead){
        //String Builder object
        StringBuilder result = new StringBuilder();
        //Try catch to avoid a crash
        try {

            //new URL from our top score.
            URL url = new URL(urlToRead[0]);
            //HTTP connection object
            HttpURLConnection cn = (HttpURLConnection) url.openConnection();
            //set up HTTP request
            cn.setRequestMethod("GET");
            //Parse JSON file badly
            BufferedReader rd = new BufferedReader(new InputStreamReader(cn.getInputStream()));

            String line;
            //While sting isnt empty...
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            //Close
            rd.close();
        } catch (Exception E) {

        }
        //pass back the result, as a String
        return result.toString();
    }

    protected void onPostExecute(String url) {
        //This is part of AsyncTask, so I need it, but I just need a debug.
        Log.d("DEBUG", "THIS HAS RUN!");
    }
}

