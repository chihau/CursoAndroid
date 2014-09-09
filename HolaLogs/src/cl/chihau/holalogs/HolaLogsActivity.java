package cl.chihau.holalogs;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class HolaLogsActivity extends Activity {
	public final static String LOGTAG = "HolaLogsActivity"; 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		
		Log.e(LOGTAG, "Mensaje de error");
        Log.w(LOGTAG, "Mensaje de advertencia");
        Log.i(LOGTAG, "Mensaje de información");
        Log.d(LOGTAG, "Mensaje de depuración");
        Log.v(LOGTAG, "Mensaje estándares");
       
	}

}
