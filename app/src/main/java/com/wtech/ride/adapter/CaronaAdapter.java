package com.wtech.ride.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.wtech.ride.R;
import com.wtech.ride.model.Carona;

import java.util.ArrayList;

/**
 * Created by weldis on 25/11/16.
 */

public class CaronaAdapter extends ArrayAdapter<Carona> {
    ArrayList<Carona> carona = new ArrayList<>();

    public CaronaAdapter(Context context, int textViewResourceId, ArrayList<Carona> objects) {
        super(context, textViewResourceId, objects);
        carona = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        v = inflater.inflate(R.layout.item_list_view_carona, null);

        Carona c = carona.get(position);

        RatingBar ratingBarCondutor = (RatingBar)v.findViewById(R.id.ratingBarCondutor);
        ratingBarCondutor.setRating(4);

        TextView nomeCondutor = (TextView)v.findViewById(R.id.txtViewNome);
        nomeCondutor.setText("idCondutor - " + c.getIdCondutor());

        TextView horaDaPartida = (TextView)v.findViewById(R.id.txtViewHoraDaPartida);
        horaDaPartida.setText(c.getHoraDaPartida());

        TextView pontoDePartida = (TextView)v.findViewById(R.id.txtViewOrigem);
        pontoDePartida.setText("idOrigem - "+c.getIdOrigem());

        TextView pontoDeChegada = (TextView)v.findViewById(R.id.txtViewDestino);
        pontoDeChegada.setText("idDestino - "+c.getIdDestino());

        return v;

    }
}
