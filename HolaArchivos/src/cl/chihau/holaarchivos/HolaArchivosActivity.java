package cl.chihau.holaarchivos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class HolaArchivosActivity extends Activity {
	
	private Button btnEscribirArchivo = null;
	private Button btnLeerArchivo = null;
	
	private TextView txtResultado = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		
		btnEscribirArchivo = (Button)findViewById(R.id.BtnEscribirArchivo);
        btnLeerArchivo = (Button)findViewById(R.id.BtnLeerArchivo);
        
        txtResultado = (TextView) findViewById(R.id.TxtResultado);
        
        btnEscribirArchivo.setOnClickListener(new OnClickListener() {

    		@Override
    		public void onClick(View v) {
    			try
 				{
    				//Metodo para escribir archivos en memoria interna
    				//devuelve un stream de salida asociado en forma de FileOutputStream
    				//MODE_PRIVATE (por defecto): acceso privado desde nuestra app
    				//MODE_APPEND: a–adir datos al archivo ya existente
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
					//MŽtodo que nos permite abrir archivos de la mem interna
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

	}
	
	
}
