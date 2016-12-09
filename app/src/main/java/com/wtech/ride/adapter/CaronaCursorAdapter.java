package com.wtech.ride.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.wtech.ride.R;

/**
 * Created by weldis on 07/12/16.
 */

public class CaronaCursorAdapter extends CursorAdapter {
    LayoutInflater layout;

    public CaronaCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    public CaronaCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        layout = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_list_view_carona, viewGroup, false);
    }

    @Override
    public void bindView(View v, Context context, Cursor cursor) {

        RatingBar ratingBarCondutor = (RatingBar)v.findViewById(R.id.ratingBarCondutor);
        TextView nomeCondutor = (TextView)v.findViewById(R.id.txtViewNome);
        TextView horaDaPartida = (TextView)v.findViewById(R.id.txtViewHoraDaPartida);
        TextView pontoDePartida = (TextView)v.findViewById(R.id.txtViewOrigem);
        TextView pontoDeChegada = (TextView)v.findViewById(R.id.txtViewDestino);

        int idCondutor = cursor.getInt(cursor.getColumnIndexOrThrow("carona_idcondutor"));
        int numeroDeVagas = cursor.getInt(cursor.getColumnIndexOrThrow("carona_numero_vagas"));
        int idOrigem = cursor.getInt(cursor.getColumnIndexOrThrow("carona_idpartida"));
        int idDestino = cursor.getInt(cursor.getColumnIndexOrThrow("carona_iddestino"));
        String horaPartida = cursor.getString(cursor.getColumnIndexOrThrow("carona_hora_partida"));

        nomeCondutor.setText("Condutor: "+idCondutor);
        horaDaPartida.setText(horaPartida);
        pontoDePartida.setText("ponto de partida: "+idOrigem);
        pontoDeChegada.setText("ponto de chegada: "+idDestino);

    }
}
