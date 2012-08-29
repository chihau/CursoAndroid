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
		
		/*
		 * Relacionamos el campo de texto TxtNombre perteneciente al 
		 * main.xml con un objeto de tipo EditText llamado txtNombre
		 */
		campoNombre = (EditText) findViewById(R.id.TxtNombre);
	}
	
	/*
	 * Este método se ejecuta cada vez que hacemos click sobre
	 * el botón
	 */
	public void saludar(View view) {
		//Obtenemos el nombre desde el campo de texto
		//y lo guardamos en un string
		String nombre = campoNombre.getText().toString();
		
		//Creamos un bundle para meter dentro el nombre
		//El bundle nos permite pasar objetos de una 
		//actividad a otra
		Bundle bundle = new Bundle();
		bundle.putString("NOMBRE", nombre);
		
		//Creamos una intención que es saltar de esta actividad
		//(HolaUserActivity) a la otra actividad (MensajeActivity)
		Intent intencion = new Intent(this, MensajeActivity.class);
		
		//Metemos nuestro bundle dentro de la intención
		intencion.putExtras(bundle);
		
		//Ejecutamos nuestra intención
		startActivity(intencion);
	}
	
}
