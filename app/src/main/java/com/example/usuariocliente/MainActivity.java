package com.example.usuariocliente;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.usuariocliente.Cliente.ClienteMenu;
import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.Driver.ListaRenta;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Intent i;
    Button login;
    EditText edit_email, edit_pass;
    TextView registro;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        i = new Intent(MainActivity.this, UsuarioClienteMenu.class);
//        startActivity(i);
        cargarSP();
        edit_email = findViewById(R.id.edit_email);
        edit_pass = findViewById(R.id.edit_pass);
        login = findViewById(R.id.btn_login);
        registro = findViewById(R.id.toregistro);

        preferences = this.getSharedPreferences("USERPREF", Context.MODE_PRIVATE);


        registro.setOnClickListener(v -> {
            i = new Intent(MainActivity.this, Registro.class);
            startActivity(i);
        });

        login.setOnClickListener(v -> {
            String mEmail = edit_email.getText().toString().trim();
            String mPass = edit_pass.getText().toString().trim();

            if (!mEmail.isEmpty() || !mPass.isEmpty()) {
                Login(mEmail, mPass);
            } else {
                edit_email.setError("Ingresar Email");
                edit_pass.setError("Ingresar Contraseña");
            }
        });
    }

        private void Login(final String correo, final String password) {


            StringRequest stringRequest = new StringRequest(Request.Method.POST, Globals.ip + "login.php",
                    response -> {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if (success.equals("1")) {

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    int id_Usuario = object.getInt("id_Usuario");
                                    int clase_usuario = object.getInt("clase_usuario");
                                    String nombres = object.getString("nombres").trim();
                                    String apellido_paterno = object.getString("apellido_paterno").trim();
                                    String apellido_materno = object.getString("apellido_materno").trim();
                                    String correo1 = object.getString("correo").trim();
                                    String telefono = object.getString("telefono").trim();
                                    String fecha_de_nacimiento = object.getString("fecha_de_nacimiento").trim();
                                    setSharedPreferences(id_Usuario, clase_usuario, nombres, apellido_paterno, apellido_materno, correo1, telefono, fecha_de_nacimiento);

                                    //If Usuario Cliente TRUE
                                    if (clase_usuario == 1){
                                        Intent intent = new Intent(getApplicationContext(), ClienteMenu.class);
                                        startActivity(intent);
                                        finish();
                                    } else if (clase_usuario == 2){
                                        Intent intent = new Intent(getApplicationContext(), ListaRenta.class);
                                        startActivity(intent);
                                        finish();
                                    }

                                }

                            } else {
                                Toast.makeText(getApplicationContext(), "Error en response" + success, Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                        }
                    },
                    error -> Toast.makeText(MainActivity.this, "Contacte a Soporte " +error.toString(), Toast.LENGTH_SHORT).show())
            {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("correo", correo);
                    params.put("password", password);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

        }

    public void setSharedPreferences(int id, int clase_usuario,String nombres, String apellido_paterno, String apellido_materno, String mail, String telefono, String fecha_de_nacimiento) {
        SharedPreferences preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("id_unico", id);
        editor.putInt("clase_usuario", clase_usuario);
        editor.putString("nombres", nombres);
        editor.putString("apellido_paterno", apellido_paterno);
        editor.putString("apellido_materno", apellido_materno);
        editor.putString("correo", mail);
        editor.putString("telefono", telefono);
        editor.putString("fecha_de_nacimiento", fecha_de_nacimiento);
        editor.putBoolean("sesion_iniciada", Boolean.TRUE);

        editor.apply();
    }

    private void cargarSP(){
        SharedPreferences preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        Boolean sesion_iniciada = preferences.getBoolean("sesion_iniciada", Boolean.FALSE);
        int clase_usuariosp = preferences.getInt("clase_usuario", 0);

        if (sesion_iniciada.equals(Boolean.TRUE)){
            if(clase_usuariosp == 1){
                i = new Intent(getApplicationContext(), ClienteMenu.class);
                startActivity(i);
            } else if (clase_usuariosp == 2){
                i = new Intent(getApplicationContext(), ListaRenta.class);
                startActivity(i);
            }
        }
    }
}
