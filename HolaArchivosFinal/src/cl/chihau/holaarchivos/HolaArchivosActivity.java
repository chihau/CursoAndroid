package cl.chihau.holaarchivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HolaArchivosActivity extends Activity {
	
	private Button btnEscribirArchivo = null;
	private Button btnLeerArchivo = null;
	
	private Button btnEscribirSD = null;
	private Button btnLeerSD = null;
	
	private Button btnLeerRaw = null;
	
	private TextView txtResultado = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		
		btnEscribirArchivo = (Button) findViewById(R.id.BtnEscribirArchivo);
        btnLeerArchivo = (Button) findViewById(R.id.BtnLeerArchivo);
        
        btnEscribirSD = (Button) findViewById(R.id.BtnEscribirSD);
        btnLeerSD = (Button) findViewById(R.id.BtnLeerSD);
        
        btnLeerRaw = (Button) findViewById(R.id.BtnLeerRaw);
        
        txtResultado = (TextView) findViewById(R.id.TxtResultado);
        
        btnEscribirArchivo.setOnClickListener(new OnClickListener() {

    		@Override
    		public void onClick(View v) {
    			try
 				{
    				//Metodo para escribir archivos en memoria interna
    				//devuelve un stream de salida asociado en forma de FileOutputStream
    				//MODE_PRIVATE (por defecto): acceso privado desde nuestra app
    				//MODE_APPEND: añadir datos al archivo ya existente
					//MODE_WORLD_READABLE: para que otras apps puedan leer
					//MODE_WORLD_WRITABLE: para que otras apps puedan escribir
 					OutputStreamWriter fout = new OutputStreamWriter(
 						openFileOutput("prueba_int.txt", Context.MODE_PRIVATE));

 					fout.write("Texto de prueba desde mem interna.");
 					fout.close();
 					
 					txtResultado.setText("Archivo creado!");
 				}
 				catch (Exception ex)
 				{
 					Log.e("Archivos", "Error al escribir Archivo a memoria interna");
 				}
    		}
    		
    	});
        
        btnLeerArchivo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				try
				{
					//Método que nos permite abrir archivos de la mem interna
					BufferedReader fin = new BufferedReader(new InputStreamReader(
						openFileInput("prueba_int.txt")));

					String texto = fin.readLine();
					
					fin.close();
					
					txtResultado.setText(texto);
				}
				catch (Exception ex)
				{
					Log.e("Archivos", "Error al leer Archivo desde memoria interna");
				}
			}
        	
        });
        
        btnEscribirSD.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				boolean sdDisponible = false;
				boolean sdAccesoEscritura = false;
				
				//Comprobamos el estado de la memoria externa (tarjeta SD)
				String estado = Environment.getExternalStorageState();
				//MEDIA_MOUNTED: memoria externa disponible
				//MEDIA_MOUNTED_READ_ONLY: memoria externa disponible s√≥lo lectura
				//Otros (MEDIA_UNMOUNTED, MEDIA_REMOVED)
				
				if (estado.equals(Environment.MEDIA_MOUNTED))
				{
					sdDisponible = true;
					sdAccesoEscritura = true;
				} 
				else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
				{
					sdDisponible = true;
					sdAccesoEscritura = false;
				} 
				else 
				{
					sdDisponible = false;
					sdAccesoEscritura = false;
				}
				
				//Si la memoria externa está disponible y se puede escribir
				if (sdDisponible && sdAccesoEscritura)
				{
					try
					{	
						//Obtenemos las ruta donde está montada la tarjeta SD
						File ruta_sd = Environment.getExternalStorageDirectory();
						
						//Le agregamos a la ruta el nombre del archivo que queremos crear
						File f = new File(ruta_sd.getAbsolutePath(), "prueba_sd.txt");
						
						OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(f));
						
						fout.write("Texto de prueba desde SD.");
						fout.close();
						
						txtResultado.setText("Archivo SD creado!");
					}
					catch (Exception ex)
					{
						Log.e("Archivos", "Error al escribir Archivo a tarjeta SD");
					}
				} else {
					Log.e("Archivos", "Error: la tarjeta SD no se encuentra disponible " +
							"o no tiene permisos de escritura");
				}
				
			}
        	
        });
        
        btnLeerSD.setOnClickListener(new OnClickListener() {
			
 			@Override
 			public void onClick(View arg0) 
 			{	
 				try
 				{
 					File ruta_sd = Environment.getExternalStorageDirectory();
 					
 					File f = new File(ruta_sd.getAbsolutePath(), "prueba_sd.txt");
 					
 					BufferedReader fin = new BufferedReader(new InputStreamReader(
 							new FileInputStream(f)));
 					
 					String texto = fin.readLine();
 					fin.close();
 					
 					txtResultado.setText(texto);
 				}
 				catch (Exception ex)
 				{
 					Log.e("Archivos", "Error al leer Archivo desde tarjeta SD");
 				}
 			}
 		});
        
        btnLeerRaw.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String linea = "";
				
				try
				{
					InputStream fraw = getResources().openRawResource(R.raw.prueba_raw);
					BufferedReader brin = new BufferedReader(new InputStreamReader(fraw));
					linea = brin.readLine();
					fraw.close();
					txtResultado.setText(linea);
				}
				catch (Exception ex)
				{
					Log.e("Archivos", "Error al leer Archivo desde recurso raw");;
				}		
			}
        	
        });

	}
	
	
}
