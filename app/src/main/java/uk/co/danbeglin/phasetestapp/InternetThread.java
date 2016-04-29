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
        StringBuilder result = new StringBuilder();
        try {

            URL url = new URL(urlToRead[0]);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rd.close();
        } catch (Exception E) {

        }
        return result.toString();
    }

    protected void onPostExecute(String url) {
        Log.d("DEBUG", url);
    }
}

