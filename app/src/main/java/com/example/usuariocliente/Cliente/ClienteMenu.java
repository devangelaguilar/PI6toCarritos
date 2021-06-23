package com.example.usuariocliente.Cliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Toast;

import com.example.usuariocliente.Cliente.History.ClienteHistory;
import com.example.usuariocliente.Cliente.Home.ClienteHome;
import com.example.usuariocliente.Cliente.Options.ClienteSettings;
import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.Models.Location.MyLocation;
import com.example.usuariocliente.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class ClienteMenu extends AppCompatActivity {
    static ClienteMenu instance;
    LocationRequest locationRequest;
    FusedLocationProviderClient fusedLocationProviderClient;
    public static ClienteMenu getInstance() {
        return instance;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_menu);
        Globals.getClientesR(this);
        cargarSP();
        Toast.makeText(getApplicationContext(), "" + Globals.id_usuario, Toast.LENGTH_LONG).show();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ClienteHome()).commit();

        instance = this;
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        updateLocation();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        Toast.makeText(getApplicationContext(), "Debes aceptar los permisos para continuar.", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                }).check();
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = item -> {
        Fragment selectedFragment = null;

        switch (item.getItemId()) {
            case R.id.nav_home:
                selectedFragment = new ClienteHome();
                break;
            case R.id.nav_history:
                selectedFragment = new ClienteHistory();
                break;
            case R.id.nav_settings:
                selectedFragment = new ClienteSettings();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        return true;
    };

    public void updateLocation() {
        buildLocationRequest();

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            return;
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, getPendingIntent());
    }

    private PendingIntent getPendingIntent() {
        Intent intent = new Intent(this, MyLocation.class);
        intent.setAction(MyLocation.ACTION_PROCESS_UPDATE);
        return PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private void buildLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(3000);
        locationRequest.setSmallestDisplacement(10f);

    }

    public void updatecurrentLocation(Location location){
        this.runOnUiThread(() -> {
            Globals.setCurrentLocation(location);
        });
    }

    private void cargarSP(){
        SharedPreferences preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        int usuario = preferences.getInt("id_unico", 0);

        Globals.id_usuario = usuario;
        //Toast.makeText(this, "a " + Globals.id_usuario, Toast.LENGTH_LONG).show();
    }
}