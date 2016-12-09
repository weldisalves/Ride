package com.wtech.ride.data;

import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadData extends AsyncTask<Void,Void, ContentValues[]> {
    private Context context;
    ProgressDialog pDialog;
    public DownloadData(Context context) {
        this.context = context;
        pDialog = ProgressDialog.show(context, "Aguarde", "Buscando arquivos do servidor.");

    }

    protected ContentValues[] doInBackground(Void... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String resultStringJson = null;


        try {

            URL url = new URL("https://raw.githubusercontent.com/davidmorosini/LunchBox/arquivo_JSON/foods.JSON");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            resultStringJson = buffer.toString();

        } catch (IOException e) {
            // If the code didn't successfully get the weather data, there's no point in attemping
            // to parse it.
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                }
            }
        }
        try {
            return getFoodsFromJSON(resultStringJson);

        } catch (Exception e) {
        }


        return null;
    }

    @Override
    protected void onPostExecute(ContentValues[] result) {
        super.onPostExecute(result);

        if (result != null) {
            int insert = context.getContentResolver().bulkInsert(RideContract.CaronaEntry.CONTENT_URI, result);
        }
        pDialog.dismiss();
    }

    private ContentValues[] getFoodsFromJSON(String jsonString) throws JSONException {

        //o json salvo no srvidor, é um array de foods
        JSONArray mJsonArray = new JSONArray(jsonString);
        JSONObject mJsonObject;

        ContentValues[] listCaronas = new ContentValues[mJsonArray.length()];
        ContentValues ctValues;

        for (int i = 0; i < mJsonArray.length(); i++) {
            ctValues = new ContentValues();
            //pegando cada item do array de foods do json
            mJsonObject = mJsonArray.getJSONObject(i);


            ctValues.put(RideContract.CaronaEntry.COLUMN_CONDUTOR_NOME,mJsonObject.getString("condutor_nome"));
            ctValues.put(RideContract.CaronaEntry.COLUMN_CONDUTOR_AVALIACAO,mJsonObject.getInt("condutor_avaliacao"));
            ctValues.put(RideContract.CaronaEntry.COLUMN_VEICULO_MODELO,mJsonObject.getString("veiculo_modelo"));
            ctValues.put(RideContract.CaronaEntry.COLUMN_VEICULO_COR,mJsonObject.getString("veiculo_cor"));
            ctValues.put(RideContract.CaronaEntry.COLUMN_VEICULO_PLACA,mJsonObject.getString("veiculo_placa"));
            ctValues.put(RideContract.CaronaEntry.COLUMN_VEICULO_IMAGEM,0);
            ctValues.put(RideContract.CaronaEntry.COLUMN_CARONA_DESTINO,mJsonObject.getString("carona_destino"));
            ctValues.put(RideContract.CaronaEntry.COLUMN_CARONA_PARTIDA,mJsonObject.getString("carona_origem"));
            ctValues.put(RideContract.CaronaEntry.COLUMN_CARONA_HORA_PARTIDA,mJsonObject.getString("carona_hora_partida"));
            ctValues.put(RideContract.CaronaEntry.COLUMN_CARONA_QTD_VAGAS,mJsonObject.getInt("carona_quantidade_de_vagas"));
            
            listCaronas[i] = ctValues;
        }
        return listCaronas;
    }
}