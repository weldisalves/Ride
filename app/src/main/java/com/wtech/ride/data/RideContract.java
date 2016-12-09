package com.wtech.ride.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Guilherme on 09/12/2016.
 */

public class RideContract {

    public static final String CONTENT_AUTHORITY = "com.wtech.smartrider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_CARONA = "carona";

    public static final class CaronaEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_CARONA).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CARONA;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CARONA;

        public static final String TABLE_NAME = "carona";

        public static final String COLUMN_CONDUTOR_NOME = "condutor_nome";
        public static final String COLUMN_CONDUTOR_AVALIACAO = "condutor_avaliacao";
        public static final String COLUMN_VEICULO_MODELO = "veiculo_modelo";
        public static final String COLUMN_VEICULO_COR = "veiculo_cor";
        public static final String COLUMN_VEICULO_PLACA = "veiculo_placa";
        public static final String COLUMN_VEICULO_IMAGEM = "veiculo_imagem";
        public static final String COLUMN_CARONA_PARTIDA = "carona_partida";
        public static final String COLUMN_CARONA_DESTINO = "carona_destino";
        public static final String COLUMN_CARONA_HORA_PARTIDA = "carona_hora_partida";
        public static final String COLUMN_CARONA_QTD_VAGAS = "carona_vagas";

        public static Uri buildCaronaUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static Uri buildCarona(long assessment_id){
            return CONTENT_URI.buildUpon().appendPath(String.valueOf(assessment_id)).build();
        }

        public static String getCaronaFromUri(Uri uri){
            return uri.getPathSegments().get(1);
        }
    }

}
