package cl.chihau.mispaises;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
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
		
		countries.add("Argentina");
		countries.add("Brasil");
		countries.add("Bolivia");
		countries.add("Chile");
		countries.add("Perœ");
		
		setContentView(R.layout.main);
		
		ListView lv = (ListView) findViewById(R.id.mylistview);
		
		adapter = new ArrayAdapter<String>(this, R.layout.list_item, countries);
		
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(this);
		
		Button btn = (Button) findViewById(R.id.mybutton);
		btn.setOnClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
		Uri uri = Uri.parse("http://en.wikipedia.org/wiki/" + Uri.encode(countries.get(pos), null));
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
	}

	@Override
	public void onClick(View arg0) {
		EditText et = (EditText) findViewById(R.id.mytextview);
		
		if(countries.contains(et.getText().toString())) {
			Toast.makeText(this, "Este pa’s ya existe en la lista", Toast.LENGTH_LONG).show();
		} else {
			countries.add(et.getText().toString());
			adapter.notifyDataSetChanged();
		}
		
		et.setText("");
	}

}
