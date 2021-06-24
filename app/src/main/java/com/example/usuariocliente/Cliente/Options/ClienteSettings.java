package com.example.usuariocliente.Cliente.Options;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import androidx.fragment.app.Fragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.usuariocliente.Cliente.Home.MisRentas;
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
    CardView historial, metodoPago, logout;
    TextView Nombre, Correo, Telefono;
    ImageView editInfo;
    private final int id_usuario = Globals.id_usuario;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings_cliente, container, false);

        Nombre = view.findViewById(R.id.tvNombre_usuario);
        Correo = view.findViewById(R.id.tvCorreo);
        Telefono = view.findViewById(R.id.tvTelefono);

        setTexts();
        historial = view.findViewById(R.id.cvHistorial);
        historial.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), MisRentas.class);
            startActivity(i);
        });
        metodoPago = view.findViewById(R.id.cvCards);
        metodoPago.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), ListaCards.class);
            startActivity(i);
        });
        logout = view.findViewById(R.id.cvLogout);
        logout.setOnClickListener(v -> {
            logOut();
            Intent i = new Intent(getContext(), Login.class);
            startActivity(i);
            getActivity().finish();
        });
        editInfo = view.findViewById(R.id.edit);
        editInfo.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), EditInfo.class);
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
    }

    private void logOut() {
        SharedPreferences preferences = getActivity().getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("sesion_iniciada", Boolean.FALSE);

        editor.apply();
    }
}
