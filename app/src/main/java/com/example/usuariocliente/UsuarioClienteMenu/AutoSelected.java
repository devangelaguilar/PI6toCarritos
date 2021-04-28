package com.example.usuariocliente.UsuarioClienteMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.usuariocliente.MainActivity;
import com.example.usuariocliente.Models.Auto;
import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.R;
import com.example.usuariocliente.Registro;
import com.example.usuariocliente.UsuarioClienteConfiguracion.UsuarioClienteUserOptions;
import com.example.usuariocliente.UsuarioClienteHistorial.HistorialSelected;
import com.example.usuariocliente.UsuarioClienteHistorial.UsuarioClienteHistorial;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class AutoSelected extends AppCompatActivity {
    ImageView Back,Historial,Usuario,CerrarSesion;
    TextView txtmodelo, txtcolor, tipo1, tipo2, tipo3, tipo4, tipo5, tipo6, tipo7;
    Intent i;
    Button btn_rentar;
    Auto autodata;
    String ubicacion, fecha_inicio, fecha_fin;
    int id_usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Menu Auto seleccionado");
        setContentView(R.layout.activity_auto_selected);
        Back = findViewById(R.id.backarrow);
        Historial = findViewById(R.id.historialicon);
        Usuario = findViewById(R.id.Usuarioicon);
        CerrarSesion = findViewById(R.id.cerrarsesionicon);
        btn_rentar = findViewById(R.id.btnPagar);

        cargarSP();

        //Data in sheet
        txtmodelo=findViewById(R.id.txtmodelo);

        txtcolor=findViewById(R.id.txtcolor);

        tipo1=findViewById(R.id.tipo1);

        tipo2=findViewById(R.id.tipo2);

        tipo3=findViewById(R.id.tipo3);

        tipo4=findViewById(R.id.tipo4);

        tipo5=findViewById(R.id.tipo5);

        tipo6=findViewById(R.id.tipo6);

        tipo7=findViewById(R.id.tipo7);
        //end Data in Sheet

        Intent intent = getIntent();
        autodata = (Auto) intent.getSerializableExtra("auto");
        Toast.makeText(this, ""+autodata.getColor(), Toast.LENGTH_SHORT).show();

        //Build Data Sheet
        txtmodelo.setText(autodata.getModelo());
        txtcolor.setText(autodata.getColor());
        tipo1.setText(autodata.getTipo_vehiculo());
        tipo2.setText(autodata.getPlacas());
        tipo3.setText(autodata.getFoto());
        tipo4.setText("Disponible");
        tipo5.setText(autodata.getTransmision());
        tipo6.setText(autodata.getPrecio());
        tipo7.setText(autodata.getPrecio());
        //END Build Data Sheet

        //NAvegation Menu

        Back.setOnClickListener(v -> {
            i = new Intent(getApplicationContext(), UsuarioClienteMenu.class);
            Toast.makeText(getApplicationContext(), "Regresar a Pantalla anterior, en este caso el menu de UsuarioClienteMenu", Toast.LENGTH_LONG).show();
            startActivity(i);
        });
        Historial.setOnClickListener(v -> {
            i = new Intent(getApplicationContext(), UsuarioClienteHistorial.class);
            Toast.makeText(getApplicationContext(), "Historial", Toast.LENGTH_SHORT).show();
            startActivity(i);
        });
        Usuario.setOnClickListener(v -> {
            i = new Intent(getApplicationContext(), UsuarioClienteUserOptions.class);
            Toast.makeText(getApplicationContext(), "Opciones de usuario", Toast.LENGTH_SHORT).show();
            startActivity(i);
        });
        CerrarSesion.setOnClickListener(v -> {
            i = new Intent(getApplicationContext(), MainActivity.class);
            Globals.cerrarSesion(getApplicationContext());
            Toast.makeText(getApplicationContext(), "Regresar a Login", Toast.LENGTH_SHORT).show();
            startActivity(i);
        });

        //Navegation Menu

        btn_rentar.setOnClickListener(v -> {
            //Toast.makeText(getApplicationContext(), " " + id_usuario, Toast.LENGTH_SHORT).show();
            registrar();
        });


    }
    public void registrar() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Globals.ip + "RentaAuto.php", response -> {
            if (response.equals("ERROR 1")){
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
            } else if (response.equals("MENSAJE")){
                i = new Intent(getApplicationContext(), UsuarioClienteMenu.class);
                startActivity(i);
            }
            Toast.makeText(getApplicationContext(), "" + response, Toast.LENGTH_SHORT).show();
        }, error -> Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();

                // En este metodo se hace el envio de valores de la aplicacion al servidor
                parametros.put("id_vehiculo", String.valueOf(autodata.getId_vehiculo()));
                parametros.put("id_usuario", String.valueOf(id_usuario));
                parametros.put("ubicacion", ubicacion);
                parametros.put("fecha_inicio", fecha_inicio);
                parametros.put("fecha_fin", fecha_fin);

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    private void cargarSP(){
        SharedPreferences preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        int usuario = preferences.getInt("id_unico", 0);
        String inicio = preferences.getString("fecha_inicio", "");
        String fin = preferences.getString("fecha_fin", "");
        String ub = preferences.getString("ubicacion", "");

        id_usuario = usuario;
        fecha_inicio = inicio;
        fecha_fin = fin;
        ubicacion = ub;
    }
}