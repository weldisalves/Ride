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

    public static final String PATH_PESSOA = "pessoa";
    public static final String PATH_CONDUTOR = "condutor";
    public static final String PATH_CARONA = "carona";
    public static final String PATH_VEICULO = "veiculo";
    public static final String PATH_PONTOVIRTUAL = "pontovirtual";
    public static final String PATH_AVALIACAO = "avaliacao";
    public static final String PATH_PASSAGEIROS_DE_CARONA = "passageiros de carona";
    public static final String PATH_USUARIO_DE_PONTO_VIRTUAL = "usuario de ponto virtual";
    public static final String PATH_PASSAGEIROS_PRE_APROVADOS = "passageiros pre aprovado";


    public static final class PessoaEntry implements BaseColumns{
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_PESSOA).build();
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PESSOA;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PESSOA;

        public static final String TABLE_NAME = "pessoa";

        public static final String COLUMN_PESSOA_NAME = "pessoa_name";
        public static final String COLUMN_PESSOA_SOBRENOME = "pessoa_sobrenome";
        public static final String COLUMN_PESSOA_USERNAME = "pessoa_username";
        public static final String COLUMN_PESSOA_PASSWORD = "pessoa_password";
        public static final String COLUMN_PESSOA_DATA_NASCIMENTO = "pessoa_nascimento";
        public static final String COLUMN_SEXO = "pessoa_sexo";
        public static final String COLUMN_ID_VEICULO = "pessoa_id_veiculo";
        public static final String COLUMN_STATUS = "pessoa_status";
        public static final String COLUMN_CNH = "pessoa_cnh";

        public static Uri buildPessoaUri(long id){
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
        public static final String COLUMN_LISTA_DE_PASSAGEIROS = "carona_listaDePassageiros";

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

    public static final class UsuarioDePontoVirtaulEntry implements BaseColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_USUARIO_DE_PONTO_VIRTUAL).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USUARIO_DE_PONTO_VIRTUAL;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_USUARIO_DE_PONTO_VIRTUAL;

        public static final String TABLE_NAME = "usuario_de_ponto_virtual";

        public static final String COLUMN_ID_USUARIO = "usuário_usuario_de_ponto_virtual";
        public static final String COLUMN_ID_PONTO_VIRTUAL = "usuario_id_ponto_de_embarque";
    }

    public static final class PassageirosPreAprovadosEntry implements BaseColumns{
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_PASSAGEIROS_PRE_APROVADOS).build();

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PASSAGEIROS_PRE_APROVADOS;
        public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_PASSAGEIROS_PRE_APROVADOS;

        public static final String TABLE_NAME = "passageiros_pre_aprovados";


    }

}
