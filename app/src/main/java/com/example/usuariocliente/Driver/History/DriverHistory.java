package com.example.usuariocliente.Driver.History;

import android.content.Context;
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
import com.example.usuariocliente.Adapter.HistoryRentasAdapter;
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

public class DriverHistory extends Fragment {
    Context context;
    RecyclerView rvRentas;
    List<Renta> rentasList = new ArrayList<>();
    TextView texto;
    ImageView logo;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_driver, container, false);
        context = getContext();
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
                    if (a.getStatus() == 0)
                        rentasList.add(a);
                }
                if (rentasList.size() == 0){
                    logo.setVisibility(View.VISIBLE);
                    texto.setVisibility(View.VISIBLE);
                }
                //Toast.makeText(context, rentasList.size() + " ", Toast.LENGTH_LONG).show();
                HistoryRentasAdapter adapter = new HistoryRentasAdapter(rentasList);
                rvRentas.setAdapter(adapter);

            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(context, e + " " + response, Toast.LENGTH_LONG).show();
            }
        }, error -> { });
        Handler.getInstance(context).addToRequestQueue(stringRequest);

    }
}
