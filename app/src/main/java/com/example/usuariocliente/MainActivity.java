package com.example.usuariocliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.usuariocliente.UsuarioClienteMenu.UsuarioClienteMenu;

public class MainActivity extends AppCompatActivity {
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i = new Intent(MainActivity.this, UsuarioClienteMenu.class);
        startActivity(i);
    }
}