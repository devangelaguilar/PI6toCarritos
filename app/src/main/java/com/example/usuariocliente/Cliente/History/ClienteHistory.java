package com.example.usuariocliente.Cliente.History;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.usuariocliente.Adapter.AutosAdapter;
import com.example.usuariocliente.Adapter.HistoryRentasAdapter;
import com.example.usuariocliente.Adapter.HistoryRentasClienteAdapter;
import com.example.usuariocliente.Cliente.Home.MisRentas;
import com.example.usuariocliente.Models.Auto;
import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.Models.Handler;
import com.example.usuariocliente.Models.Renta;
import com.example.usuariocliente.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ClienteHistory extends Fragment {
    RecyclerView rvAutos;
    List<Renta> autosList = new ArrayList<>();
    Context context;
    Button btn;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.history_cliente, container, false);
        context = getContext();
        btn = view.findViewById(R.id.btn);
        rvAutos = view.findViewById(R.id.rvAutos);
        showList();

        rvAutos.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        rvAutos.setLayoutManager(llm);

        btn.setOnClickListener(v -> {
            Intent i = new Intent(context, MisRentas.class);
            startActivity(i);
        });
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
                    if (a.getId_usuario() == Globals.id_usuario && a.getStatus() == 0)
                        autosList.add(a);
                }
                //Toast.makeText(context, autosList.size() + " ", Toast.LENGTH_LONG).show();
                if (autosList.size() > 0) {
                    HistoryRentasClienteAdapter adapter = new HistoryRentasClienteAdapter(autosList);
                    rvAutos.setAdapter(adapter);
                }


            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(context, e + " " + response, Toast.LENGTH_LONG).show();
            }
        }, error -> { });
        Handler.getInstance(context).addToRequestQueue(stringRequest);

    }
}
