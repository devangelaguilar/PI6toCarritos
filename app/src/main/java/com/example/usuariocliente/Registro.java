package com.example.usuariocliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.usuariocliente.Cliente.ClienteMenu;
import com.example.usuariocliente.Models.Globals;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registro extends AppCompatActivity {

    Intent i;

    EditText nombres, apellido_paterno, apellido_materno, correo, password, telefono, fecha_de_nacimiento, licencia;
    Button Registro;
    TextView loginReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        Registro = findViewById(R.id.btn_registro);

        nombres = findViewById(R.id.edit_nombre);
        apellido_paterno = findViewById(R.id.edit_apellido_p);
        apellido_materno = findViewById(R.id.edit_apellido_m);
        correo = findViewById(R.id.edit_email);
        password = findViewById(R.id.edit_pass);
        telefono= findViewById(R.id.edit_tel);
        fecha_de_nacimiento= findViewById(R.id.edit_Fecha_nacimiento);
        licencia= findViewById(R.id.edit_licencia);


        //Filtros de EditText
        nombres.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
        apellido_paterno.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        apellido_materno.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        correo.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
        password.setFilters(new InputFilter[]{ new InputFilter.LengthFilter(16)});
        telefono.setFilters(new InputFilter[]{ new InputFilter.LengthFilter(10)});
        //Corregir licencia para tener formato de foto y Fecha de NAcimiento para emplear calendario Android

        nombres.setFilters(new InputFilter[] { filterLetter });
        apellido_paterno.setFilters(new InputFilter[] { filterLetter });
        apellido_materno.setFilters(new InputFilter[] { filterLetter });

        Registro.setOnClickListener(view -> {
            int lengthtel = telefono.getText().length();
            if(!(lengthtel==10)){
                Toast.makeText(Registro.this, "Introduzca un número de teléfono válido", Toast.LENGTH_SHORT).show();
            }
            if(!(isEmailValid(correo.getText().toString()))){
                Toast.makeText(Registro.this, "Introduzca un Correo (email) válido", Toast.LENGTH_SHORT).show();

            }
            if (  (lengthtel==10) && (isEmailValid(correo.getText().toString()))){
                //Toast.makeText(Registro.this, "Registro en progreso", Toast.LENGTH_SHORT).show();
                registrar();
            }
        });



    }
    public void registrar() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Globals.ip + "registrar.php", response -> {
            if (response.equals("MENSAJE")){
                i = new Intent(getApplicationContext(), ClienteMenu.class);
                setSharedPreferences(nombres.getText().toString(), apellido_paterno.getText().toString(), apellido_materno.getText().toString(), correo.getText().toString(), telefono.getText().toString(), fecha_de_nacimiento.getText().toString());
                startActivity(i);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "" + response, Toast.LENGTH_SHORT).show();
            }

        }, error -> Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show()){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();

                // En este metodo se hace el envio de valores de la aplicacion al servidor
                parametros.put("correo", correo.getText().toString());
                parametros.put("password", password.getText().toString());
                parametros.put("nombres", nombres.getText().toString());
                parametros.put("apellido_paterno", apellido_paterno.getText().toString());
                parametros.put("apellido_materno", apellido_materno.getText().toString());
                parametros.put("telefono", telefono.getText().toString());
                parametros.put("fecha_de_nacimiento", fecha_de_nacimiento.getText().toString());
                parametros.put("licencia", licencia.getText().toString());

                return parametros;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
    InputFilter filter = (source, start, end, dest, dstart, dend) -> {
        for (int i = start; i < end; i++) {
            if (!Character.isLetterOrDigit(source.charAt(i))) {
                return "";
            }
        }
        return null;
    };
    InputFilter filterLetter = (source, start, end, dest, dstart, dend) -> {
        for (int i = start; i < end; i++) {
            if (!Character.isLetter(source.charAt(i))) {
                return "";
            }
        }
        return null;
    };

    //Comprobar si E-mail es valido
    public boolean isEmailValid(String correo)
    {
        String regExpn =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        CharSequence inputStr = correo;

        Pattern pattern = Pattern.compile(regExpn, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if(matcher.matches())
            return true;
        else
            return false;
    }

    public void setSharedPreferences(String nombres, String apellido_paterno, String apellido_materno, String mail, String telefono, String fecha_de_nacimiento) {
        SharedPreferences preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("nombres", nombres);
        editor.putString("apellido_paterno", apellido_paterno);
        editor.putString("apellido_materno", apellido_materno);
        editor.putString("correo", mail);
        editor.putString("telefono", telefono);
        editor.putString("fecha_de_nacimiento", fecha_de_nacimiento);
        editor.putBoolean("sesion_iniciada", Boolean.TRUE);

        editor.apply();
    }
}