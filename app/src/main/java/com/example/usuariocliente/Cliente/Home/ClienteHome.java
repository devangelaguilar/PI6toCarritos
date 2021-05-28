package com.example.usuariocliente.Cliente.Home;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;

import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.Models.MyLocation;
import com.example.usuariocliente.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ClienteHome extends Fragment {
    ImageView Back, Historial, Usuario, CerrarSesion;
    Intent i;
    CardView ubicacion, fechas;
    Button buscar;
    TextView dia_mes_inicio, dia_mes_fin, dia_inicio, dia_fin;
    String date_start, date_finish;
    static ClienteHome instance;
    LocationRequest locationRequest;
    FusedLocationProviderClient fusedLocationProviderClient;
    Pair<Date, Date> rangeDate = null;
    Context context;

    public static ClienteHome getInstance() {
        return instance;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_cliente, container, false);
        context = getContext();
        Date fecha = new Date();
        //Long fechaHoy = (Long) fecha;
        instance = this;
        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        updateLocation();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(context, "Debes aceptar los permisos para continuar.", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();

        Back = view.findViewById(R.id.backarrow);
        Historial = view.findViewById(R.id.historialicon);
        Usuario = view.findViewById(R.id.Usuarioicon);
        CerrarSesion = view.findViewById(R.id.cerrarsesionicon);
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

    public void updateLocation() {
        buildLocationRequest();

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context); //5:19 https://www.youtube.com/watch?v=4xcrZcnBk60
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, getPendingIntent());
    }

    private PendingIntent getPendingIntent() {
        Intent intent = new Intent(context, MyLocation.class);
        intent.setAction(MyLocation.ACTION_PROCESS_UPDATE);
        return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void buildLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setSmallestDisplacement(10f);

    }

    public void updatecurrentLocation(Location location){
        getActivity().runOnUiThread(() -> {
            Globals.setCurrentLocation(location);
        });
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
