package cl.chihau.holauser;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class HolaUserActivity extends Activity {
	
	EditText campoNombre;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		
		/*
		 * Relacionamos el campo de texto TxtNombre perteneciente al 
		 * main.xml con un objeto de tipo EditText llamado txtNombre
		 */
		campoNombre = (EditText) findViewById(R.id.TxtNombre);
	}
	
}
