package cl.chihau.holauser;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class HolaUserActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		
		/* 
		 * Relacionamos el bot—n BtnHola perteneciente al main.xml
		 * con un objeto de tipo Button llamado btnHola
		 */
		final Button btnHola = (Button) findViewById(R.id.BtnHola);
		
		/*
		 * Relacionamos el campo de texto TxtNombre perteneciente al 
		 * main.xml con un objeto de tipo EditText llamado txtNombre
		 */
		final EditText txtNombre = (EditText) findViewById(R.id.TxtNombre);
		
		
	}
}
