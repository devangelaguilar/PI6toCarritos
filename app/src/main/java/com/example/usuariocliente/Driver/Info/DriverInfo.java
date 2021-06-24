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
import androidx.fragment.app.Fragment;

import com.example.usuariocliente.Login;
import com.example.usuariocliente.Models.Cliente;
import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.R;

public class DriverInfo extends Fragment {
    Button logout;
    String URL_SERVIDOR= Globals.ip + "GetUserInfo.php";
    TextView Nombre, Apellido, Correo, Telefono, Foto, Direccion, FechaNacimiento, MetodoPago, Licencia;
    private int id_usuario = Globals.id_usuario;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_driver, container, false);
        Nombre = view.findViewById(R.id.AModelo);
        Apellido = view.findViewById(R.id.AColor);
        Correo = view.findViewById(R.id.ATipo);
        Telefono = view.findViewById(R.id.APlacas);
        Foto = view.findViewById(R.id.Afoto);
        Direccion = view.findViewById(R.id.AEstado);
        FechaNacimiento = view.findViewById(R.id.ATransmision);
        MetodoPago = view.findViewById(R.id.APrecio);
        Licencia = view.findViewById(R.id.ATotal);
        setTexts();
        logout = view.findViewById(R.id.logout);
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
        Nombre.setText(cliente.getNombres());
        Apellido.setText(cliente.getApellido_paterno() + " " + cliente.getApellido_materno());
        Correo.setText(cliente.getCorreo());
        Telefono.setText(cliente.getTelefono());
        Direccion.setText(cliente.getDireccion());
        FechaNacimiento.setText(cliente.getFecha_de_nacimiento());
        //MetodoPago.setText("En desarrollo");
    }

    private void logOut() {
        SharedPreferences preferences = getActivity().getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("sesion_iniciada", Boolean.FALSE);

        editor.apply();

    }

}
