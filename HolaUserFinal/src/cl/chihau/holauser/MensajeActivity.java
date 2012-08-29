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
		
		// Relacionamos la parte l—gica (JAVA) con la interfaz
		// (XML), creamos un objeto de tipo TextView que va a 
		// estar enlazado con el TextView de mi mensaje.xml
		textoMensaje = (TextView) findViewById(R.id.TxtMensaje);
		
		// Creamos una copia del bundle que enviamos desde la 
		// otra actividad
		Bundle bundle = getIntent().getExtras();
		
		// Obtenemos el string que viene en el bundle
		// y lo guardamos en nombre
		String nombre = bundle.getString("NOMBRE");
		
		// Desplegamos el nombre en el TextView
		textoMensaje.setText("Hola " + nombre);
	}

}
