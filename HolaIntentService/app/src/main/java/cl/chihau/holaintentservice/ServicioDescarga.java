package cl.chihau.holaintentservice;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by chihau on 09-11-15.
 */
public class ServicioDescarga extends IntentService {
    boolean stopped;

    public ServicioDescarga() {
        super("ServicioDescarga");
        stopped = false;
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Intent bcIntent = new Intent();

        int iter = intent.getIntExtra("iteraciones", 0);

        for(int i=1; i<=iter; i++) {
            tareaLarga();

            if(stopped) {
                break;
            }

            //Informamos sobre el progreso a la actividad
            bcIntent.setAction("ACTION_PROGRESO");
            bcIntent.putExtra("progreso", i*100/iter);
            sendBroadcast(bcIntent);
        }

        if(stopped) {
            bcIntent.setAction("ACTION_DETENER");
        } else {
            bcIntent.setAction("ACTION_FIN");
        }
        sendBroadcast(bcIntent);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopped = true;
    }

    private void tareaLarga() {
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e) {
            Log.e("HelloIntentService", "No se puede descargar el archivo");
        }
    }
}
