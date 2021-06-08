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
import com.example.usuariocliente.R;
import com.example.usuariocliente.Registro;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ClienteSettings extends Fragment {
    Button logout, metodoPago;
    String idUsuario;
    String URL_SERVIDOR="Aqu[i va el url del php";
    TextView Nombre, Apellido, Correo, Telefono, Foto, Direccion, FechaNacimiento, MetodoPago, Licencia;


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

        logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(v -> {
            logOut();
            Intent i = new Intent(getContext(), Login.class);
            startActivity(i);
            getActivity().finish();

        }   );
        metodoPago = view.findViewById(R.id.metodopagobutton);
        metodoPago.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), metodoPagoForm.class);
            startActivity(i);
        });

        //DECLARAR VARIABLE PARA JALAR LA INFORMACIóN DEL USUARIO
        GetUserInfo(idUsuario);

        return view;
    }

    private void GetUserInfo(final String idUsuario) {


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_SERVIDOR,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if (success.equals("1")) {

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String nombres = object.getString("nombres").trim();
                                    String apellido_paterno = object.getString("apellido_paterno").trim();
                                    String telefono = object.getString("telefono").trim();
                                    String correo = object.getString("correo").trim();
                                    String direccion = object.getString("direccion").trim();
                                    String fecha_de_nacimiento = object.getString("fecha_de_nacimiento").trim();
                                    String licencia = object.getString("licencia").trim();

                                    Nombre.setText(nombres);
                                    Apellido.setText(apellido_paterno);
                                    Correo.setText(correo);
                                    Telefono.setText(telefono);
                                    Foto.setText("En desarrollo");
                                    Direccion.setText(direccion);
                                    FechaNacimiento.setText(fecha_de_nacimiento);
                                    MetodoPago.setText("En desarrollo");
                                    Licencia.setText(licencia);


                                }

                            }

                        } catch (JSONException e) { e.printStackTrace(); }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("idUsuario", idUsuario);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());;
        requestQueue.add(stringRequest);

    }

    private void logOut() {
        SharedPreferences preferences = getActivity().getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("sesion_iniciada", Boolean.FALSE);

        editor.apply();
    }

}
