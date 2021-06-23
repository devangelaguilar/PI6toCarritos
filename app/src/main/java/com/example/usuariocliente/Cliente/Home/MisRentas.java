package com.example.usuariocliente.Cliente.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.usuariocliente.Adapter.HistoryRentasClienteAdapter;
import com.example.usuariocliente.Adapter.MisRentasAdapter;
import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.Models.Handler;
import com.example.usuariocliente.Models.Renta;
import com.example.usuariocliente.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MisRentas extends AppCompatActivity {
    RecyclerView rvRentas;
    List<Renta> autosList = new ArrayList<>();
    TextView texto;
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_rentas);
        rvRentas = findViewById(R.id.rvRentas);
        showList();

        rvRentas.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvRentas.setLayoutManager(llm);
        texto = findViewById(R.id.texto);
        logo = findViewById(R.id.logo);
    }

    public void back(View view) { finish(); }

    private void showList() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Globals.ip + "getRenta.php", response -> {
            try {
                JSONObject obj = new JSONObject(response);
                JSONArray array = obj.getJSONArray("rentas");
                for (int i = 0; i < array.length(); i++){
                    JSONObject rentaObj = array.getJSONObject(i);
                    Renta a = new Renta(rentaObj.getInt("id_renta"), rentaObj.getInt("id_vehiculo"), rentaObj.getInt("id_usuario"),
                            rentaObj.getString("ubicacion"), rentaObj.getInt("status"), rentaObj.getString("fecha_inicio"), rentaObj.getString("fecha_fin"));
                    if (a.getId_usuario() == Globals.id_usuario && a.getStatus() == 1)
                        autosList.add(a);
                }
                if (autosList.size() == 0){
                    logo.setVisibility(View.VISIBLE);
                    texto.setVisibility(View.VISIBLE);
                }
                //Toast.makeText(context, autosList.size() + " ", Toast.LENGTH_LONG).show();
                if (autosList.size() > 0) {
                    MisRentasAdapter adapter = new MisRentasAdapter(autosList);
                    rvRentas.setAdapter(adapter);
                }


            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(this, e + " " + response, Toast.LENGTH_LONG).show();
            }
        }, error -> { });
        Handler.getInstance(this).addToRequestQueue(stringRequest);

    }
}