package com.example.usuariocliente.Cliente.Options;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.usuariocliente.Cliente.ClienteMenu;
import com.example.usuariocliente.Models.Cliente;
import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.R;
import com.example.usuariocliente.SplashScreen;

import java.util.HashMap;
import java.util.Map;

import static com.example.usuariocliente.Models.Globals.id_usuario;

public class EditInfo extends AppCompatActivity {
    EditText nombre, apellido_paterno, apellido_materno, correo, telefono, fecha_nacimiento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        Cliente cliente = Globals.getCliente(id_usuario);

        nombre = findViewById(R.id.edit_nombre);
        nombre.setText(cliente.getNombres());
        apellido_paterno = findViewById(R.id.edit_Ap);
        apellido_paterno.setText(cliente.getApellido_paterno());
        apellido_materno = findViewById(R.id.edit_Am);
        apellido_materno.setText(cliente.getApellido_materno());
        correo = findViewById(R.id.edit_correo);
        correo.setText(cliente.getCorreo());
        telefono = findViewById(R.id.edit_tel);
        telefono.setText(cliente.getTelefono());
        fecha_nacimiento = findViewById(R.id.edit_fNac);
        fecha_nacimiento.setText(cliente.getFecha_de_nacimiento());
    }

    public void guardarInfo(View view) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Globals.ip + "editarInfo.php", response -> {
            if (!response.contains("Error")){
                Toast.makeText(this, "Información editada con éxito", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(this, SplashScreen.class);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "" + response, Toast.LENGTH_SHORT).show();
            }

        }, error -> Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show()){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("id_usuario", String.valueOf(id_usuario));
                parametros.put("correo", correo.getText().toString());
                parametros.put("nombres", nombre.getText().toString());
                parametros.put("apellido_paterno", apellido_paterno.getText().toString());
                parametros.put("apellido_materno", apellido_materno.getText().toString());
                parametros.put("telefono", telefono.getText().toString());
                parametros.put("fecha_de_nacimiento", fecha_nacimiento.getText().toString());
                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

    public void back(View view) {
        Intent i = new Intent(this, ClienteMenu.class);
        startActivity(i);
        finish();
    }
}