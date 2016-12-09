package com.wtech.ride.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.wtech.ride.data.RideContract;

/**
 * Created by Guilherme on 09/12/2016.
 */

public class DbHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "ride.db";
    public static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_CARONA_TABLE = "CREATE TABLE " + RideContract.CaronaEntry.TABLE_NAME + " (" +
                RideContract.CaronaEntry._ID +                          " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                RideContract.CaronaEntry.COLUMN_CONDUTOR_NOME +         " TEXT NOT NULL, " +
                RideContract.CaronaEntry.COLUMN_CONDUTOR_AVALIACAO +    " TEXT NOT NULL, " +
                RideContract.CaronaEntry.COLUMN_VEICULO_MODELO +        " TEXT NOT NULL, " +
                RideContract.CaronaEntry.COLUMN_VEICULO_COR +           " TEXT NOT NULL, " +
                RideContract.CaronaEntry.COLUMN_VEICULO_PLACA +         " TEXT NOT NULL, " +
                RideContract.CaronaEntry.COLUMN_VEICULO_IMAGEM +        " TEXT NOT NULL, " +
                RideContract.CaronaEntry.COLUMN_CARONA_DESTINO +        " TEXT NOT NULL, " +
                RideContract.CaronaEntry.COLUMN_CARONA_PARTIDA +        " TEXT NOT NULL, " +
                RideContract.CaronaEntry.COLUMN_CARONA_HORA_PARTIDA +   " TEXT NOT NULL, " +
                RideContract.CaronaEntry.COLUMN_CARONA_QTD_VAGAS +      " TEXT NOT NULL "  +
                                                                        ");";
        db.execSQL(SQL_CREATE_CARONA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RideContract.CaronaEntry.TABLE_NAME);
        onCreate(db);
    }
}
