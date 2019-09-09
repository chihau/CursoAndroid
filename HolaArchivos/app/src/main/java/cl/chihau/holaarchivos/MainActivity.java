package cl.chihau.holaarchivos;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    TextView txtResultado;
    Button btnEscribirSD;
    Button btnLeerSD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResultado = findViewById(R.id.txt_resultado);

        btnEscribirSD = findViewById(R.id.btn_escribir_sd);
        btnLeerSD = findViewById(R.id.btn_leer_sd);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (chequearPermiso()) {
            btnEscribirSD.setEnabled(true);
            btnLeerSD.setEnabled(true);
        } else {
            btnEscribirSD.setEnabled(false);
            btnLeerSD.setEnabled(false);
            pedirPermiso();
        }
    }

    public void escribirArchivo(View view) {
        try {
            FileOutputStream fout = openFileOutput("prueba_int.txt", Context.MODE_PRIVATE);
            String texto = "Texto de prueba desde memoria interna";
            fout.write(texto.getBytes());
            fout.close();
            txtResultado.setText("Archivo creado!");
        } catch (Exception e) {
            Log.e("HolaArchivos", "Error al escribir el archivo en memoria interna");
            txtResultado.setText("Error al escribir el archivo en memoria interna");
        }
    }

    public void leerArchivo(View view) {
        try {
            BufferedReader fin = new BufferedReader(
                    new InputStreamReader(openFileInput("prueba_int.txt")));
            String texto = fin.readLine();
            fin.close();
            txtResultado.setText(texto);
        } catch (Exception ex) {
            Log.e("HolaArchivos", "Error al leer el archivo en memoria interna");
            txtResultado.setText("Error al leer el archivo en memoria interna");
        }
    }


    public void escribirSD(View view) {
        boolean sdDisponible = false;
        boolean sdAccesoEscritura = false;

        String estado = Environment.getExternalStorageState();

        if (estado.equals(Environment.MEDIA_MOUNTED)) {
            sdDisponible = true;
            sdAccesoEscritura = true;
        } else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            sdDisponible = true;
            sdAccesoEscritura = false;
        } else {
            sdDisponible = false;
            sdAccesoEscritura = false;
        }

        if (sdDisponible && sdAccesoEscritura) {
            try {
                File ruta_sd = Environment.getExternalStorageDirectory();
                File f = new File(ruta_sd.getAbsolutePath(), "prueba_sd.txt");
                OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(f));
                fout.write("Texto de prueba desde SD");
                fout.close();
                txtResultado.setText("Archivo SD creado!");
            } catch (Exception ex) {
                Log.e("HolaArchivos", "Error al escribir el archivo en memoria externa");
                txtResultado.setText("Error al escribir el archivo en memoria externa");
            }
        } else {
            Log.e("HolaArchivos", "Error: la tarjeta SD no se encuentra o " +
                    "no tiene permisos de escritura");
            txtResultado.setText("Error: la tarjeta SD no se encuentra o " +
                    "no tiene permisos de escritura");
        }
    }

    public void leerSD(View view) {
        try {
            File ruta_sd = Environment.getExternalStorageDirectory();
            File f = new File(ruta_sd.getAbsolutePath(), "prueba_sd.txt");
            BufferedReader fin = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String texto = fin.readLine();
            fin.close();
            txtResultado.setText(texto);
        } catch (Exception e) {
            Log.e("HolaArchivos", "Error al leer el archivo en memoria externa");
            txtResultado.setText("Error al leer el archivo en memoria externa");
        }
    }

    public void leerRaw(View view) {
        try {
            InputStream fraw = getResources().openRawResource(R.raw.prueba_raw);
            BufferedReader brin = new BufferedReader(new InputStreamReader(fraw));
            String texto = brin.readLine();
            fraw.close();
            brin.close();
            txtResultado.setText(texto);
        } catch (Exception e) {
            Log.e("HolaArchivos", "Error al leer el archivo RAW");
            txtResultado.setText("Error al leer el archivo RAW");
        }
    }

    private boolean chequearPermiso() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        return result == PackageManager.PERMISSION_GRANTED;
    }

    private void pedirPermiso() {
        ActivityCompat.requestPermissions(this,
                new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                200);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 200: {
                // Si el request es cancelado entonces el arreglo tiene largo 0
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    btnEscribirSD.setEnabled(true);
                    btnLeerSD.setEnabled(true);
                } else {
                    btnEscribirSD.setEnabled(false);
                    btnLeerSD.setEnabled(false);
                }

                return;
            }
        }
    }
}
