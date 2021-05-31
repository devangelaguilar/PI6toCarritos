package com.example.usuariocliente.Driver.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.usuariocliente.Models.Maps.MapActivity;
import com.example.usuariocliente.Models.Renta;
import com.example.usuariocliente.R;

public class RentaSelected extends AppCompatActivity {
    Renta renta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renta_selected);
        Intent intent = getIntent();
        renta = (Renta) intent.getSerializableExtra("renta");
    }

    public void openLocation(View view) {
        Intent i = new Intent(this, MapActivity.class);
        i.putExtra("Location", renta.getUbicacion());
        startActivity(i);
    }

    public void back(View view) {
        finish();
    }
}