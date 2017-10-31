package cl.chihau.holacamara;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static final int TAKE_PICTURE = 0;
    Uri mUri;
    Bitmap mPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sacarFoto(View view) {
        if (view.getId() == R.id.btn_foto &&
                getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {

            // Desde Android 5.1 el método recomendado para acceder a los archivos es utilizando
            // un FileProvider (que es una subclase de ContentProvider)
            // antiguamente se accedía directamente a través de la uri file:// pero ahora es
            // a través de content://
            Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
            // Antes era File f = new File(Environment.getExternalStorageDirectory(),  "foto.jpg");
            File f = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES),  "foto.jpg");

            // Antes era mUri = Uri.fromFile(f); // Ej: file://sdcard/foto.jpg
            mUri = FileProvider.getUriForFile(MainActivity.this,
                    BuildConfig.APPLICATION_ID + ".provider",
                    f);

            i.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
            startActivityForResult(i, TAKE_PICTURE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TAKE_PICTURE:
                if (resultCode == Activity.RESULT_OK) {
                    try {
                        //Cargamos la imagen guardada en la memoria SD en nuestro ImageView
                        mPhoto = android.provider.MediaStore.Images.Media.getBitmap(
                                getContentResolver(), mUri);
                        ((ImageView)findViewById(R.id.img_foto)).setImageBitmap(mPhoto);
                    } catch (Exception e) {
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("HolaCamara", e.getMessage());
                    }
                }
        }
    }
}
