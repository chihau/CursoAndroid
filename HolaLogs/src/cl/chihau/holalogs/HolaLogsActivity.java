package cl.chihau.holalogs;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class HolaLogsActivity extends Activity {
	
	private static final String LOGTAG = "HolaLogs";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		
        Log.e(LOGTAG, "Mensaje de error");
        Log.w(LOGTAG, "Mensaje de warning");
        Log.i(LOGTAG, "Mensaje de informaci—n");
        Log.d(LOGTAG, "Mensaje de depuraci—n");
        Log.v(LOGTAG, "Mensaje de verbose");
	}
}
