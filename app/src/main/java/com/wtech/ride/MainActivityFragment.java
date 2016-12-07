package com.wtech.ride;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View main = inflater.inflate(R.layout.fragment_main, container, false);

        Button btExibirCaronas = (Button)main.findViewById(R.id.btnExibirCaronas);

        btExibirCaronas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),CaronaActivity.class);
                startActivity(intent);
            }
        });

        Button btCriarCaronas = (Button)main.findViewById(R.id.btnCriarCarona);

        btCriarCaronas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),CaronaCreateActivity.class);
                startActivity(intent);
            }
        });

        return main;

    }
}
