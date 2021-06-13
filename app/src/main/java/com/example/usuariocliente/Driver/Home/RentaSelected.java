package com.example.usuariocliente.Driver.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.Models.Maps.MapActivity;
import com.example.usuariocliente.Models.Renta;
import com.example.usuariocliente.R;

import java.util.HashMap;
import java.util.Map;

import static com.example.usuariocliente.Models.Globals.id_usuario;

public class RentaSelected extends AppCompatActivity {
    Renta renta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renta_selected);
        Intent intent = getIntent();
        renta = (Renta) intent.getSerializableExtra("renta");
    }

    public void openLocation(View view) {
        Intent i = new Intent(this, MapActivity.class);
        i.putExtra("Location", renta.getUbicacion());
        startActivity(i);
    }

    public void back(View view) {
        finish();
    }

    public void entregar(View view) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Globals.ip + "EntregarAuto.php", response -> {
            if (response.equals("MENSAJE")){
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