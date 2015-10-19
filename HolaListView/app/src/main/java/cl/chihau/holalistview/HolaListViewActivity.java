package cl.chihau.holalistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HolaListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        String[] countries = getResources().getStringArray(R.array.countries_array);

        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this, R.layout.list_item, countries);

        ListView lv = (ListView) findViewById(R.id.lv);

        lv.setAdapter(adaptador);
    }
}
