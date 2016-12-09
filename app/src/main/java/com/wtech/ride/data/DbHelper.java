package com.wtech.ride.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wtech.ride.data.RideContract.*;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "smartride.db";
    public static final int DATABASE_VERSION = 2;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_PESSOA_TABLE = "CREATE TABLE "+ UsuarioEntry.TABLE_NAME +" ("+
                UsuarioEntry._ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                UsuarioEntry.COLUMN_USUARIO_NAME +" TEXT NOT NULL, " +
                UsuarioEntry.COLUMN_USUARIO_SOBRENOME +" TEXT NOT NULL, " +
                UsuarioEntry.COLUMN_USUARIO_DATA_NASCIMENTO + " INTEGER NOT NULL, " +
                UsuarioEntry.COLUMN_USUARIO_USERNAME +" TEXT NOT NULL, "+
                UsuarioEntry.COLUMN_USUARIO_PASSWORD +" TEXT NOT NULL);";

        final String SQL_CREATE_PONTOVIRTUAL_TABLE = "CREATE TABLE " + PontoVirtualEntry.TABLE_NAME + " ("+
                PontoVirtualEntry._ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                PontoVirtualEntry.COLUMN_PONTOVIRTUAL_LATITUDE + " REAL NOT NULL, " +
                PontoVirtualEntry.COLUMN_PONTOVIRTUAL_LONGITUDE + " REAL NOT NULL, " +
                PontoVirtualEntry.COLUMN_PONTOVIRTUAL_DATA_CRIACAO + " TEXT NOT NULL);";

        final String SQL_CREATE_VEICULO_TABLE = "CREATE TABLE " +  VeiculoEntry.TABLE_NAME + " ("+
                VeiculoEntry._ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                VeiculoEntry.COLUMN_VEICULO_COR + " TEXT NOT NULL, " +
                VeiculoEntry.COLUMN_VEICULO_FOTO + " TEXT NOT NULL," +
                VeiculoEntry.COLUMN_VEICULO_MARCA + " TEXT NOT NULL, " +
                VeiculoEntry.COLUMN_VEICULO_MODELO + " TEXT NOT NULL, " +
                VeiculoEntry.COLUMN_VEICULO_PLACA + " TEXT NOT NULL);";

        final String SQL_CREATE_CARONA_TABLE = "CREATE TABLE " + CaronaEntry.TABLE_NAME + " (" +
                CaronaEntry._ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                CaronaEntry.COLUMN_CARONA_HORA_PARTIDA + " TEXT NOT NULL, " +
                CaronaEntry.COLUMN_CARONA_ID_DESTINO + " INTEGER NOT NULL, " +
                CaronaEntry.COLUMN_CARONA_ID_PONTOPARTIDA + " INTEGER NOT NULL, " +
                CaronaEntry.COLUMN_CARONA_IDCONDUTOR + " INTEGER NOT NULL, " +
                CaronaEntry.COLUMN_CARONA_NUMERO_VAGAS + " INTEGER NOT NULL);";

        sqLiteDatabase.execSQL(SQL_CREATE_CARONA_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_PESSOA_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_PONTOVIRTUAL_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_VEICULO_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + UsuarioEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + CaronaEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + VeiculoEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PontoVirtualEntry.TABLE_NAME);

        onCreate(db);
    }
}
