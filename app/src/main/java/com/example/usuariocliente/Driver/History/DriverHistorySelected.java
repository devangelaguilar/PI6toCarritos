package com.example.usuariocliente.Driver.History;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.usuariocliente.R;

public class DriverHistorySelected extends AppCompatActivity {
    ImageView Back,Historial,Usuario,CerrarSesion;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_selected_driver);
        Back = findViewById(R.id.backarrow);
        Historial = findViewById(R.id.historialicon);
        Usuario = findViewById(R.id.Usuarioicon);
        CerrarSesion = findViewById(R.id.cerrarsesionicon);

    }
}