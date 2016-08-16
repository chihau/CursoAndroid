package cl.chihau.holamapas;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    GoogleMap mapa = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (mapa == null) {
            SupportMapFragment smf = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            smf.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mapa = googleMap;

        if (mapa != null) {
            mapa.setMyLocationEnabled(true);

            mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);

            mapa.addMarker(new MarkerOptions()
                            .position(new LatLng(-33.036652, -71.595088))
                            .title("Entrada")
                            .draggable(true)
            );

        } else {
            Log.e("HolaMapas", "mapa nulo");
        }
    }
}
