package cl.chihau.holalinearlayout;

import android.app.Activity;
import android.os.Bundle;

public class HolaLinearLayoutActivity extends Activity{
	
	/** Llamado cuando la actividad se inicia por primera vez*/
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Para cargar un archivo main.xml con la interfaz
		setContentView(R.layout.main);
	}
	
}
