package com.example.usuariocliente.Cliente.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.usuariocliente.Models.Auto;
import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.R;

import java.util.HashMap;
import java.util.Map;

public class AutoSelected extends AppCompatActivity {
    ImageView foto;
    TextView txtmodelo, placas, transmision, fechas, txttotal;
    Intent i;
    CardView uCard;
    Button btn_rentar;
    Auto autodata;
    String ubicacion, fecha_inicio, fecha_fin;
    String[] f_i, f_f;
    int id_usuario, dif;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Menu Auto seleccionado");
        setContentView(R.layout.activity_auto_selected);
        foto = findViewById(R.id.img);

        btn_rentar = findViewById(R.id.btnPagar);

        cargarSP();
        ubicacion = Globals.getCurrentLocation().getLatitude() + "," + Globals.getCurrentLocation().getLongitude();
        f_i = fecha_inicio.split("-");
        f_f = fecha_fin.split("-");
        dif = Integer.parseInt(f_f[2]) - Integer.parseInt(f_i[2]);

        //Data in sheet
        txtmodelo=findViewById(R.id.AModelo);
        placas = findViewById(R.id.APlacas);
        transmision = findViewById(R.id.transmision);
        fechas = findViewById(R.id.fechas);
        uCard = findViewById(R.id.ubicacionCard);
        txttotal = findViewById(R.id.ATotal);
        //end Data in Sheet

        Intent intent = getIntent();
        autodata = (Auto) intent.getSerializableExtra("auto");
        //Toast.makeText(this, ""+autodata.getColor(), Toast.LENGTH_SHORT).show();
        Glide.with(this).load("https://cinderellaride.000webhostapp.com/assets/img/autos/" + autodata.getId_vehiculo() + ".png").asBitmap().into(foto);
        //Build Data Sheet
        txtmodelo.setText(autodata.getModelo());
        placas.setText(autodata.getPlacas());
        transmision.setText(autodata.getTransmision());
        fechas.setText(fecha_inicio + " - " + fecha_fin);
        double total = Double.parseDouble(autodata.getPrecio()) * dif;
        txttotal.setText("$" + total);
        //END Build Data Sheet

        uCard.setOnClickListener(v -> {
            Uri gmmIntentUri = Uri.parse("geo:"+ ubicacion);
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        });

        btn_rentar.setOnClickListener(v -> {
            //Toast.makeText(getApplicationContext(), " " + id_usuario, Toast.LENGTH_SHORT).show();
            //Globals.rentarAuto(this, ubicacion, fecha_inicio, fecha_fin, autodata);
            Intent i = new Intent(this, SelectCard.class);
            i.putExtra("ubicacion", ubicacion);
            i.putExtra("fecha_inicio", fecha_inicio);
            i.putExtra("fecha_fin", fecha_fin);
            i.putExtra("autodata", autodata);
            startActivity(i);
        });
    }


    private void cargarSP(){
        SharedPreferences preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        int usuario = preferences.getInt("id_unico", 0);
        String inicio = preferences.getString("fecha_inicio", "");
        String fin = preferences.getString("fecha_fin", "");

        id_usuario = usuario;
        fecha_inicio = inicio;
        fecha_fin = fin;
    }


    public void back(View view) { finish(); }
}