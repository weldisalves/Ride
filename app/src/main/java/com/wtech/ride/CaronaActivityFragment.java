package com.wtech.ride;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class CaronaActivityFragment extends Fragment {

    ArrayList<Carona> caronas;
    CaronaAdapter caronaAdapter;
    ListView listViewCarona;

    public CaronaActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View carona = inflater.inflate(R.layout.fragment_carona, container, false);

        final SwipeRefreshLayout swipe = (SwipeRefreshLayout) carona.findViewById(R.id.swipeRefreshCarona);

        caronas = new ArrayList<Carona>();
        listViewCarona = (ListView)carona.findViewById(R.id.listViewCarona);
        caronaAdapter = new CaronaAdapter(getContext(),R.layout.item_list_view_carona,caronas);

        listViewCarona.setAdapter(caronaAdapter);

        listViewCarona.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Carona c = caronas.get(i); //caronaAdapter.getItem(i);
                Intent intent = new Intent(getContext(),CaronaDescriptionActivity.class);
                intent.putExtra("carona",c);
                startActivity(intent);
            }
        });

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        carregarCaronas();
                        swipe.setRefreshing(false);
                    }
                }, 500);
            }
        });

        return carona;
    }


    void carregarCaronas(){
        caronas.clear();

        for(int i = 0; i< 10;i++){
            Carona carona = new Carona();

            //preenchendo os valores especificos da classe carona
            carona.setHoraDaPartida(i*12+"-"+i*60+"-"+i*60);
            carona.setIdCondutor(i*3);
            if(i%3==0)carona.setIdOrigem(i*7);
            carona.setIdDestino(i*9);
            carona.setNumeroDeVagas(i+1);

            String listaDePassageiros [] = {String.valueOf(i*11), String.valueOf(i*13),String.valueOf(i*17), String.valueOf(i*19)};
            carona.setListaDePassageiros(listaDePassageiros);

            String pontosDeEmbarque[] = {String.valueOf(i*23), String.valueOf(i*29),String.valueOf(i*31), String.valueOf(i*37)};
            carona.setPontosDeEmbarque(pontosDeEmbarque);

            caronas.add(carona);
        }

        caronaAdapter = new CaronaAdapter(getContext(),R.layout.item_list_view_carona,caronas);
        listViewCarona.setAdapter(caronaAdapter);

    }
}
