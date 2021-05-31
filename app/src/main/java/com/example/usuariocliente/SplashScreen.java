package com.example.usuariocliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usuariocliente.Models.Globals;

public class SplashScreen extends AppCompatActivity {
    ImageView logo;
    TextView cr;
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

        Globals.getClientes(getApplicationContext());
        Globals.getAutos(getApplicationContext());
    }
}