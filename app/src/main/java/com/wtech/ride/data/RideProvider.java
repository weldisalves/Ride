package com.wtech.ride.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Guilherme on 05/12/2016.
 */

public class RideProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private DbHelper mOpenHelper;



    static final int CARONA = 100;
    static final int USUARIO = 200;
    static final int VEICULO = 300;
    static final int PONTOVIRTUAL = 400;


    static UriMatcher buildUriMatcher(){
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = RideContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, RideContract.PATH_CARONA, CARONA);
        matcher.addURI(authority, RideContract.PATH_USUARIO, USUARIO);
        matcher.addURI(authority, RideContract.PATH_PONTOVIRTUAL, PONTOVIRTUAL);
        matcher.addURI(authority, RideContract.PATH_VEICULO, VEICULO);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        mOpenHelper = new DbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {

        Cursor retCursor;
        switch (sUriMatcher.match(uri)){
            case CARONA: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        RideContract.CaronaEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case PONTOVIRTUAL: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        RideContract.PontoVirtualEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case VEICULO: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        RideContract.VeiculoEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case USUARIO: {
                retCursor = mOpenHelper.getReadableDatabase().query(
                        RideContract.PessoaEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            default:
                throw new UnsupportedOperationException("URI desconhecida: " +uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match = sUriMatcher.match(uri);
        switch(match) {
            case CARONA:
                return RideContract.CaronaEntry.CONTENT_TYPE;
            case USUARIO:
                return RideContract.PessoaEntry.CONTENT_TYPE;
            case VEICULO:
                return RideContract.VeiculoEntry.CONTENT_TYPE;
            case PONTOVIRTUAL:
                return RideContract.PontoVirtualEntry.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("URI desconhecida: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;
        switch (match) {
            case CARONA:{
                long _id = db.insert(RideContract.CaronaEntry.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = RideContract.CaronaEntry.buildCaronaUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case USUARIO:{
                long _id = db.insert(RideContract.PessoaEntry.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = RideContract.PessoaEntry.buildPessoaUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case VEICULO:{
                long _id = db.insert(RideContract.VeiculoEntry.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = RideContract.VeiculoEntry.buildVeiculoUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case PONTOVIRTUAL:{
                long _id = db.insert(RideContract.PontoVirtualEntry.TABLE_NAME, null, values);
                if (_id > 0)
                    returnUri = RideContract.PontoVirtualEntry.buildPontoVirtualUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);

        int rowsDeleted;
        // this makes delete all rows return the number of rows deleted
        if ( null == selection ) selection = "1";
        switch (match) {
            case CARONA:
                rowsDeleted = db.delete(
                        RideContract.CaronaEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case USUARIO:
                rowsDeleted = db.delete(
                        RideContract.PessoaEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case VEICULO:
                rowsDeleted = db.delete(
                        RideContract.VeiculoEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case PONTOVIRTUAL:
                rowsDeleted = db.delete(
                        RideContract.PontoVirtualEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsUpdated;

        switch (match) {
            case CARONA:
                rowsUpdated = db.update(RideContract.CaronaEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case USUARIO:
                rowsUpdated = db.update(RideContract.PessoaEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case VEICULO:
                rowsUpdated = db.update(RideContract.VeiculoEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            case PONTOVIRTUAL:
                rowsUpdated = db.update(RideContract.PontoVirtualEntry.TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }
}
