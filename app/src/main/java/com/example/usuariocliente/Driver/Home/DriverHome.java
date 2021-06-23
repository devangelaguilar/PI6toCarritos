package com.example.usuariocliente.Driver.Home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.usuariocliente.Adapter.RentasAdapter;
import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.Models.Handler;
import com.example.usuariocliente.Models.Renta;
import com.example.usuariocliente.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DriverHome extends Fragment {
    Context context;
    Intent i;
    RecyclerView rvRentas;
    List<Renta> rentasList = new ArrayList<>();
    TextView texto;
    ImageView logo;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_driver, container, false);
        context = getContext();
        cargarSP();
        rvRentas = view.findViewById(R.id.rvRentas);
        showList();
        rvRentas.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        rvRentas.setLayoutManager(llm);

        texto = view.findViewById(R.id.texto);
        logo = view.findViewById(R.id.logo);

        return view;
    }

    private void showList() {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Globals.ip + "getRenta.php", response -> {
            try {
                JSONObject obj = new JSONObject(response);
                JSONArray array = obj.getJSONArray("rentas");
                for (int i = 0; i < array.length(); i++){
                    JSONObject rentaObj = array.getJSONObject(i);
                    Renta a = new Renta(rentaObj.getInt("id_renta"), rentaObj.getInt("id_vehiculo"), rentaObj.getInt("id_usuario"),
                            rentaObj.getString("ubicacion"), rentaObj.getInt("status"), rentaObj.getString("fecha_inicio"), rentaObj.getString("fecha_fin"));
                    if (a.getStatus() == 3 || a.getStatus() == 2) // 3 = entregar al cliente. 2 = recoger del cliente
                        rentasList.add(a);
                }
                if (rentasList.size() == 0){
                    logo.setVisibility(View.VISIBLE);
                    texto.setVisibility(View.VISIBLE);
                }

                RentasAdapter adapter = new RentasAdapter(rentasList);
                rvRentas.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(context, e + " " + response, Toast.LENGTH_LONG).show();
            }
        }, error -> { });
        Handler.getInstance(context).addToRequestQueue(stringRequest);

    }
    private void cargarSP(){
        SharedPreferences preferences = getActivity().getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        int usuario = preferences.getInt("id_unico", 0);

        Globals.id_usuario = usuario;
        //Toast.makeText(this, "a " + Globals.id_usuario, Toast.LENGTH_LONG).show();
    }
}
