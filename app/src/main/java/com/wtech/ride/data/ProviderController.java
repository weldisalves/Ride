package com.wtech.ride.data;

import android.content.ContentValues;
import android.net.Uri;

/**
 * Created by Guilherme on 07/12/2016.
 */

public class ProviderController {

    RideProvider provider;

    public void insere(String nomeTabela, ContentValues values){
        Uri uri;
        switch (nomeTabela){
            case RideContract.CaronaEntry.TABLE_NAME: // SE NOME == "CARONA"
                uri = RideContract.CaronaEntry.CONTENT_URI;
                break;
            case RideContract.VeiculoEntry.TABLE_NAME:
                uri = RideContract.VeiculoEntry.CONTENT_URI;
                break;
            case RideContract.PontoVirtualEntry.TABLE_NAME:
                uri = RideContract.PontoVirtualEntry.CONTENT_URI;
                break;
            case RideContract.UsuarioEntry.TABLE_NAME:
                uri = RideContract.UsuarioEntry.CONTENT_URI;
                break;
            default:
                throw new UnsupportedOperationException("Tabela desconhecida: " + nomeTabela);
        }
        provider.insert(uri,values);
    }

    public Object procura(String nomeTabela, long id){
        Object obj = new Object();
        Uri uri;
        switch (nomeTabela){
            case RideContract.CaronaEntry.TABLE_NAME: // SE NOME == "CARONA"
                uri = RideContract.CaronaEntry.CONTENT_URI;
                break;
            case RideContract.VeiculoEntry.TABLE_NAME:
                uri = RideContract.VeiculoEntry.CONTENT_URI;
                break;
            case RideContract.PontoVirtualEntry.TABLE_NAME:
                uri = RideContract.PontoVirtualEntry.CONTENT_URI;
                break;
            case RideContract.UsuarioEntry.TABLE_NAME:
                uri = RideContract.UsuarioEntry.CONTENT_URI;
                break;
            default:
                throw new UnsupportedOperationException("Tabela desconhecida: " + nomeTabela);
        }
        return obj;
    }
}
