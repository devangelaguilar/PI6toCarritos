package com.example.usuariocliente.UsuarioDriverInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.usuariocliente.R;
import com.example.usuariocliente.UsuarioDriverHistorial.UsuarioDriverHistorial;
import com.example.usuariocliente.UsuarioDriverMenu.UsuarioDriverMenu;

public class UsuarioDriverInfo extends AppCompatActivity {
    ImageView Back, Historial, Usuario, CerrarSesion;
    Intent i;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_driver_info);
        Back = findViewById(R.id.backarrow);
        Historial = findViewById(R.id.historialicon);
        Usuario = findViewById(R.id.Usuarioicon);
        CerrarSesion = findViewById(R.id.cerrarsesionicon);

        //NAvegation Menu

        Back.setOnClickListener(v -> {
            i = new Intent(getApplicationContext(), UsuarioDriverHistorial.class);
            Toast.makeText(getApplicationContext(), "Regresar a Pantalla anterior, en este caso el menu de historial", Toast.LENGTH_LONG).show();
            startActivity(i);
        });
        Historial.setOnClickListener(v -> {
            i = new Intent(getApplicationContext(), UsuarioDriverHistorial.class);
            Toast.makeText(getApplicationContext(), "Historial", Toast.LENGTH_SHORT).show();
            startActivity(i);
        });
        Usuario.setOnClickListener(v -> {
            i = new Intent(getApplicationContext(), UsuarioDriverInfo.class);
            Toast.makeText(getApplicationContext(), "Opciones de usuario", Toast.LENGTH_SHORT).show();
            startActivity(i);
        });
        CerrarSesion.setOnClickListener(v -> {
            i = new Intent(getApplicationContext(), UsuarioDriverMenu.class);
            Toast.makeText(getApplicationContext(), "Regresar a Login", Toast.LENGTH_SHORT).show();
            startActivity(i);
        });

        //Navegation Menu
    }
}