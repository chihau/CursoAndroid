package cl.chihau.holawebservicecalc;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HolaWebserviceCalcActivity extends Activity {
	
	String NAMESPACE = "http://webservicetest2.chihau.cl";
	String METHOD_NAME = "sumar";
	String SOAP_ACTION = "http://webservicetest2.chihau.cl/sumar";
	String URL = "http://192.168.0.100:8080/WebserviceCalculadora/services/Calculadora?WSDL";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        final TextView tv = (TextView) findViewById(R.id.txtResultado);
		final EditText num1 = (EditText) findViewById(R.id.num1);
		final EditText num2 = (EditText) findViewById(R.id.num2);
		Button btnSumar = (Button) findViewById(R.id.boton);
		
		//Ojo que no se valida si los campos no contienen nada
		btnSumar.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				//Ejecutamos la conexión al ws en un hilo independiente
				new Thread(new Runnable() {
			        public void run() {

						SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		
						PropertyInfo propInfo = new PropertyInfo();
						propInfo.setName("num1");
						propInfo.setType(PropertyInfo.INTEGER_CLASS);
						propInfo.setValue(Integer.parseInt(num1.getText().toString()));
						request.addProperty(propInfo);
						
						PropertyInfo propInfo2 = new PropertyInfo();
						propInfo2.setName("num2");
						propInfo2.setType(PropertyInfo.INTEGER_CLASS);
						propInfo2.setValue(Integer.parseInt(num2.getText().toString()));
						request.addProperty(propInfo2);
						
						SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
								SoapEnvelope.VER11);
						envelope.setOutputSoapObject(request);
						HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
						
						try {
							androidHttpTransport.call(SOAP_ACTION, envelope);
		
							final SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
		
							tv.post(new Runnable() {
				                public void run() {
				                	tv.setText("El resultado de la suma es: " + response.toString());				                }
				            });
		
						} catch (Exception e) {
			    			Log.e("HolaWebserviceCalc", "Error al conectarse al webservice");
							Log.e("HolaWebserviceCalc", e.getMessage());		
						}
			        }
			    }).start();

			}
			
		});
    }

}
