package com.example.miptpraktikosdarbas5.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.miptpraktikosdarbas5.Utils.Constants.EUROPA_API_URL;
import static com.example.miptpraktikosdarbas5.Utils.Constants.FLOATRATES_API_URL;
import static com.example.miptpraktikosdarbas5.Utils.Constants.METEO_API_URL;

import android.util.Log;

import com.example.miptpraktikosdarbas5.Parsers.EuropaXML;
import com.example.miptpraktikosdarbas5.Parsers.FloatRatesXML;
import com.example.miptpraktikosdarbas5.Parsers.MeteoJSON;

public class ApiDataReader {
    public static String getValuesFromApi(String apiCode) throws IOException {
        Log.d("ApiDataReader", "getValuesFromApi Method called!");

        InputStream apiContentStream = null;
        String result = "";
        try {
            switch (apiCode) {
                case EUROPA_API_URL:
                    apiContentStream = downloadUrlContent(EUROPA_API_URL);
                    result = EuropaXML.getCurrencyRates(apiContentStream);
                    break;
                case FLOATRATES_API_URL:
                    apiContentStream = downloadUrlContent(FLOATRATES_API_URL);
                    result = FloatRatesXML.getCurrencyRates(apiContentStream);
                    break;
                case METEO_API_URL:
                    apiContentStream = downloadUrlContent(METEO_API_URL);
                    result = MeteoJSON.getWeatherForecast(apiContentStream);
                    break;
                default:
            }
        } finally {
            if (apiContentStream != null) {
                apiContentStream.close();
            }
        }
        return result;
    }

    //Routine that creates and calls GET request to web page
    private static InputStream downloadUrlContent(String urlString) throws IOException {
        Log.d("ApiDataReader", "downloadUrlContent Method called!");

        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();

            return conn.getInputStream();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new IOException("Invalid URL: " + urlString);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}

