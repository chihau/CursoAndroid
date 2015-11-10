package cl.chihau.holaintentservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ProgressBar barraProgreso;
    private TextView textoPorcentaje;

    Intent msgIntent;
    ProgressReceiver prcv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        barraProgreso = (ProgressBar) findViewById(R.id.barraProgreso);
        textoPorcentaje = (TextView) findViewById(R.id.textoPorcentaje);

        prcv = new ProgressReceiver();

        IntentFilter filter = new IntentFilter();
        filter.addAction("ACTION_PROGRESO");
        filter.addAction("ACTION_DETENER");
        filter.addAction("ACTION_FIN");

        registerReceiver(prcv, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(prcv);
    }

    public void ejecutarTarea(View view) {
        msgIntent = new Intent(MainActivity.this, ServicioDescarga.class);
        msgIntent.putExtra("iteraciones", 10);
        startService(msgIntent);
    }

    public void detenerTarea(View view) {
        if (msgIntent != null) {
            stopService(msgIntent);
            msgIntent = null;
        }
    }

    public class ProgressReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("ACTION_PROGRESO")) {
                int prog = intent.getIntExtra("progreso", 0);
                barraProgreso.setProgress(prog);
                textoPorcentaje.setText("Porcentaje de descarga: " + prog + "%");
            } else if (intent.getAction().equals("ACTION_DETENER")) {
                Toast.makeText(MainActivity.this, "Descarga detenida!",
                        Toast.LENGTH_SHORT).show();
            } else if (intent.getAction().equals("ACTION_FIN")) {
                Toast.makeText(MainActivity.this, "Descarga finalizada!",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }
}
