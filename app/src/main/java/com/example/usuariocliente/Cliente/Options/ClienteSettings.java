package com.example.usuariocliente.Cliente.Options;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.usuariocliente.Login;
import com.example.usuariocliente.Models.Cliente;
import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.R;
import com.example.usuariocliente.Registro;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ClienteSettings extends Fragment {
    Button logout, metodoPago;
    TextView Nombre, Apellido, Correo, Telefono, Foto, Direccion, FechaNacimiento, MetodoPago, Licencia;
    private final int id_usuario = Globals.id_usuario;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_cliente, container, false);

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
        metodoPago = view.findViewById(R.id.metodopagobutton);
        metodoPago.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), ListaCards.class);
            startActivity(i);
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
        MetodoPago.setText("En desarrollo");
    }

    private void logOut() {
        SharedPreferences preferences = getActivity().getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("sesion_iniciada", Boolean.FALSE);

        editor.apply();

    }

}
