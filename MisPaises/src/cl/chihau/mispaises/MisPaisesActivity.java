package cl.chihau.mispaises;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MisPaisesActivity extends Activity implements OnItemClickListener, OnClickListener {
	
	List<String> countries = new ArrayList<String>();
	ArrayAdapter<String> adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		
		ListView lv = (ListView) findViewById(R.id.mylistview);
		
		loadData();
		
		adapter = new ArrayAdapter<String>(this, R.layout.list_item, countries);
		
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(this);
		
		Button btn = (Button) findViewById(R.id.mybutton);
		btn.setOnClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
		Uri uri = Uri.parse("http://en.wikipedia.org/wiki/" + Uri.encode(countries.get(pos)));
		
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}

	@Override
	public void onClick(View v) {
		EditText et = (EditText) findViewById(R.id.mytextview);
		
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

	private void loadData() {
		SharedPreferences sp = getPreferences(MODE_PRIVATE);
		String countryList = sp.getString("countries", "Argentina;Brasil;Bolivia;Chile;Perú");
		
		for (String country : countryList.split(";")) {
			countries.add(country);
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
}
