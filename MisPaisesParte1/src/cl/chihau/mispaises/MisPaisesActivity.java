package cl.chihau.mispaises;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MisPaisesActivity extends Activity implements OnItemClickListener {
	
	static final String[] COUNTRIES = new String[] {"Argentina", "Brasil", "Bolivia", "Chile", "Perœ"};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		
		ListView lv = (ListView) findViewById(R.id.mylistview);
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item, COUNTRIES);
		
		lv.setAdapter(adapter);
		
		lv.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

}
