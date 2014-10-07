package cl.chihau.holauser;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MensajeActivity extends Activity {
	
	TextView textoMensaje;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.mensaje);
		
		textoMensaje = (TextView) findViewById(R.id.TxtMensaje);
		
		Bundle bundle = getIntent().getExtras();
		String nombre = bundle.getString("NOMBRE");
		
		textoMensaje.setText("Hola " + nombre);
	}

}
