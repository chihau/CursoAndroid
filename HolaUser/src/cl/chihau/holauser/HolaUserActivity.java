package cl.chihau.holauser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class HolaUserActivity extends Activity {
	
	EditText campoNombre;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		
		campoNombre = (EditText) findViewById(R.id.TxtNombre);
	}
	
	public void saludar(View v) {
		
		String nombre = campoNombre.getText().toString();
		
		Bundle bundle = new Bundle();
		bundle.putString("NOMBRE", nombre);
		
		Intent intencion = new Intent(this, MensajeActivity.class);
		intencion.putExtras(bundle);
		
		startActivity(intencion);
	}

}
