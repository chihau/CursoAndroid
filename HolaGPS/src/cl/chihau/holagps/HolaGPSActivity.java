package cl.chihau.holagps;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class HolaGPSActivity extends Activity {
	
	private TextView tvLatitud; 
	private TextView tvLongitud;
	
	private LocationManager locManager;
	private LocationListener locListener;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		
	    tvLatitud = (TextView)findViewById(R.id.tv_latitud);
	    tvLongitud = (TextView)findViewById(R.id.tv_longitud);
	    
    	//Creamos un objeto para obtener una referencia al LocationManager
        locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        
    	//Obtenemos y mostramos la última posición conocida
        Location loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        modificarLatLong(loc);
        
        obtenerLocalicacion();
	}
	
	private void obtenerLocalicacion()
    {	
    	//Creamos un Listener para escuchar la ubicación
    	locListener = new LocationListener() {
    		
    		//Cuando cambia la posición del proveedor
	    	public void onLocationChanged(Location location) {
	    		modificarLatLong(location);
	    	}
	    	
	    	//Cuando el proveedor está deshabilitado
	    	public void onProviderDisabled(String provider){
	    		Log.i("HolaGPS", "Estado del GPS: OFF");
	    	}
	    	
	    	//Cuando el proveedor está habilitado
	    	public void onProviderEnabled(String provider){
	    		Log.i("HolaGPS", "Estado del GPS: ON");
	    	}
	    	
	    	//Cuando el proveedor cambia de estado
	    	//Status: 0 OUT_OF_SERVICE, 1 TEMPORARILY_UNAVAILABLE, 2 AVAILABLE.
	    	public void onStatusChanged(String provider, int status, Bundle extras){
	    		Log.i("HelloGPS", "Estado del GPS: " + status);
	    	}
    	};
    	
    	//Le indicamos al LocationManager que las actualizaciones del GPS se consulten
    	//cada 1 segundos, sin inportar si la distancia a variado y le indicamos el listener
    	locManager.requestLocationUpdates(
    			LocationManager.GPS_PROVIDER, 1000, 0, locListener);
    }
	
	private void modificarLatLong(Location loc) {
    	if(loc != null)
    	{
    		tvLongitud.setText("Longitud: " + String.valueOf(loc.getLongitude()));
    		tvLatitud.setText("Latitud: " + String.valueOf(loc.getLatitude()));
    		Log.i("HolaGPS", String.valueOf("long: " + String.valueOf(loc.getLongitude())) 
    				+ " - " + "lat: " + loc.getLatitude());
    	} else {
    		tvLongitud.setText("Longitud: Indeterminada");
    		tvLatitud.setText("Latitud: Indeterminada");
    		Toast.makeText(this, "No se puede determinar posición", Toast.LENGTH_LONG).show();
    	}
    }
}
