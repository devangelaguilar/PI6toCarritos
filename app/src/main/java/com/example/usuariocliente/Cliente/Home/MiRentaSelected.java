package com.example.usuariocliente.Cliente.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.usuariocliente.Cliente.ClienteMenu;
import com.example.usuariocliente.Models.Auto;
import com.example.usuariocliente.Models.Cliente;
import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.Models.Maps.MapActivity;
import com.example.usuariocliente.Models.Renta;
import com.example.usuariocliente.R;

import java.util.HashMap;
import java.util.Map;

public class MiRentaSelected extends AppCompatActivity {
    Renta renta;
    TextView modelo, placas;
    ImageView foto;
    CardView punto_de_entrega;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_renta_selected);
        Intent intent = getIntent();
        renta = (Renta) intent.getSerializableExtra("renta");
        Auto auto = Globals.getAuto(this, renta);
        foto = findViewById(R.id.img);
        Glide.with(this).load("https://cinderellaride.000webhostapp.com/assets/img/autos/" + auto.getId_vehiculo() + ".png").asBitmap().into(foto);
        modelo = findViewById(R.id.modelo);
        modelo.setText(auto.getModelo());
        placas = findViewById(R.id.placas);
        placas.setText(auto.getPlacas());
        punto_de_entrega = findViewById(R.id.punto_de_entrega);
        punto_de_entrega.setOnClickListener(v -> {
            Intent i = new Intent(this, MapActivity.class);
            i.putExtra("Location", renta.getUbicacion());
            startActivity(i);
        });
    }

    public void back(View view) {
        finish();
    }

    public void entregar(View view) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Globals.ip + "EntregarAutoRentado.php", response -> {
            if (response.equals("MENSAJE")){
                Toast.makeText(getApplicationContext(), "Por favor, espere a que el conductor llegue.", Toast.LENGTH_LONG).show();
                Intent i = new Intent(this, ClienteMenu.class);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "" + response, Toast.LENGTH_SHORT).show();
            }
            //Toast.makeText(getApplicationContext(), "" + response, Toast.LENGTH_SHORT).show();
        }, error -> Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show()){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<>();

                // En este metodo se hace el envio de valores de la aplicacion al servidor
                parametros.put("id_renta", String.valueOf(renta.getId_renta()));
                parametros.put("id_vehiculo", String.valueOf(renta.getId_vehiculo()));

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}