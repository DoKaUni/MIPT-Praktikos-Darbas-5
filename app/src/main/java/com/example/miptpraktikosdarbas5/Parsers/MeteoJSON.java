package com.example.miptpraktikosdarbas5.Parsers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MeteoJSON {
    public static String getWeatherForecast(InputStream stream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        String line = "";
        StringBuilder data = new StringBuilder();
        while (line != null) {
            line = bufferedReader.readLine();
            data.append(line);
        }

        StringBuilder result = new StringBuilder();
        try {
            JSONObject jData = new JSONObject(data.toString());

            JSONObject placeNode = jData.getJSONObject("place");
            String placeName = placeNode.getString("name");
            JSONObject coordinatesNodes = placeNode.getJSONObject("coordinates");
            String latitude = coordinatesNodes.getString("latitude");
            String longitude = coordinatesNodes.getString("longitude");
            result.append(String.format("Location name: %s, latitude: %s, longitude: %s \n", placeName, latitude, longitude));

            JSONArray timeStampArray = jData.getJSONArray("forecastTimestamps");
            for (int i = 0; i <= 24; i++) {
                JSONObject currentTimeStamp = timeStampArray.getJSONObject(i);
                String forecastTime = currentTimeStamp.getString("forecastTimeUtc");
                String airTemperature = currentTimeStamp.getString("airTemperature");
                String feelTemperature = currentTimeStamp.getString("feelsLikeTemperature");
                String conditionCode = currentTimeStamp.getString("conditionCode");
                result.append(String.format("Forecast time: %s, condition: %s, temperature: %s, feels like: %s\n", forecastTime, conditionCode, airTemperature, feelTemperature));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result.toString();
    }
}
