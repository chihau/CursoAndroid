package cl.chihau.holalifecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class HolaLifeCycleActivity extends AppCompatActivity {

    private static final String LOGTAG = "HolaLifeCycle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Log.d(LOGTAG, "onCreate");
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.d(LOGTAG, "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.d(LOGTAG, "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.d(LOGTAG, "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.d(LOGTAG, "onStop");
    }

    @Override
    public void onRestart() {
        super.onRestart();

        Log.d(LOGTAG, "onRestart");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.d(LOGTAG, "onDestroy");
    }
}
