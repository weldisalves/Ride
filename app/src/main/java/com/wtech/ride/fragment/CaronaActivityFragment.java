package com.wtech.ride.fragment;

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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.wtech.ride.R;
import com.wtech.ride.activity.CaronaDescriptionActivity;
import com.wtech.ride.adapter.CaronaCursorAdapter;
import com.wtech.ride.data.DownloadData;
import com.wtech.ride.data.RideContract;

/**
 * A placeholder fragment containing a simple view.
 */
public class CaronaActivityFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{


    private CaronaCursorAdapter caronaCursorAdapter;

    private SwipeRefreshLayout swipe;

    private ProgressDialog pDialog;


    private static final int CARONA_LOADER = 0;
    // For the forecast view we're showing only a small subset of the stored data.
    // Specify the columns we need.
    private static final String[] CARONA_COLUMNS = {

            RideContract.CaronaEntry.TABLE_NAME + "." +  RideContract.CaronaEntry._ID,
            RideContract.CaronaEntry.COLUMN_CONDUTOR_AVALIACAO,
            RideContract.CaronaEntry.COLUMN_CONDUTOR_NOME,
            RideContract.CaronaEntry.COLUMN_CARONA_HORA_PARTIDA,
            RideContract.CaronaEntry.COLUMN_CARONA_PARTIDA,
            RideContract.CaronaEntry.COLUMN_CARONA_DESTINO
    };

    // These indices are tied to FORECAST_COLUMNS.  If FORECAST_COLUMNS changes, these
    // must change.
    static final int COLUMN_CARONA_ID = 0;
    static final int COLUMN_CONDUTOR_AVALIACAO = 1;
    static final int COLUMN_CONDUTOR_NOME = 2;
    static final int COLUMN_CARONA_HORA_PARTIDA = 3;
    static final int COLUMN_CARONA_PARTIDA = 4;
    

    public interface Callback {

        public void onItemSelected(Uri uri);
    }

    public CaronaActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        caronaCursorAdapter = new CaronaCursorAdapter(getActivity(), null, 0);

        View rootView = inflater.inflate(R.layout.fragment_carona, container, false);

        // Get a reference to the ListView, and attach this adapter to it.
        ListView listView = (ListView) rootView.findViewById(R.id.listViewCarona);
        listView.setAdapter(caronaCursorAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Cursor cursor = (Cursor) adapterView.getItemAtPosition(position);
                if (cursor != null) {
                    Intent intent = new Intent(getActivity(), CaronaDescriptionActivity.class)
                            .setData(RideContract.CaronaEntry.buildCarona(
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
                        getContext().getContentResolver().delete(RideContract.CaronaEntry.CONTENT_URI, null, null);
                        new DownloadData(getContext()).execute();
                        swipe.setRefreshing(false);
                    }
                }, 0);
            }
        });

        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(CARONA_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

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

    void onDataChanged( ) {
        updateData();
        getLoaderManager().restartLoader(CARONA_LOADER, null, this);
    }

    void updateData() {

        DownloadData fetchDataTask = new DownloadData(getActivity());
        fetchDataTask.execute();

        getLoaderManager().restartLoader(CARONA_LOADER, null, this);
    }

}
