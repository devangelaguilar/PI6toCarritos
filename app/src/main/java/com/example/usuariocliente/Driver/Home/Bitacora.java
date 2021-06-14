package com.example.usuariocliente.Driver.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.usuariocliente.Models.Auto;
import com.example.usuariocliente.Models.Cliente;
import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.Models.Renta;
import com.example.usuariocliente.R;

import java.util.HashMap;
import java.util.Map;

public class Bitacora extends AppCompatActivity {
    Renta renta;
    TextView modelo, placas;
    EditText km, notas;
    ImageView foto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitacora);
        Intent intent = getIntent();
        renta = (Renta) intent.getSerializableExtra("renta");
        Auto auto = Globals.getAuto(this, renta);
        Cliente cliente = Globals.getCliente(this, renta);
        foto = findViewById(R.id.img);
        Glide.with(this).load("https://cinderellaride.000webhostapp.com/assets/img/autos/" + auto.getId_vehiculo() + ".png").asBitmap().into(foto);
        modelo = findViewById(R.id.modelo);
        modelo.setText(auto.getModelo());
        placas = findViewById(R.id.placas);
        placas.setText(auto.getPlacas());

        km = findViewById(R.id.km);
        notas = findViewById(R.id.notas);
    }

    public void recolectar(View view) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Globals.ip + "RecolectarAuto.php", response -> {
            if (response.equals("MENSAJE")){
                Globals.getClientes(this);
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
                parametros.put("id_usuario", String.valueOf(renta.getId_usuario()));
                parametros.put("km", km.getText().toString());
                parametros.put("notas", notas.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}