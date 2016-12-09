package com.wtech.ride.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wtech.ride.R;
import com.wtech.ride.activity.CaronaActivity;
import com.wtech.ride.activity.CaronaCreateActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    Button btExibirCaronas;
    Button btCriarCarona;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View main = inflater.inflate(R.layout.fragment_main, container, false);

        btExibirCaronas = (Button)main.findViewById(R.id.btnExibirCaronas);

        btExibirCaronas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),CaronaActivity.class);
                startActivity(intent);
            }
        });

        btCriarCarona = (Button)main.findViewById(R.id.btnCriarCarona);

        btCriarCarona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),CaronaCreateActivity.class);
                startActivity(intent);
            }
        });


        return main;

    }
}
