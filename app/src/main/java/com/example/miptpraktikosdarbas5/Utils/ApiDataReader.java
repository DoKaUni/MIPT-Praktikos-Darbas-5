package com.example.miptpraktikosdarbas5.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.example.miptpraktikosdarbas5.Utils.Constants.EUROPA_API_URL;
import static com.example.miptpraktikosdarbas5.Utils.Constants.FLOATRATES_API_URL;
import static com.example.miptpraktikosdarbas5.Utils.Constants.METEO_API_URL;

public class ApiDataReader {
    public static String getValuesFromApi(String apiCode) throws IOException {
        InputStream apiContentStream = null;
        String result = "";
        try {
            switch (apiCode) {
                case EUROPA_API_URL:
                    apiContentStream = downloadUrlContent(EUROPA_API_URL);
                    //result = <!-- TODO: Add Europa Parser Call when the parser is implemented.
                    break;
                case FLOATRATES_API_URL:
                    apiContentStream = downloadUrlContent(FLOATRATES_API_URL);
                    //result = <!-- TODO: Add FloatRates Parser Call when the parser is implemented.
                    break;
                case METEO_API_URL:
                    apiContentStream = downloadUrlContent(METEO_API_URL);
                    //result = <!-- TODO: Add Meteo Parser Call when the parser is implemented.
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

