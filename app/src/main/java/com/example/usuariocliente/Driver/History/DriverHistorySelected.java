package com.example.usuariocliente.Driver.History;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.usuariocliente.Models.Auto;
import com.example.usuariocliente.Models.Cliente;
import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.Models.Maps.MapActivity;
import com.example.usuariocliente.Models.Renta;
import com.example.usuariocliente.R;

public class DriverHistorySelected extends AppCompatActivity {
    Renta renta;
    TextView modelo, placas, usuario;
    ImageView foto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_selected_driver);

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
        usuario = findViewById(R.id.usuario);
        usuario.setText(cliente.getNombres() + " " + cliente.getApellido_paterno() + " " + cliente.getApellido_materno());
    }

    public void openLocation(View view) {
        Intent i = new Intent(this, MapActivity.class);
        i.putExtra("Location", renta.getUbicacion());
        startActivity(i);
    }

    public void back(View view) {
        finish();
    }
}