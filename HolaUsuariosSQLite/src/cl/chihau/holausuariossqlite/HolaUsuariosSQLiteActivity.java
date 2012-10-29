package cl.chihau.holausuariossqlite;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class HolaUsuariosSQLiteActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		
		//Abrimos la base de datos 'DBUsuarios' en modo escritura
        HolaUsuariosSQLiteHelper usdbh = 
        		new HolaUsuariosSQLiteHelper(this, "DBUsuarios", null, 1);
        
        SQLiteDatabase db = usdbh.getWritableDatabase();
        
        if(db != null) {
        	Cursor c = db.rawQuery("SELECT * FROM Usuarios", null);
        	//Nos aseguramos de que existe al menos un registro
        	if (c.moveToFirst()) {
        		String usuario = c.getString(1);
        	    TextView txt = (TextView)findViewById(R.id.txt);
        	    txt.setText(usuario);
        	}
 
            //Cerramos la base de datos
            db.close();
        }
	}
}
