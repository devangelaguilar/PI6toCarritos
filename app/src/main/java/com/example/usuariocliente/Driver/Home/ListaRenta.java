package com.example.usuariocliente.Driver.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.usuariocliente.Adapter.RentasAdapter;
import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.Models.Handler;
import com.example.usuariocliente.Models.Renta;
import com.example.usuariocliente.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListaRenta extends AppCompatActivity {
    Intent i;
    RecyclerView rvRentas;
    List<Renta> rentasList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_driver);
        setTitle("Lista de Entregas pendientes");

        rvRentas = findViewById(R.id.rvRentas);
        showList();
        rvRentas.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvRentas.setLayoutManager(llm);

        //Navegation Menu
        //GetRenta gr = new GetRenta(getApplication());
        //gr.execute();
        //Toast.makeText(getApplicationContext(), " " + Globals.listaRentas, Toast.LENGTH_LONG).show();
        //RentasAdapter adapter = new RentasAdapter(Globals.listaRentas);
        //rvRentas.setAdapter(adapter);
    }

    private void showList() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Globals.ip + "getRenta.php", response -> {
            try {
                JSONObject obj = new JSONObject(response);
                JSONArray array = obj.getJSONArray("rentas");
                for (int i = 0; i < array.length(); i++){
                    JSONObject rentaObj = array.getJSONObject(i);
                    Renta a = new Renta(rentaObj.getInt("id_renta"), rentaObj.getInt("id_vehiculo"), rentaObj.getInt("id_usuario"),
                            rentaObj.getString("ubicacion"), rentaObj.getInt("status"), rentaObj.getString("fecha_inicio"), rentaObj.getString("fecha_fin"));
                    if (a.getStatus() == 1)
                        rentasList.add(a);
                }
                RentasAdapter adapter = new RentasAdapter(rentasList);
                rvRentas.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), e + " " + response, Toast.LENGTH_LONG).show();
            }
        }, error -> { });
        Handler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);

    }
}