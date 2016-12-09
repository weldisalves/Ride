package com.wtech.ride;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.ViewDragHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wtech.ride.data.RideContract;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class CaronaActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private CaronaCursorAdapter caronaCursorAdapter;
    private SwipeRefreshLayout swipe;
    private ProgressDialog pDialog;

    private static final int CARONA_LOADER = 0;

    private static final String[] CARONA_COLUMNS = {
            RideContract.CaronaEntry.TABLE_NAME + "." + RideContract.CaronaEntry._ID,
            RideContract.CaronaEntry.COLUMN_CARONA_IDCONDUTOR,
            RideContract.CaronaEntry.COLUMN_CARONA_NUMERO_VAGAS,
            RideContract.CaronaEntry.COLUMN_CARONA_ID_PONTOPARTIDA,
            RideContract.CaronaEntry.COLUMN_CARONA_ID_DESTINO,
            RideContract.CaronaEntry.COLUMN_CARONA_HORA_PARTIDA
    };

    public final int COLUMN_CARONA_ID = 0;
    public final int COLUMN_CARONA_IDCONDUTOR = 1;
    public final int COLUMN_CARONA_NUMERO_VAGAS = 2;
    public final int COLUMN_CARONA_ID_PONTOPARTIDA = 3;
    public final int COLUMN_CARONA_ID_DESTINO = 4;
    public final int COLUMN_CARONA_HORA_PARTIDA = 5;

    public interface Callback{
        public void onItemSelectes(Uri uri);
    }

    public CaronaActivityFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        caronaCursorAdapter = new CaronaCursorAdapter(getActivity(),null,0);
        View rootView = inflater.inflate(R.layout.fragment_carona,container,false);

        ListView listView = (ListView)rootView.findViewById(R.id.listViewCarona);
        listView.setAdapter(caronaCursorAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Cursor cursor = (Cursor) adapterView.getItemAtPosition(position);
                if(cursor != null){
                    Intent intent = new Intent(getActivity(),CaronaDescriptionActivity.class)
                            .setData(RideContract.CaronaEntry.buildCaronaUri(
                                    cursor.getLong(COLUMN_CARONA_ID)
                            ));
                    startActivity(intent);
                }
            }
        });

        swipe = (SwipeRefreshLayout)rootView.findViewById(R.id.swipeRefreshCarona);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){

            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getContext().getContentResolver().delete(RideContract.CaronaEntry.CONTENT_URI,null,null);
                        new DownloadData(getContext()).execute();
                        swipe.setRefreshing(false);
                    };
                },0);

            }
        });

        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {

        CursorLoader cursorLoader = new CursorLoader(getActivity(),
                RideContract.CaronaEntry.CONTENT_URI,
                CARONA_COLUMNS,
                null,
                null,
                null);

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        caronaCursorAdapter.swapCursor(cursor);

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        caronaCursorAdapter.swapCursor(null);
    }

    void onDataChanged(){
        updateData();
        getLoaderManager().restartLoader(CARONA_LOADER,null,this);
    }

    void updateData(){
        DownloadData fetchDataTask = new DownloadData(getActivity());
        fetchDataTask.execute();

        getLoaderManager().restartLoader(CARONA_LOADER,null,this);
    }
}