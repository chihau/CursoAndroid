package cl.chihau.holafragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by chihau on 09-11-15.
 */
public class Fragment2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Aumentamos el diseño para este fragment
        return inflater.inflate(R.layout.fragment2, container, false);

    }
}
