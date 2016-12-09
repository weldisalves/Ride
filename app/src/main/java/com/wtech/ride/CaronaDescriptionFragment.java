package com.wtech.ride;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import app.mobile.ccomp.ufes.david.lunchbox.Data.Contract;

/**
 * A placeholder fragment containing a simple view.
 *
 */


public class CaronaDescriptionFragment extends Fragment {
    static final String DETAIL_URI = "URI";

    private static final String RIDE_SHARE_HASHTAG = " #RideApp";

    private Uri mUri;

    private static final int DETAIL_LOADER = 0;

    private static final String[] DETAIL_COLUMNS = {
            Contract.FoodEntry.TABLE_NAME + "." + Contract.FoodEntry._ID,
            Contract.FoodEntry.COLUMN_FOOD_NAME,
            Contract.FoodEntry.COLUMN_FOOD_CATEGORY,
            Contract.FoodEntry.COLUMN_FOOD_PRICE,
            Contract.FoodEntry.COLUMN_CHEFF_NAME,
            Contract.FoodEntry.COLUMN_CHEFF_CONTACT,
            Contract.FoodEntry.COLUMN_QTD_HITS,
            Contract.FoodEntry.COLUMN_RATING
    };

    // These indices are tied to DETAIL_COLUMNS.  If DETAIL_COLUMNS changes, these
    // must change.
    public static final int COL_FOOD_ID = 0;
    public static final int COLUMN_FOOD_NAME = 1;
    public static final int COLUMN_FOOD_CATEGORY = 2;
    public static final int COLUMN_FOOD_PRICE = 3;
    public static final int COLUMN_CHEFF_NAME = 4;
    public static final int COLUMN_CHEFF_CONTACT = 5;
    public static final int COLUMN_QTD_HITS = 6;
    public static final int COLUMN_RATING = 7;

    ImageView imgVeiculo;
    ImageView imgPerfil;
    TextView nome;
    TextView profissao;
    RatingBar ratingBar;
    ListView passageiros;
    TextView numeroDeVagas;
    Button solicitarCarona;

    public CaronaDescriptionFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle arguments = getArguments();
        if (arguments != null) {
            mUri = arguments.getParcelable(CaronaDescriptionFragment.DETAIL_URI);
        }

        View description = inflater.inflate(R.layout.fragment_carona_description, container, false);

        imgVeiculo = (ImageView)description.findViewById(R.id.imgVeiculo);
        imgPerfil = (ImageView)description.findViewById(R.id.imgPerfil);
        nome = (TextView)description.findViewById(R.id.txtViewNome);
        profissao = (TextView)description.findViewById(R.id.txtViewProfissao);
        ratingBar = (RatingBar)description.findViewById(R.id.ratingBarCondutor);
        passageiros = (ListView)description.findViewById(R.id.listViewPassageiros);
        numeroDeVagas = (TextView)description.findViewById(R.id.txtViewNumeroDeVagas);
        solicitarCarona = (Button)description.findViewById(R.id.btSolicitarCarona);

        return description;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        getLoaderManager().initLoader(DETAIL_LOADER, null, this);
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        if ( null != mUri ) {
            // Now create and return a CursorLoader that will take care of
            // creating a Cursor for the data being displayed.
            return new CursorLoader(
                    getActivity(),
                    mUri,
                    DETAIL_COLUMNS,
                    null,
                    null,
                    null
            );
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor != null && cursor.moveToFirst()) {
            // Read weather condition ID from cursor
            int weatherId = cursor.getInt(COL_FOOD_ID);

            String sNome = cursor.getString(cursor.getColumnIndexOrThrow(Contract.FoodEntry.COLUMN_FOOD_NAME));
            String sCategory = cursor.getString(cursor.getColumnIndexOrThrow(Contract.FoodEntry.COLUMN_FOOD_CATEGORY));
            float fRating = cursor.getFloat(cursor.getColumnIndexOrThrow(Contract.FoodEntry.COLUMN_RATING));
            String sCheffName = cursor.getString(cursor.getColumnIndexOrThrow(Contract.FoodEntry.COLUMN_CHEFF_NAME));
            String sCheffContact = cursor.getString(cursor.getColumnIndexOrThrow(Contract.FoodEntry.COLUMN_CHEFF_CONTACT));
            float fPrice = cursor.getFloat(cursor.getColumnIndexOrThrow(Contract.FoodEntry.COLUMN_FOOD_PRICE));
            int iQtdHits = cursor.getInt(cursor.getColumnIndexOrThrow(Contract.FoodEntry.COLUMN_QTD_HITS));

            txtName.setText(sNome);
            txtCategory.setText(sCategory);
            ratingBar.setRating(fRating);
            txtCheffName.setText(sCheffName);
            txtCheffContact.setText(sCheffContact);
            txtPrice.setText("" + fPrice);
            txtQtdHits.setText("" + iQtdHits);

        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) { }

}