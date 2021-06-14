package com.example.usuariocliente.Cliente.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.usuariocliente.Adapter.AutosAdapter;
import com.example.usuariocliente.Models.Auto;
import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.Models.Handler;
import com.example.usuariocliente.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class AutoList extends AppCompatActivity {
    RecyclerView rvAutos;
    List<Auto> autosList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_list);
        setTitle("Menu Lista de Autos");
        rvAutos = findViewById(R.id.rvAutos);
        showList();

        rvAutos.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rvAutos.setLayoutManager(llm);


    }

    private void showList(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Globals.ip + "getAutos.php", response -> {
            try {
                JSONObject obj = new JSONObject(response);
                JSONArray array = obj.getJSONArray("vehiculos");
                for (int i = 0; i < array.length(); i++){
                    JSONObject autoObj = array.getJSONObject(i);
                    Auto a = new Auto(autoObj.getInt("id_vehiculo"), Globals.getTipo_vehiculo(autoObj.getInt("tipo_vehiculo")), autoObj.getString("placas"), autoObj.getString("modelo"),
                            Globals.getColor(autoObj.getInt("color")), autoObj.getString("foto"), autoObj.getInt("status"), autoObj.getString("precio"), autoObj.getString("transmision"));
                    if (a.getStatus() == 1){
                        autosList.add(a);
                    }
                    //Toast.makeText(getApplicationContext(), a + " " + response, Toast.LENGTH_LONG).show();
                }
                AutosAdapter adapter = new AutosAdapter(autosList);
                rvAutos.setAdapter(adapter);

            } catch (JSONException e) {

                e.printStackTrace();
                Toast.makeText(getApplicationContext(), e + " " + response, Toast.LENGTH_LONG).show();

            }
        }, error -> { Toast.makeText(getApplicationContext(), error + " ", Toast.LENGTH_LONG).show();});
        Handler.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    public void back(View view) {
        finish();
    }

    public void misRentas(View view) {
        Intent i = new Intent(this, MisRentas.class);
        startActivity(i);
    }
}