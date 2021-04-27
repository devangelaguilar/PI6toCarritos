package com.example.usuariocliente.UsuarioClienteMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuariocliente.Models.Auto;
import com.example.usuariocliente.R;
import com.example.usuariocliente.UsuarioClienteConfiguracion.UsuarioClienteUserOptions;
import com.example.usuariocliente.UsuarioClienteHistorial.HistorialSelected;
import com.example.usuariocliente.UsuarioClienteHistorial.UsuarioClienteHistorial;

public class AutoSelected extends AppCompatActivity {
    ImageView Back,Historial,Usuario,CerrarSesion;
    TextView txtmodelo, txtcolor, tipo1, tipo2, tipo3, tipo4, tipo5, tipo6, tipo7;
    Intent i;
    Button btn_rentar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Menu Auto seleccionado");
        setContentView(R.layout.activity_auto_selected);
        Back = findViewById(R.id.backarrow);
        Historial = findViewById(R.id.historialicon);
        Usuario = findViewById(R.id.Usuarioicon);
        CerrarSesion = findViewById(R.id.cerrarsesionicon);
        btn_rentar = findViewById(R.id.btnPagar);

        //Data in sheet
        txtmodelo=findViewById(R.id.txtmodelo);

        txtcolor=findViewById(R.id.txtcolor);

        tipo1=findViewById(R.id.tipo1);

        tipo2=findViewById(R.id.tipo2);

        tipo3=findViewById(R.id.tipo3);

        tipo4=findViewById(R.id.tipo4);

        tipo5=findViewById(R.id.tipo5);

        tipo6=findViewById(R.id.tipo6);

        tipo7=findViewById(R.id.tipo7);
        //end Data in Sheet

        Intent intent = getIntent();
        Auto autodata = (Auto) intent.getSerializableExtra("auto");
        Toast.makeText(this, ""+autodata.getColor(), Toast.LENGTH_SHORT).show();

        //Build Data Sheet
        txtmodelo.setText(autodata.getModelo());
        txtcolor.setText(autodata.getColor());
        tipo1.setText(autodata.getTipo_vehiculo());
        tipo2.setText(autodata.getPlacas());
        tipo3.setText(autodata.getFoto());
        tipo4.setText("Disponible");
        tipo5.setText(autodata.getTransmision());
        tipo6.setText(autodata.getPrecio());
        tipo7.setText(autodata.getPrecio());
        //END Build Data Sheet

        //NAvegation Menu

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

        btn_rentar.setOnClickListener(v -> {
            //Enviar todos los datos a la tabla renta_auto***********************************
        });


    }
}