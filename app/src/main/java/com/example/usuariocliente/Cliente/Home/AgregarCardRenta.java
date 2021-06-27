package com.example.usuariocliente.Cliente.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.usuariocliente.Cliente.Options.AgregarCard;
import com.example.usuariocliente.Cliente.Options.ListaCards;
import com.example.usuariocliente.Models.Auto;
import com.example.usuariocliente.Models.Cliente;
import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.R;

import java.util.HashMap;
import java.util.Map;

import static com.example.usuariocliente.Models.Globals.id_usuario;

public class AgregarCardRenta extends AppCompatActivity {
    Button btntarjeta;
    EditText nametarjeta, numtarjeta,cvvtarjeta;
    String id_user, fechatarjeta;
    Spinner mestarjeta,aniotarjeta;
    String ubicacion, fecha_inicio, fecha_fin;
    Auto autodata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_card_renta);
        Intent i = getIntent();
        ubicacion = i.getStringExtra("ubicacion");
        fecha_inicio = i.getStringExtra("fecha_inicio");
        fecha_fin = i.getStringExtra("fecha_fin");
        autodata = (Auto) i.getSerializableExtra("autodata");
        cargarSP();
        Cliente cliente = Globals.getCliente(id_usuario);
        id_user = String.valueOf(cliente.getId_Usuario());
        nametarjeta = findViewById(R.id.nametarjeta);
        numtarjeta = findViewById(R.id.numtarjeta);
        mestarjeta = findViewById(R.id.mescard);
        aniotarjeta = findViewById(R.id.aniocard);
        cvvtarjeta = findViewById(R.id.cvvtarjeta);
        btntarjeta = findViewById(R.id.btntarjeta);
        String[] meses = {"01", "02", "03", "04", "05","06", "07", "08", "09", "10", "11", "12"};
        String[] anios = {"21", "22", "23", "24", "25","26","28","29"};
        //metodosExistentes(id_user);

        ArrayAdapter<String> mesesarray= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, meses);
        mesesarray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mestarjeta.setAdapter(mesesarray);

        ArrayAdapter<String> aniosarray= new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, anios);
        aniosarray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aniotarjeta.setAdapter(aniosarray);

        btntarjeta.setOnClickListener(v -> {

            if(  !(nametarjeta.getText().toString().isEmpty())  && !(numtarjeta.getText().toString().isEmpty())  && !(cvvtarjeta.getText().toString().isEmpty())) {
                registroMetodoPago();
            }
            else{
                Toast.makeText(this, "Llene todos los campos", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void registroMetodoPago() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Globals.ip + "metodoPago.php", response -> {
            if (response.equals("MENSAJE")){
                Toast.makeText(this, "Método de pago agregado con éxito", Toast.LENGTH_SHORT).show();
                Globals.rentarAuto(this, ubicacion, fecha_inicio, fecha_fin, autodata);
            } else {
                Toast.makeText(getApplicationContext(), "" + response, Toast.LENGTH_SHORT).show();
            }

        }, error -> Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show()){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<>();
                fechatarjeta = ""+mestarjeta.getSelectedItem().toString()+"/"+aniotarjeta.getSelectedItem().toString();
                ///Toast.makeText(AgregarCard.this, ""+fechatarjeta, Toast.LENGTH_SHORT).show();
                // En este metodo se hace el envio de valores de la aplicacion al servidor
                parametros.put("id_usuario", String.valueOf(id_usuario));
                parametros.put("numeracion_tarjeta", numtarjeta.getText().toString());
                parametros.put("fecha_vencimiento", fechatarjeta);
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void cargarSP(){
        SharedPreferences preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        id_usuario = preferences.getInt("id_unico", 0);
    }

    public void back(View view) {
        Intent i = new Intent(this, ListaCards.class);
        startActivity(i);
        finish();
    }
}