package cl.chihau.holacamara;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class HolaCamaraActivity extends AppCompatActivity {
    private static final int TAKE_PICTURE = 0;
    Uri mUri;
    Bitmap mPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
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

    public void sacarFoto(View view) {
        if (view.getId() == R.id.btn_foto &&
                getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)) {

            Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
            File f = new File(Environment.getExternalStorageDirectory(),  "foto.jpg");
            mUri = Uri.fromFile(f); // Ej: /sdcard/foto.jpg
            i.putExtra(MediaStore.EXTRA_OUTPUT, mUri);
            startActivityForResult(i, TAKE_PICTURE);
        }

    }
}
