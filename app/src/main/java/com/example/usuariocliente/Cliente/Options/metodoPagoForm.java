package com.example.usuariocliente.Cliente.Options;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.usuariocliente.Cliente.ClienteMenu;
import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.R;

import java.util.HashMap;
import java.util.Map;

public class metodoPagoForm extends AppCompatActivity {

    Button btntarjeta;
    EditText nametarjeta, numtarjeta, fechatarjeta, cvvtarjeta;
    String id_usuario="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metodo_pago_form);
        //COLOCAR VARIABLE GLOBAL PARA OBTENER EL N[UMERO DE USUARIO!!!!!!


        btntarjeta = findViewById(R.id.btntarjeta);
        btntarjeta.setOnClickListener(v -> {
            if(  !(nametarjeta.getText().toString().isEmpty())  && !(numtarjeta.getText().toString().isEmpty()) && !(fechatarjeta.getText().toString().isEmpty()) && !(cvvtarjeta.getText().toString().isEmpty())) {
            registroMetodoPago();

            Intent i = new Intent(this, ClienteSettings.class);
            startActivity(i);}
            else{
                Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void registroMetodoPago() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Globals.ip + "metodoPago.php", response -> {
            if (response.equals("MENSAJE")){
                Toast.makeText(this, "Añadido correctamente", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getApplicationContext(), "" + response, Toast.LENGTH_SHORT).show();
            }

        }, error -> Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();

                // En este metodo se hace el envio de valores de la aplicacion al servidor
                parametros.put("id_usuario", id_usuario);
                parametros.put("numeracion_tarjeta", numtarjeta.getText().toString());
                parametros.put("fecha_vencimiento", fechatarjeta.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);







    }
}