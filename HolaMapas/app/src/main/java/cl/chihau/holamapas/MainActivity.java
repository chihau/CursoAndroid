package cl.chihau.holamapas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoogleMap mapa = null;

        if (mapa == null) {
            mapa = ((SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map)).getMap();

            if (mapa != null) {
                // Agregamos una capa simple de localización (MyLocation Layer)
                // Para hacer algo más preciso y personalizable utilizar la
                // Location API
                // http://developer.android.com/google/play-services/location.html
                mapa.setMyLocationEnabled(true);

                mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);

                mapa.addMarker(new MarkerOptions()
                                .position(new LatLng(-33.036652, -71.595088))
                                .title("Entrada")
                                .draggable(true)
                );
            } else {
                Log.e("HelloMaps", "No están disponibles los mapas");
            }
        }
    }
}
