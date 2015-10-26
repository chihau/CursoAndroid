package cl.chihau.holaactionbar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by chihau on 26-10-15.
 */
public class SearchActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        TextView tv = (TextView) findViewById(R.id.textview1);
        tv.setText("Actividad 2 (Search)");
    }
}
