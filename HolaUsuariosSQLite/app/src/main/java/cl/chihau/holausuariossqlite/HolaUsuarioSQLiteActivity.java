package cl.chihau.holausuariossqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class HolaUsuarioSQLiteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        HolaUsuariosSQLiteHelper usdbh =
                new HolaUsuariosSQLiteHelper(this, "DBUsuarios", null, 1);

        SQLiteDatabase db = usdbh.getWritableDatabase();

        if (db != null) {
            Cursor c = db.rawQuery("SELECT * FROM Usuarios", null);

            //Nos aseguramos de que existe al menos un registro
            if (c.moveToFirst()) {
                StringBuilder sb = new StringBuilder();
                do {
                    sb.append(c.getString(1));
                    sb.append("\n");
                } while (c.moveToNext());

                String usuarios = sb.toString();
                TextView txt = (TextView)findViewById(R.id.txt);
                txt.setText(usuarios);
            }

            //Cerramos la base de datos
            db.close();
        }
    }
}
