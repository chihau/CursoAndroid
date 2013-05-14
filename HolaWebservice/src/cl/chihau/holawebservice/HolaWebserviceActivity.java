package cl.chihau.holawebservice;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class HolaWebserviceActivity extends Activity{
	
	//Namespace definido para nuestro ws
	String NAMESPACE = "http://webservicetest.chihau.cl";
	//Método que vamos a utilizar
	String METHOD_NAME = "doBasicStuff";
	//NAMESPACE+METHOD_NAME
	String SOAP_ACTION = "http://webservicetest.chihau.cl/doBasicStuff";
	String URL = "http://192.168.0.100:8080/WebserviceTest/services/DoesMagic?WSDL";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		
		final TextView tv = (TextView) findViewById(R.id.txtResultado);
		
		//Desde la versión 4.0 de Android ya no se pueden ejecutar ciertas tareas desde el 
		//hilo principal, ya que es una mala practica que puede afectar el desempeño de nuestra
		//aplicación. Entra esas tareas están las conexión de red, por lo que tenemos que
		//ejecutar la conexión al webservice desde otro hilo.
		new Thread(new Runnable() {
	        public void run() {
	    		SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
	            
	    		PropertyInfo propInfo = new PropertyInfo();
	    		propInfo.setName("message");
	    		propInfo.setValue("test!");
	    		propInfo.setType(PropertyInfo.STRING_CLASS);

	    		request.addProperty(propInfo);
	    		
	    		//Creamos un contenedor SOAP
	    		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
	    				SoapEnvelope.VER11);
	    		envelope.setOutputSoapObject(request);
	    		
	    		HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
	    		
	    		try {
				androidHttpTransport.call(SOAP_ACTION, envelope);

				final SoapPrimitive resultsRequestSOAP = (SoapPrimitive) envelope.getResponse();
	        	
				//Otra cosa que no deberíamos hacer es acceder a la interfaz desde fuera
				//del hilo principal, por lo que tenemos que utilizar el método post
				//que está disponible para cualquier View y así modificar el TextView de mi
				//aplicación
	        	tv.post(new Runnable() {
	                public void run() {
	        			tv.setText(resultsRequestSOAP.toString());
	                }
	            });
	        	
	    		} catch (Exception e) {
	    			Log.e("HolaWebservice", "Error al conectarse al webservice");
	    			Log.e("HolaWebservice", e.getMessage());
	    		}
	        }
	    }).start();
		//Otra alternativa a la clase Thread y muy recomendada en Android es utilizar AsyncTask
		//Pero eso se ve en un curso más avanzado
	}
}
