package com.example.usuariocliente.Cliente.Home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;

import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.R;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ClienteHome extends Fragment {
    ImageView Back, Historial, Usuario, CerrarSesion;
    Intent i;
    CardView ubicacion, fechas;
    Button buscar;
    TextView dia_mes_inicio, dia_mes_fin, dia_inicio, dia_fin;
    String date_start, date_finish;

    Pair<Date, Date> rangeDate = null;
    Context context;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_cliente, container, false);
        context = getContext();

        Back = view.findViewById(R.id.backarrow);
        buscar = view.findViewById(R.id.btn_buscar);
        ubicacion = view.findViewById(R.id.ubicacion);
        fechas = view.findViewById(R.id.fechas);
        dia_mes_inicio = view.findViewById(R.id.dia_mesInicio);
        dia_mes_fin = view.findViewById(R.id.dia_mesFin);
        dia_inicio = view.findViewById(R.id.dia_inicio);
        dia_fin = view.findViewById(R.id.dia_fin);

        //DatePicker Builder
        MaterialDatePicker.Builder<Pair<Long, Long>> builder = MaterialDatePicker.Builder.dateRangePicker();
        MaterialDatePicker materialDatePicker = builder.build();
        builder.setTitleText("Seleccione el rango de fechas");

        //DatePicker Builder

        buscar.setOnClickListener(v -> {
            i = new Intent(context, AutoList.class);
            guardarSP();
            startActivity(i);
        });

        ubicacion.setOnClickListener(v -> {
            //Toast.makeText(getApplicationContext(), "ubicacion", Toast.LENGTH_SHORT).show();
            Uri gmmIntentUri = Uri.parse("geo:"+ Globals.getCurrentLocation().getLatitude()+","+Globals.getCurrentLocation().getLongitude());
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
            mapIntent.setPackage("com.google.android.apps.maps");
            startActivity(mapIntent);
        });

        fechas.setOnClickListener(v -> {
            materialDatePicker.show(getParentFragmentManager(), "Seleccionar fechas");
        });

        materialDatePicker.addOnPositiveButtonClickListener(selection -> {
            Pair selectedDates = (Pair) materialDatePicker.getSelection();
            rangeDate = new Pair<>(new Date((Long) selectedDates.first), new Date((Long) selectedDates.second));
            Date startDate = rangeDate.first;
            Date endDate = rangeDate.second;
            dia_inicio.setText(Globals.getDayNumber(startDate));
            dia_fin.setText(Globals.getDayNumber(endDate));
            dia_mes_inicio.setText(Globals.getDayOfWeek(startDate) + " | " + Globals.getMonth(startDate));
            dia_mes_fin.setText(Globals.getDayOfWeek(endDate) + " | " + Globals.getMonth(endDate));
            SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
            date_start = simpleFormat.format(startDate);
            date_finish = simpleFormat.format(endDate);
            //Toast.makeText(getApplicationContext(), "Fechas" + selectedDates.first + " segundo:" + selectedDates.second, Toast.LENGTH_SHORT).show();
        });
        return view;
    }



    private void guardarSP() {
        SharedPreferences preferences = getActivity().getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("ubicacion", "aqui");
        editor.putString("fecha_inicio", date_start);
        editor.putString("fecha_fin", date_finish);
        editor.apply();
    }
}
