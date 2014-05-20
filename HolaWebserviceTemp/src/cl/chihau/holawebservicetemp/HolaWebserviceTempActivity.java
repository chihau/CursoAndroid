package cl.chihau.holawebservicetemp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class HolaWebserviceTempActivity extends Activity {
    
    String NAMESPACE = "http://www.w3schools.com/webservices/";
    String METHOD_NAME = "CelsiusToFahrenheit";
    String SOAP_ACTION = "http://www.w3schools.com/webservices/CelsiusToFahrenheit";
    String URL = "http://www.w3schools.com/webservices/tempconvert.asmx";
    
    EditText et_tempCelsius;
    TextView tv_resultado;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.main);
        
        et_tempCelsius = (EditText) findViewById(R.id.et_temp);
        tv_resultado = (TextView) findViewById(R.id.tv_resultado);
    }
    
    public void convertir(View view) {
        new Thread(new Runnable() {

            @Override
            public void run() {
                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                request.addProperty("Celsius", et_tempCelsius.getText().toString());
                
                SoapSerializationEnvelope envelope = 
                        new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                
                HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

                try {
                    androidHttpTransport.call(SOAP_ACTION, envelope);
                    
                    final SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                    
                    tv_resultado.post(new Runnable() {
                        public void run() {
                            tv_resultado.setText(response.toString());
                        }
                    });
                } catch(Exception ex) {
                    Log.e("HolaWebserviceTemp", "Error al conectarse al webservice");
                    Log.e("HolaWebserviceTemp", ex.getMessage());
                }
                
            }
            
        }).start();
    }
}
