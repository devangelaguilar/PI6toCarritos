package com.example.usuariocliente.UsuarioClienteMenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.util.Pair;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usuariocliente.MainActivity;
import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.R;
import com.example.usuariocliente.UsuarioClienteConfiguracion.UsuarioClienteUserOptions;
import com.example.usuariocliente.UsuarioClienteHistorial.UsuarioClienteHistorial;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class UsuarioClienteMenu extends AppCompatActivity {
    ImageView Back,Historial,Usuario,CerrarSesion;
    Intent i;
    CardView ubicacion, fechas;
    Button buscar;
    TextView dia_mes_inicio, dia_mes_fin, dia_inicio, dia_fin;
    String date_start, date_finish;
    ListView AutosDisponibles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario_cliente_menu);
        setTitle("Menu Cliente");
        Back = findViewById(R.id.backarrow);
        Historial = findViewById(R.id.historialicon);
        Usuario = findViewById(R.id.Usuarioicon);
        CerrarSesion = findViewById(R.id.cerrarsesionicon);
        buscar = findViewById(R.id.btn_buscar);
        ubicacion = findViewById(R.id.ubicacion);
        fechas = findViewById(R.id.fechas);
        dia_mes_inicio = findViewById(R.id.dia_mesInicio);
        dia_mes_fin = findViewById(R.id.dia_mesFin);
        dia_inicio = findViewById(R.id.dia_inicio);
        dia_fin = findViewById(R.id.dia_fin);

        //DatePicker Builder
        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        MaterialDatePicker materialDatePicker = builder.build();
        builder.setTitleText("Seleccione el rango de fechas");
        //DatePicker Builder

        buscar.setOnClickListener(v -> {
            i = new Intent(getApplicationContext(), AutoList.class);
            guardarSP();
            startActivity(i);
        });

        ubicacion.setOnClickListener(v -> { Toast.makeText(getApplicationContext(), "ubicacion", Toast.LENGTH_SHORT).show(); });

        fechas.setOnClickListener(v -> { materialDatePicker.show(getSupportFragmentManager(), "Seleccionar fechas"); });

        materialDatePicker.addOnPositiveButtonClickListener(selection -> {
            Pair selectedDates = (Pair) materialDatePicker.getSelection();
            final Pair<Date, Date> rangeDate = new Pair<>(new Date((Long) selectedDates.first), new Date((Long) selectedDates.second));
            Date startDate = rangeDate.first;
            Date endDate = rangeDate.second;
            dia_inicio.setText(Globals.getDayNumber(startDate));
            dia_fin.setText(Globals.getDayNumber(endDate));
            dia_mes_inicio.setText(Globals.getDayOfWeek(startDate) + " | " + Globals.getMonth(startDate));
            dia_mes_fin.setText(Globals.getDayOfWeek(endDate) + " | " + Globals.getMonth(endDate));
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
            date_start = simpleFormat.format(startDate);
            date_finish = simpleFormat.format(endDate);
            //Toast.makeText(getApplicationContext(), "Fechas" + simpleFormat.format(startDate) + " segundo:" + simpleFormat.format(endDate), Toast.LENGTH_SHORT).show();
        });

        //Navegation menu

        Back.setOnClickListener(v -> {
            i = new Intent(getApplicationContext(), UsuarioClienteMenu.class);
            Toast.makeText(getApplicationContext(), "Regresar a Pantalla anterior, pero en este men[u no deeber[ia de salir esta flecha, acoplar navegaci[on de iconos a un menu", Toast.LENGTH_LONG).show();
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
            i = new Intent(getApplicationContext(), MainActivity.class);
            Globals.cerrarSesion(getApplicationContext());
            Toast.makeText(getApplicationContext(), "Regresar a Login", Toast.LENGTH_SHORT).show();
            startActivity(i);
        });

        //Navegation Menu


    }
    private void guardarSP() {
        SharedPreferences preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ubicacion", "aqui");
        editor.putString("fecha_inicio", date_start);
        editor.putString("fecha_fin", date_finish);
        editor.apply();
    }
}