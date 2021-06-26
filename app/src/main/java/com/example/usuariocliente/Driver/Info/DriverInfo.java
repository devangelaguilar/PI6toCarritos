package com.example.usuariocliente.Driver.Info;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.usuariocliente.Cliente.Home.MisRentas;
import com.example.usuariocliente.Login;
import com.example.usuariocliente.Models.Card;
import com.example.usuariocliente.Models.Cliente;
import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.R;

public class DriverInfo extends Fragment {
    CardView logout;
    TextView Nombre, Apellido, Correo, Telefono, Foto, Direccion, FechaNacimiento, MetodoPago, Licencia;
    private int id_usuario = Globals.id_usuario;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_driver, container, false);
        Nombre = view.findViewById(R.id.tvNombre_usuario);
        Correo = view.findViewById(R.id.tvCorreo);
        Telefono = view.findViewById(R.id.tvTelefono);

        setTexts();

        logout = view.findViewById(R.id.cvLogout);
        logout.setOnClickListener(v -> {
            logOut();
            Intent i = new Intent(getContext(), Login.class);
            startActivity(i);
            getActivity().finish();

        });

        return view;
    }

    private void setTexts(){
        Cliente cliente = Globals.getCliente(id_usuario);
        Nombre.setText(cliente.getNombres() + " " + cliente.getApellido_paterno() + " " + cliente.getApellido_materno());
        Correo.setText(cliente.getCorreo());
        Telefono.setText(cliente.getTelefono());
        //MetodoPago.setText("En desarrollo");
    }

    private void logOut() {
        SharedPreferences preferences = getActivity().getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("sesion_iniciada", Boolean.FALSE);

        editor.apply();

    }

}
