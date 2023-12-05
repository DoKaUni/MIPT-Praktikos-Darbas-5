package com.example.miptpraktikosdarbas5;

import static com.example.miptpraktikosdarbas5.Utils.Constants.EUROPA_API_URL;
import static com.example.miptpraktikosdarbas5.Utils.Constants.FLOATRATES_API_URL;
import static com.example.miptpraktikosdarbas5.Utils.Constants.METEO_API_URL;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import com.example.miptpraktikosdarbas5.Utils.AsyncDataLoader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editTextCurrencyFilter;
    private ArrayAdapter<String> listAdapter;
    private List<String> originalData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCurrencyFilter = findViewById(R.id.editTextCurrencyFilter);
        Spinner apiSpinner = findViewById(R.id.apiSpinner);
        ListView listViewItems = findViewById(R.id.listViewItems);

        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<>());
        listViewItems.setAdapter(listAdapter);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.api_options,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        apiSpinner.setAdapter(adapter);
        apiSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Log.d("MainActivity", "onItemSelected Method called!");

                String selectedApi = parentView.getItemAtPosition(position).toString();
                switch (selectedApi) {
                    case "Europa exchange":
                        editTextCurrencyFilter.setVisibility(View.VISIBLE);
                        loadData(EUROPA_API_URL);
                        break;
                    case "FloatRates exchange":
                        editTextCurrencyFilter.setVisibility(View.VISIBLE);
                        loadData(FLOATRATES_API_URL);
                        break;
                    case "Meteo forecast":
                        editTextCurrencyFilter.setVisibility(View.GONE);
                        loadData(METEO_API_URL);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        editTextCurrencyFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                Log.d("MainActivity", "onTextChanged Method called!");

                filterData(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    private void loadData(String selectedApi) {
        Log.d("MainActivity", "loadData Method called!");

        new AsyncDataLoader() {
            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                originalData = Arrays.asList(result.split("\n"));
                listAdapter.clear();
                listAdapter.addAll(originalData);
            }
        }.execute(selectedApi);
    }

    private void filterData(String filterText) {
        Log.d("MainActivity", "filterData Method called!");

        List<String> filteredData = new ArrayList<>();
        if (originalData != null) {
            for (String item : originalData) {
                if (item.contains(filterText.toUpperCase())) {
                    filteredData.add(item);
                }
            }
        }
        listAdapter.clear();
        listAdapter.addAll(filteredData);
    }
}
