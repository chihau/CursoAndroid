package cl.chihau.mispaises;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MisPaisesActivity extends AppCompatActivity
        implements View.OnClickListener, AdapterView.OnItemClickListener {

    List countries = new ArrayList();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ListView lv = (ListView) findViewById(R.id.mylistview);

        loadData();

        adapter = new ArrayAdapter(this, R.layout.list_item, countries);

        lv.setAdapter(adapter);

        Button btn = (Button) findViewById(R.id.mybutton);
        btn.setOnClickListener(this);

        lv.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View view) {
        EditText et = (EditText) findViewById(R.id.myedittext);
        if (et.getText().toString().length() > 0) {
            if (countries.contains(et.getText().toString())) {
                Toast.makeText(this, "Este país ya existe en la lista", Toast.LENGTH_LONG).show();
            } else {
                String country = et.getText().toString();
                countries.add(country.substring(0, 1).toUpperCase() +
                        country.substring(1, country.length()).toLowerCase());
                Collections.sort(countries);
                adapter.notifyDataSetChanged();
                et.setText("");
                saveData();
            }
        } else {
            Toast.makeText(this, "El campo está vacío", Toast.LENGTH_LONG).show();
        }
    }

    private void saveData() {
        SharedPreferences.Editor spe = getPreferences(MODE_PRIVATE).edit();

        StringBuilder sb = new StringBuilder();
        int i;
        for (i = 0; i < countries.size(); i++) {
            sb.append( ((i == 0) ? "" : ";")  + countries.get(i));
        }

        spe.putString("countries", sb.toString());
        spe.commit();
    }

    private void loadData() {
        SharedPreferences sp = getPreferences(MODE_PRIVATE);
        String countryList = sp.getString("countries",
                "Argentina;Brasil;Bolivia;Chile;Perú");

        for (String country : countryList.split(";")) {
            countries.add(country);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
        Uri uri = Uri.parse("http://en.wikipedia.org/wiki/" +
                Uri.encode(countries.get(pos).toString()));

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}
