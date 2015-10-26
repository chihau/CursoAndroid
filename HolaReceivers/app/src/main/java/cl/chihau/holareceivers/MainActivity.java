package cl.chihau.holareceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "HolaReceivers";

    TextView tvNetwork;
    TextView tvBattery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvNetwork = (TextView) findViewById(R.id.tv_network);
        tvBattery = (TextView) findViewById(R.id.tv_battery);
    }

    @Override
    public void onResume() {
        super.onResume();

        registerReceivers(true);
    }

    @Override
    public void onPause() {
        super.onPause();

        registerReceivers(false);
    }

    private BroadcastReceiver netStatusReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkNetStatus();
        }
    };

    private BroadcastReceiver batteryStatusReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            checkBatteryStatus(intent);
        }
    };

    private void registerReceivers(boolean encender) {
        if (encender) {
            registerReceiver(netStatusReceiver, new IntentFilter(
                    ConnectivityManager.CONNECTIVITY_ACTION));

            // Intent.ACTION_BATTERY_CHANGED es un sticky intent
            registerReceiver(batteryStatusReceiver, new IntentFilter(
                    Intent.ACTION_BATTERY_CHANGED));
            Log.d(TAG, "Broadcast Receivers ON");
        } else {
            unregisterReceiver(netStatusReceiver);
            unregisterReceiver(batteryStatusReceiver);
            Log.d(TAG, "Broadcast Receivers OFF");
        }
    }

    private void checkNetStatus() {
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();

        if (isConnected) {
            if (activeNetwork.getTypeName().equals("WIFI")) {
                tvNetwork.setText("Estado de la Red: conectado por Red Wifi");
                //Hay teléfonos que devuelven "mobile" en mayúsculas y en minúsculas
            } else if (activeNetwork.getTypeName().toUpperCase().equals("MOBILE")) {
                tvNetwork.setText("Estado de la Red: Conectado por Red Móvil");
            }
            tvNetwork.setTextColor(Color.BLACK);
        } else {
            tvNetwork.setText("Estado de la Red: desconectado");
            tvNetwork.setTextColor(Color.RED);
        }

    }

    private void checkBatteryStatus(Intent intent) {
        int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;

        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);

        if (isCharging) {
            tvBattery.setText("Estado de la Batería: cargando con " + level + "%");
            tvBattery.setTextColor(Color.BLACK);
        } else {
            tvBattery.setText("Estado de la Batería: descargando con " + level + "%");
            tvBattery.setTextColor(Color.RED);
        }
    }
}
