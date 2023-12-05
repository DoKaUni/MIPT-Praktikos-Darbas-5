package com.example.miptpraktikosdarbas5.Utils;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;

public class AsyncDataLoader extends AsyncTask<String, Void, String> {

    protected String doInBackground(String... params) {
        Log.d("AsyncDataLoader", "doInBackground Method called!");

        try {
            return ApiDataReader.getValuesFromApi(params[0]);
        } catch (IOException ex) {
            return String.format("Some error occured => %s", ex.getMessage());
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Log.d("AsyncDataLoader", "onPostExecute Method called!");
        super.onPostExecute(result);
    }
}
