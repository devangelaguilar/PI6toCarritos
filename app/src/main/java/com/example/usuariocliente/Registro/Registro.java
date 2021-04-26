package com.example.usuariocliente.Registro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.usuariocliente.Login.Login;
import com.example.usuariocliente.Models.Globals;
import com.example.usuariocliente.R;

import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registro extends AppCompatActivity {

    Intent i;

    EditText nombres, apellido_paterno, apellido_materno, correo, password, telefono, fecha_de_nacimiento, licencia;
    Button Registro;
    TextView LoginReturn;

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

        Registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int lengthtel = telefono.getText().length();


                if(!(lengthtel==10)){
                    Toast.makeText(Registro.this, "Introduzca un número de teléfono válido", Toast.LENGTH_SHORT).show();
                }


                if(!(isEmailValid(correo.getText().toString()))){
                    Toast.makeText(Registro.this, "Introduzca un Correo (email) válido", Toast.LENGTH_SHORT).show();

                }
                if (  (lengthtel==10) && (isEmailValid(correo.getText().toString()))){
                    Toast.makeText(Registro.this, "Registro en progreso", Toast.LENGTH_SHORT).show();
                    registrar();
                }




            }
        });



    }
    public void registrar() {
        StringRequest stringRequest;
        stringRequest = new StringRequest(Request.Method.POST, Globals.ip + "registrar.php",
                response -> {
                    // En este apartado se programa lo que deseamos hacer en caso de no haber errores

                    if(response.equals("ERROR 1")) {
                        Toast.makeText(Registro.this, "Se deben de llenar todos los campos", Toast.LENGTH_SHORT).show();
                        i = new Intent(Registro.this, Registro.class);
                        startActivity(i);

                    } else if(response.equals("ERROR 2")) {
                        Toast.makeText(Registro.this, "Usuario ya existente, emplee otro correo", Toast.LENGTH_SHORT).show();
                        correo.setText("");

                    } else if(response.equals("MENSAJE")) {
                        Toast.makeText(Registro.this, "Registro exitoso.", Toast.LENGTH_LONG).show();
                        i = new Intent(Registro.this, Login.class);
                        startActivity(i);
                    }

                        Toast.makeText(Registro.this, "Registro ."+response, Toast.LENGTH_LONG).show();



                }, error -> {
                    // En caso de tener algun error en la obtencion de los datos
                    Toast.makeText(Registro.this, "ERROR CON LA CONEXION", Toast.LENGTH_LONG).show();
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                // En este metodo se hace el envio de valores de la aplicacion al servidor
                Map<String, String> parametros = new Hashtable<String, String>();
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

        RequestQueue requestQueue = Volley.newRequestQueue(Registro.this);
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
}