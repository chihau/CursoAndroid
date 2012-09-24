package cl.chihau.mispaises;

import java.util.ArrayList;
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
		
		loadData();
		
		setContentView(R.layout.main);
		
		ListView lv = (ListView) findViewById(R.id.mylistview);
		
		adapter = new ArrayAdapter<String>(this, R.layout.list_item, countries);
		
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(this);
		
		Button btn = (Button) findViewById(R.id.mybutton);
		btn.setOnClickListener(this);
	}

	// MŽtodo que se ejecuta cuando seleccionamos un elemento de la lista
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
		Uri uri = Uri.parse("http://en.wikipedia.org/wiki/" + Uri.encode(countries.get(pos), null));
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}

	// MŽtodo que se ejecuta cuando presionamos el bot—n "Agregar"
	@Override
	public void onClick(View arg0) {
		EditText et = (EditText) findViewById(R.id.mytextview);
		
		if(countries.contains(et.getText().toString())) {
			Toast.makeText(this, "Este pa’s ya existe en la lista", Toast.LENGTH_LONG).show();
		} else {
			countries.add(et.getText().toString());
			adapter.notifyDataSetChanged();
			saveData();
		}
		
		et.setText("");
	}
	
	// MŽtodo para cargar los pa’ses guardados en memoria
	void loadData() {
		SharedPreferences sp = getPreferences(MODE_PRIVATE);
		String countryList = sp.getString("countries","Argentina;Brasil;Bolivia;Chile;Perœ");
		for (String country : countryList.split(";")) 
			countries.add(country);
	}
	
	// MŽtodo para guardar los pa’ses en memoria
	void saveData() {
		SharedPreferences.Editor spe = getPreferences(MODE_PRIVATE).edit();
		
		StringBuilder sb = new StringBuilder();
		int i;
	    for (i = 0; i < countries.size(); i++)
	        sb.append( ((i == 0) ? "" : ";") + countries.get(i));
	    
	    spe.putString("countries", sb.toString());
	    spe.commit();
	}

}
