package com.wtech.ride.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wtech.ride.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class CaronaCreateActivityFragment extends Fragment {

    public CaronaCreateActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_carona_create, container, false);
    }
}
