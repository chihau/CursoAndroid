package cl.chihau.holalistview;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HolaListViewActivity extends ListActivity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Generamos un arreglo de Strings a partir de el string-array declarado
		// en strings.xml 
		String[] countries = getResources().getStringArray(R.array.countries_array);
		
		// Adaptador que contendr‡ el dise–o de cada elemento de la lista y el arreglo
		// con los nombres de los pa’ses
		ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, R.layout.list_item, 
				countries);
		
		// Cargamos el adaptador para que se muestre la lista
		setListAdapter(adaptador);
		
		// Relacionamos la lista de mi actividad con un objeto de tipo ListView
		ListView lv = getListView();
		
		// Habilitamos el filtrado de texto de la lista
		lv.setTextFilterEnabled(true);
		
		// Creamos un escuchador que est‡ pendiente cuando el usuario
		// hace click en un item de la lista
		OnItemClickListener escuchador = new OnItemClickListener() {
			
			// MŽtodo que se ejecuta cuando se hace click en un item
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int pos,
					long id) {
				
				// Creamos una burbuja emergente que despliega el pa’s elegido
				// por un tiempo largo
				Toast.makeText(getApplicationContext(), "Usted elegi— "+((TextView) view).getText(), 
						Toast.LENGTH_LONG).show();
				
			}
           
        };
        
        // Le asignamos un escuchador al ListView
        lv.setOnItemClickListener(escuchador);
	}

}
