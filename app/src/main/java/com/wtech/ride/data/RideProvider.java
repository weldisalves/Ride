package com.wtech.ride.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import java.sql.RowId;

/**
 * Created by Guilherme on 09/12/2016.
 */

public class RideProvider extends ContentProvider{

    private UriMatcher uriMatcher = buildUriMatcher();
    private DbHelper rideDb;

    public static final int CARONA = 1;
    public static final int CARONA_WITH_ID = 2;

    public static UriMatcher buildUriMatcher(){
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = RideContract.CONTENT_AUTHORITY;
        matcher.addURI(authority, RideContract.PATH_CARONA, CARONA);
        matcher.addURI(authority, RideContract.PATH_CARONA + "/#", CARONA_WITH_ID);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        rideDb = new DbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor;
        switch (uriMatcher.match((uri))) {
            case CARONA: {
                cursor = rideDb.getReadableDatabase().query(
                        RideContract.CaronaEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                return cursor;
            }
            case CARONA_WITH_ID: {
                cursor = rideDb.getReadableDatabase().query(
                        RideContract.CaronaEntry.TABLE_NAME,
                        projection,
                        RideContract.CaronaEntry._ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))},
                        null,
                        null,
                        sortOrder);
                return cursor;
            }
            default:
                throw new UnsupportedOperationException("URI desconhecida: " + uri);
        }
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        final int match =  uriMatcher.match(uri);
        switch (match){
            case CARONA:
                return RideContract.CaronaEntry.CONTENT_TYPE;
            case CARONA_WITH_ID:
                return RideContract.CaronaEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("URI desconhecida: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = rideDb.getWritableDatabase();
        Uri returnUri;
        switch (uriMatcher.match(uri)){
            case CARONA:{
                long _id = db.insert(RideContract.CaronaEntry.TABLE_NAME, null, values);
                if(_id > 0){
                    returnUri = RideContract.CaronaEntry.buildCaronaUri(_id);
                } else{
                    throw new android.database.SQLException("Insercao falhou: " + uri);
                }
                break;
            }
            default:
                throw new UnsupportedOperationException("URI desconhecida: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        db.close();
        return returnUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = rideDb.getWritableDatabase();
        final int match = uriMatcher.match(uri);
        int numDeleted;
        switch (match){
            case CARONA:
                numDeleted = db.delete(RideContract.CaronaEntry.TABLE_NAME, selection, selectionArgs);
                db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" +
                        RideContract.CaronaEntry.TABLE_NAME + "'");
                break;
            case CARONA_WITH_ID:
                numDeleted = db.delete(
                        RideContract.CaronaEntry.TABLE_NAME,
                        RideContract.CaronaEntry._ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))});
                db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" +
                        RideContract.CaronaEntry.TABLE_NAME + "'");
                break;
            default:
                throw new UnsupportedOperationException("URI desconhecida: " + uri);
        }
        return numDeleted;
    }

    public int bulkInsert(Uri uri, ContentValues[] values){
        final SQLiteDatabase db = rideDb.getWritableDatabase();
        final int match = uriMatcher.match(uri);
        switch (match){
            case CARONA:
                db.beginTransaction();
                int numInserted = 0;
                try{
                    for(ContentValues value : values){
                        if(value == null){
                            throw new IllegalArgumentException("Cannot have null content values");
                        }
                        long _id = -1;
                        _id = db.insert(RideContract.CaronaEntry.TABLE_NAME, null, value);
                        if(_id != -1){
                            numInserted++;
                        }
                    }
                    if(numInserted > 0){
                        db.setTransactionSuccessful();
                    }
                } finally {
                    db.endTransaction();
                }
                if(numInserted > 0){
                    getContext().getContentResolver().notifyChange(uri, null);
                }
                return numInserted;
            default:
                return super.bulkInsert(uri, values);
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = rideDb.getWritableDatabase();
        int numUpdated = 0;
        if(values == null){
            throw new IllegalArgumentException("Cannot have null content values");
        }
        switch (uriMatcher.match(uri)){
            case CARONA:{
                numUpdated = db.update(RideContract.CaronaEntry.TABLE_NAME,
                        values,
                        selection,
                        selectionArgs);
                break;
            }
            case CARONA_WITH_ID:{
                numUpdated = db.update(RideContract.CaronaEntry.TABLE_NAME,
                        values,
                        RideContract.CaronaEntry._ID + " = ?",
                        new String[] {String.valueOf(ContentUris.parseId(uri))});
                break;
            }
            default:
                throw new UnsupportedOperationException("URI desconhecida: " + uri);
        }
        if (numUpdated > 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }

        return numUpdated;
    }
}
