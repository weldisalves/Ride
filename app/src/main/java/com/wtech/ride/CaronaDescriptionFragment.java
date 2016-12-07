package com.wtech.ride;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.net.URISyntaxException;

/**
 * A placeholder fragment containing a simple view.
 */
public class CaronaDescriptionFragment extends Fragment {



    public CaronaDescriptionFragment(){


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View description = inflater.inflate(R.layout.fragment_carona_description, container, false);

        ImageView imgVeiculo = (ImageView)description.findViewById(R.id.imgVeiculo);
        ImageView imgPerfil = (ImageView)description.findViewById(R.id.imgPerfil);
        TextView nome = (TextView)description.findViewById(R.id.txtViewNome);
        TextView profissao = (TextView)description.findViewById(R.id.txtViewProfissao);
        RatingBar ratingBar = (RatingBar)description.findViewById(R.id.ratingBarCondutor);
        ListView passageiros = (ListView)description.findViewById(R.id.listViewPassageiros);
        TextView numeroDeVagas = (TextView)description.findViewById(R.id.txtViewNumeroDeVagas);
        Button solicitarCarona = (Button)description.findViewById(R.id.btSolicitarCarona);

        nome.setText("idCondutor.getNome()");
        profissao.setText("idCondutor.getProfissão()");

        ratingBar.setNumStars(5);
        //idConduto.getAvaliacao();
        ratingBar.setRating(5);





        return description;
    }
}