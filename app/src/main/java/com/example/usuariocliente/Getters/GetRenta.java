package com.example.usuariocliente.Getters;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.Models.Handler;
import com.example.usuariocliente.Models.Renta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetRenta extends AsyncTask<Void, Void, List<Renta>> {
    ProgressDialog pd;
    Context c;

    public GetRenta(Context c) {
        this.c = c;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd = new ProgressDialog(c);
        pd.setTitle("Obteniendo datos");
        pd.setMessage("Descargando datos... Espere un momento");
        //pd.show();

    }
    @Override
    protected List<Renta> doInBackground(Void... voids) { return showListRenta(c); }

    @Override
    protected void onPostExecute(List<Renta> response) {
        super.onPostExecute(response);
        Globals.setRentasList(response);

        //pd.dismiss();
    }

    public static List<Renta> showListRenta(Context c) {
        List<Renta> rentasList = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Globals.ip + "getRenta.php", response -> {
            try {
                JSONObject obj = new JSONObject(response);
                JSONArray array = obj.getJSONArray("rentas");
                for (int i = 0; i < array.length(); i++){
                    JSONObject rentaObj = array.getJSONObject(i);
                    Renta a = new Renta(rentaObj.getInt("id_renta"), rentaObj.getInt("id_vehiculo"), rentaObj.getInt("id_usuario"),
                            rentaObj.getString("ubicacion"), rentaObj.getInt("status"), rentaObj.getString("fecha_inicio"), rentaObj.getString("fecha_fin"));
                    rentasList.add(a);
                    Toast.makeText(c, "GetRenta " + rentasList.get(0).getFecha_inicio(), Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(c, e + " " + response, Toast.LENGTH_LONG).show();
            }
        }, error -> { });
        Handler.getInstance(c).addToRequestQueue(stringRequest);
        return rentasList;
    }

}
