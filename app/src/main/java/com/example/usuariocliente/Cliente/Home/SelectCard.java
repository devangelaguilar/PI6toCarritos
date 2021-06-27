package com.example.usuariocliente.Cliente.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.usuariocliente.Adapter.CardAdapter;
import com.example.usuariocliente.Cliente.Options.AgregarCard;
import com.example.usuariocliente.Models.Auto;
import com.example.usuariocliente.Models.Card;
import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SelectCard extends AppCompatActivity {
    ArrayList<Card> tarjetaList = new ArrayList<>();
    RecyclerView rvCards;
    ImageView logo;
    TextView texto;
    Button add_card;
    public static String ubicacion, fecha_inicio, fecha_fin;
    public static Auto autodata;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_card);

        Intent i = getIntent();
        ubicacion = i.getStringExtra("ubicacion");
        fecha_inicio = i.getStringExtra("fecha_inicio");
        fecha_fin = i.getStringExtra("fecha_fin");
        autodata = (Auto) i.getSerializableExtra("autodata");
        add_card = findViewById(R.id.addCard);
        add_card.setOnClickListener(v -> {
            Intent intent = new Intent(this, AgregarCardRenta.class);
            intent.putExtra("ubicacion", ubicacion);
            intent.putExtra("fecha_inicio", fecha_inicio);
            intent.putExtra("fecha_fin", fecha_fin);
            intent.putExtra("autodata", autodata);
            startActivity(intent);
        });
        rvCards = findViewById(R.id.rvCards);
        rvCards.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rvCards.setLayoutManager(llm);
        texto = findViewById(R.id.texto);
        logo = findViewById(R.id.logo);
        metodosExistentes(String.valueOf(Globals.id_usuario));
    }
    private void metodosExistentes(String id_usuario) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Globals.ip + "METODOSPAGOLISTAFILLER.php",
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("numeracion_tarjeta");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String numerotarjeta = object.getString("numeracion_tarjeta");
                            String fecha_vencimiento = object.getString("fecha_vencimiento");
                            Card card = new Card(numerotarjeta, fecha_vencimiento);
                            tarjetaList.add(card);
                        }
                        if (tarjetaList.size() == 0){
                            logo.setVisibility(View.VISIBLE);
                            texto.setVisibility(View.VISIBLE);
                            add_card.setVisibility(View.VISIBLE);
                        }
                        CardAdapter adapter = new CardAdapter(tarjetaList, true, ubicacion, fecha_inicio, fecha_fin, autodata);
                        rvCards.setAdapter(adapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        logo.setVisibility(View.VISIBLE);
                        texto.setVisibility(View.VISIBLE);
                        add_card.setVisibility(View.VISIBLE);
                        Toast.makeText(this, "Ingrese un Método de pago", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(this, "Contacte a Soporte", Toast.LENGTH_SHORT).show())
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id_usuario", id_usuario);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void back(View view) {
        finish();
    }
}