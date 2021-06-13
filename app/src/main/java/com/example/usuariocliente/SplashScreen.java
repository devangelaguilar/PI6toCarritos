package com.example.usuariocliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usuariocliente.Cliente.ClienteMenu;
import com.example.usuariocliente.Driver.DriverMenu;
import com.example.usuariocliente.Models.Cliente;
import com.example.usuariocliente.Models.Globals;

public class SplashScreen extends AppCompatActivity {
    ImageView logo;
    TextView cr;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        logo = findViewById(R.id.logo);
        cr = findViewById(R.id.cr);


        //Animaciones
        Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.up);
        Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.down);

        cr.setAnimation(anim2);
        logo.setAnimation(anim1);

        checkUser();
    }

    private void checkUser(){
        SharedPreferences preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        Boolean sesion_iniciada = preferences.getBoolean("sesion_iniciada", Boolean.FALSE);
        int clase_usuariosp = preferences.getInt("clase_usuario", 0);

        if (sesion_iniciada.equals(Boolean.TRUE)){
            if(clase_usuariosp == 1){
                //Globals.getPics(this);
                Globals.getClientesU(getApplicationContext());
            } else if (clase_usuariosp == 2){
                Globals.getAutos(getApplicationContext());
            }
        } else {
            i = new Intent(this, Login.class);
            startActivity(i);
            finish();
        }
    }
}