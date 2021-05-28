package com.example.usuariocliente;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.widget.TextView;

import com.example.usuariocliente.Registro.Registro;
import com.example.usuariocliente.Cliente.UsuarioClienteMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.content.Intent;

import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Intent i;
    Button login;
    EditText edit_email, edit_pass;
    TextView registro;
    SharedPreferences preferences;


    String URL_SERVIDOR = "Colocar servidor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        i = new Intent(MainActivity.this, UsuarioClienteMenu.class);
//        startActivity(i);

        edit_email = findViewById(R.id.edit_email);
        edit_pass = findViewById(R.id.edit_pass);
        login = findViewById(R.id.btn_login);
        registro = findViewById(R.id.toregistro);

        preferences = this.getSharedPreferences("USERPREF", Context.MODE_PRIVATE);


        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = new Intent(MainActivity.this, Registro.class);
                startActivity(i);
            }

        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = edit_email.getText().toString().trim();
                String mPass = edit_pass.getText().toString().trim();

                if (!mEmail.isEmpty() || !mPass.isEmpty()) {
                    Login(mEmail, mPass);
                } else {
                    edit_email.setError("Ingresar Email");
                    edit_pass.setError("Ingresar Contraseña");
                }
            }
        });
    }

        private void Login(final String correo, final String password) {


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

                                        String id_Usuario = object.getString("id_Usuario").trim();
                                        String nombres = object.getString("nombres").trim();
                                        String apellido_paterno = object.getString("apellido_paterno").trim();
                                        String apellido_materno = object.getString("apellido_materno").trim();
                                        String correo = object.getString("correo").trim();
                                        String telefono = object.getString("telefono").trim();
                                        String fecha_de_nacimiento = object.getString("fecha_de_nacimiento").trim();


                                                    //If Usuario Cliente TRUE
                                        Intent intent = new Intent(MainActivity.this, UsuarioClienteMenu.class); //Aqu[i debemos converger para decidir si va a Driver o Usuario Cliente
                                        intent.putExtra("id_Usuario", id_Usuario);
                                        intent.putExtra("nombres", nombres);
                                        intent.putExtra("apellido_paterno", apellido_paterno);
                                        intent.putExtra("apellido_materno", apellido_materno);
                                        intent.putExtra("correo", correo);
                                        intent.putExtra("telefono", telefono);
                                        intent.putExtra("fecha_de_nacimiento", fecha_de_nacimiento);
                                        startActivity(intent);
                                        finish();



                                    }

                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(MainActivity.this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, "Contacte a Soporte" +error.toString(), Toast.LENGTH_SHORT).show();
                        }
                    })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("correo", correo);
                    params.put("password", password);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);

        }
    }
