package cl.chihau.holaintentfilter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText etURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etURL = findViewById(R.id.et_url);
    }

    public void navegar(View view) {
        String url = etURL.getText().toString();

        Intent i = new Intent("cl.chihau.accion.navegar", Uri.parse("http://" + url));
        startActivity(i);
    }
}
