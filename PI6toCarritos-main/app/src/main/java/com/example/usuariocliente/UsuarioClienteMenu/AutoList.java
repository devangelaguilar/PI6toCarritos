package com.example.usuariocliente.UsuarioClienteMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.usuariocliente.R;
import com.example.usuariocliente.UsuarioClienteConfiguracion.UsuarioClienteUserOptions;
import com.example.usuariocliente.UsuarioClienteHistorial.HistorialSelected;
import com.example.usuariocliente.UsuarioClienteHistorial.UsuarioClienteHistorial;


public class AutoList extends AppCompatActivity {
    ImageView Back,Historial,Usuario,CerrarSesion;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_list);
        setTitle("Menu Lista de Autos");
        Back = findViewById(R.id.backarrow);
        Historial = findViewById(R.id.historialicon);
        Usuario = findViewById(R.id.Usuarioicon);
        CerrarSesion = findViewById(R.id.cerrarsesionicon);

        //Navegation Menu

        Back.setOnClickListener(v -> {
            i = new Intent(getApplicationContext(), UsuarioClienteMenu.class);
            Toast.makeText(getApplicationContext(), "Regresar a Pantalla anterior, en este caso el menu de UsuarioClienteMenu", Toast.LENGTH_LONG).show();
            startActivity(i);
        });
        Historial.setOnClickListener(v -> {
            i = new Intent(getApplicationContext(), UsuarioClienteHistorial.class);
            Toast.makeText(getApplicationContext(), "Historial", Toast.LENGTH_SHORT).show();
            startActivity(i);
        });
        Usuario.setOnClickListener(v -> {
            i = new Intent(getApplicationContext(), UsuarioClienteUserOptions.class);
            Toast.makeText(getApplicationContext(), "Opciones de usuario", Toast.LENGTH_SHORT).show();
            startActivity(i);
        });
        CerrarSesion.setOnClickListener(v -> {
            i = new Intent(getApplicationContext(), UsuarioClienteMenu.class);
            Toast.makeText(getApplicationContext(), "Regresar a Login", Toast.LENGTH_SHORT).show();
            startActivity(i);
        });

        //Navegation Menu
    }
}