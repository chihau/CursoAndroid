package cl.chihau.holamenus;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class HolaMenusActivity extends Activity {
	
	TextView lblMensaje;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		
		lblMensaje = (TextView) findViewById(R.id.mensaje);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_principal, menu);
		return true;
	}
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MnuOpc1:
                lblMensaje.setText("Opci—n 1 pulsada!");
                return true;
            case R.id.MnuOpc2:
                lblMensaje.setText("Opci—n 2 pulsada!");;
                return true;
            case R.id.MnuOpc3:
                lblMensaje.setText("Opci—n 3 pulsada!");;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
	
	    
}
