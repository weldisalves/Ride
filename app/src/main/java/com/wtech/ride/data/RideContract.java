package com.wtech.ride.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by guilherme on 04/12/16.
 */

public class RideContract {

    public static final String CONTENT_AUTHORITY = "com.wtech.smartrider";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_USUARIO = "usuario";
    public static final String PATH_CONDUTOR = "condutor";
    public static final String PATH_CARONA = "carona";
    public static final String PATH_VEICULO = "veiculo";
    public static final String PATH_PONTOVIRTUAL = "pontovirtual";


    public static final class UsuarioEntry implements BaseColumns{
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_USUARIO).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USUARIO;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USUARIO;

        public static final String TABLE_NAME = "usuario";

        public static final String COLUMN_USUARIO_NAME = "usuario_name";
        public static final String COLUMN_USUARIO_SOBRENOME = "usuario_sobrenome";
        public static final String COLUMN_USUARIO_USERNAME = "usuario_username";
        public static final String COLUMN_USUARIO_PASSWORD = "usuario_password";
        public static final String COLUMN_USUARIO_DATA_NASCIMENTO = "usuario_nascimento";

        public static Uri buildUsuarioUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }


    }

    public static final class CaronaEntry implements BaseColumns{
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_CARONA).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CARONA;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CARONA;

        public static final String TABLE_NAME = "carona";

        public static final String COLUMN_CARONA_IDCONDUTOR = "carona_idcondutor";
        public static final String COLUMN_CARONA_NUMERO_VAGAS = "carona_numero_vagas";
        public static final String COLUMN_CARONA_ID_PONTOPARTIDA = "carona_idpartida";
        public static final String COLUMN_CARONA_ID_DESTINO = "carona_iddestino";
        public static final String COLUMN_CARONA_HORA_PARTIDA = "carona_hora_partida";

        public static Uri buildCaronaUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

    public static final class CondutorEntry implements BaseColumns{
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_CONDUTOR).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CONDUTOR;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CONDUTOR;

        public static final String TABLE_NAME = "condutor";

        public static final String COLUMN_CONDUTOR_IMG_VEICULO = "condutor_img_veiculo";
        public static final String COLUMN_CONDUTOR_SOMADASNOTAS = "condutor_soma_notas";
        public static final String COLUMN_CONDUTOR_NUMERO_AVALIACOES = "condutor_numero_avaliacoes";

        public static Uri buildCondutorUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class VeiculoEntry implements BaseColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_VEICULO).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_VEICULO;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_VEICULO;

        public static final String TABLE_NAME = "veiculo";

        public static final String COLUMN_VEICULO_MARCA = "veiculo_marca";
        public static final String COLUMN_VEICULO_COR = "veiculo_cor";
        public static final String COLUMN_VEICULO_MODELO = "veiculo_modelo";
        public static final String COLUMN_VEICULO_FOTO = "veiculo_foto";
        public static final String COLUMN_VEICULO_PLACA = "veiculo_placa";

        public static Uri buildVeiculoUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    public static final class PontoVirtualEntry implements BaseColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_PONTOVIRTUAL).build();
        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PONTOVIRTUAL;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PONTOVIRTUAL;

        public static final String TABLE_NAME = "pontovirtual";

        public static final String COLUMN_PONTOVIRTUAL_LATITUDE = "pontovirtual_latitude";
        public static final String COLUMN_PONTOVIRTUAL_LONGITUDE = "pontovirtual_longitude";
        public static final String COLUMN_PONTOVIRTUAL_DATA_CRIACAO = "pontovirtual_data_criacao";

        public static Uri buildPontoVirtualUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

    }

}
