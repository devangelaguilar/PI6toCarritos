package com.example.usuariocliente.Models;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.location.Location;
import android.util.Log;
import android.util.SparseArray;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.usuariocliente.Cliente.ClienteMenu;
import com.example.usuariocliente.Cliente.Options.ListaCards;
import com.example.usuariocliente.Driver.DriverMenu;
import com.example.usuariocliente.Login;
import com.example.usuariocliente.SplashScreen;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

public class Globals {
    public static String ip = "https://cinderellaride.000webhostapp.com/assets/php/";
    public static List<Renta> listaRentas = new ArrayList<>();
    public static Location currentLocation;
    public static List<Cliente> clientes = new ArrayList<>();
    public static List<Auto> autosList = new ArrayList<>();
    public static ArrayList<Bitmap> autoPics = new ArrayList<>();
    public static Location getCurrentLocation() {
        return currentLocation;
    }
    public static int id_usuario;

    public static void setCurrentLocation(Location currentLocation) {
        Globals.currentLocation = currentLocation;
    }

    public static String getColor(int id){
        switch (id){
            case 0:
                return "Rojo";
            case 1:
                return "Blanco";
            case 2:
                return "Azul";
            default:
                return null;
        }
    }

    public static String getTipo_vehiculo(int t){
        switch (t){
            case 0:
                return "Sedán";
            case 1:
                return "Hatchback";
            default:
                return null;
        }
    }

    public static String getStatus(int status){
        switch (status){
            case 0:
                return "Rentado";
            case 1:
                return "Disponible";
            default:
                return null;
        }
    }

    public static List<Auto> showList(Context c){
        List<Auto> autosList = new ArrayList<>();
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
                }

            } catch (JSONException e) {

                e.printStackTrace();
            }
        }, error -> { });
        Handler.getInstance(c).addToRequestQueue(stringRequest);
        return autosList;
    }

    public static String getDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT-7"));
        c.setTime(date);
        String dayWeekText = new SimpleDateFormat("EEEE").format(date);
        return  dayWeekText;
    }

    public static String getMonth(Date date) {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT-7"));
        c.setTime(date);
        String month = new SimpleDateFormat("MMMM").format(date);
        return month;
    }

    public static String getDayNumber(Date date) {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT-7"));
        c.setTime(date);
        String day = new SimpleDateFormat("dd").format(date);
        return day;
    }

    public static void cerrarSesion(Context c) {
        SharedPreferences preferences = c.getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("sesion_iniciada", Boolean.FALSE);
        editor.apply();
    }

    public static List<Renta> showListRenta(Context c) {
        List<Renta> rentasList = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Globals.ip + "getRenta.php", response -> {
            try {
                JSONObject obj = new JSONObject(response);
                JSONArray array = obj.getJSONArray("vehiculos");
                for (int i = 0; i < array.length(); i++){
                    JSONObject rentaObj = array.getJSONObject(i);
                    Renta a = new Renta(rentaObj.getInt("id_renta"), rentaObj.getInt("id_vehiculo"), rentaObj.getInt("id_usuario"),
                            rentaObj.getString("ubicacion"), rentaObj.getInt("status"), rentaObj.getString("fecha_inicio"), rentaObj.getString("fecha_fin"));
                    if (a.status == 1){
                        rentasList.add(a);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                Toast.makeText(c, e + " " + response, Toast.LENGTH_LONG).show();
            }
        }, error -> { });
        Handler.getInstance(c).addToRequestQueue(stringRequest);
        return rentasList;
    }

    public static void getClientes(Context c) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Globals.ip + "getUsuario.php", response -> {
            try {
                JSONObject obj = new JSONObject(response);
                JSONArray array = obj.getJSONArray("usuarios");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject clienteObj = array.getJSONObject(i);
                    Cliente cliente = new Cliente(clienteObj.getInt("id_Usuario"), clienteObj.getInt("clase_usuario"), clienteObj.getString("correo"), clienteObj.getString("nombre"),
                            clienteObj.getString("apellido_paterno"), clienteObj.getString("apellido_materno"), clienteObj.getString("telefono"),
                            clienteObj.getString("direccion"), clienteObj.getString("fecha_de_nacimiento"));
                    clientes.add(cliente);
                }
                Intent intent = new Intent(c, DriverMenu.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(intent);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> { });
        Handler.getInstance(c).addToRequestQueue(stringRequest);

    }

    public static void getClientesU(Context c) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Globals.ip + "getUsuario.php", response -> {
            try {
                JSONObject obj = new JSONObject(response);
                JSONArray array = obj.getJSONArray("usuarios");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject clienteObj = array.getJSONObject(i);
                    Cliente cliente = new Cliente(clienteObj.getInt("id_Usuario"), clienteObj.getInt("clase_usuario"), clienteObj.getString("correo"), clienteObj.getString("nombre"),
                            clienteObj.getString("apellido_paterno"), clienteObj.getString("apellido_materno"), clienteObj.getString("telefono"),
                            clienteObj.getString("direccion"), clienteObj.getString("fecha_de_nacimiento"));
                    clientes.add(cliente);
                }
                getAutosU(c);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> { });
        Handler.getInstance(c).addToRequestQueue(stringRequest);

    }
    public static void getClientesR(Context c) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Globals.ip + "getUsuario.php", response -> {
            try {
                JSONObject obj = new JSONObject(response);
                JSONArray array = obj.getJSONArray("usuarios");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject clienteObj = array.getJSONObject(i);
                    Cliente cliente = new Cliente(clienteObj.getInt("id_Usuario"), clienteObj.getInt("clase_usuario"), clienteObj.getString("correo"), clienteObj.getString("nombre"),
                            clienteObj.getString("apellido_paterno"), clienteObj.getString("apellido_materno"), clienteObj.getString("telefono"),
                            clienteObj.getString("direccion"), clienteObj.getString("fecha_de_nacimiento"));
                    clientes.add(cliente);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> { });
        Handler.getInstance(c).addToRequestQueue(stringRequest);

    }
    public static void getAutosU(Context c){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Globals.ip + "getAutos.php", response -> {
            try {
                JSONObject obj = new JSONObject(response);
                JSONArray array = obj.getJSONArray("vehiculos");
                for (int i = 0; i < array.length(); i++){
                    JSONObject autoObj = array.getJSONObject(i);
                    Auto a = new Auto(autoObj.getInt("id_vehiculo"), Globals.getTipo_vehiculo(autoObj.getInt("tipo_vehiculo")), autoObj.getString("placas"), autoObj.getString("modelo"),
                            Globals.getColor(autoObj.getInt("color")), autoObj.getString("foto"), autoObj.getInt("status"), autoObj.getString("precio"), autoObj.getString("transmision"));
                    autosList.add(a);
                }
                Intent intent = new Intent(c, ClienteMenu.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                c.startActivity(intent);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> { });
        Handler.getInstance(c).addToRequestQueue(stringRequest);

    }
    public static void getAutos(Context c){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Globals.ip + "getAutos.php", response -> {
            try {
                JSONObject obj = new JSONObject(response);
                JSONArray array = obj.getJSONArray("vehiculos");
                for (int i = 0; i < array.length(); i++){
                    JSONObject autoObj = array.getJSONObject(i);
                    Auto a = new Auto(autoObj.getInt("id_vehiculo"), Globals.getTipo_vehiculo(autoObj.getInt("tipo_vehiculo")), autoObj.getString("placas"), autoObj.getString("modelo"),
                            Globals.getColor(autoObj.getInt("color")), autoObj.getString("foto"), autoObj.getInt("status"), autoObj.getString("precio"), autoObj.getString("transmision"));
                    autosList.add(a);
                }
                getClientes(c);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> { });
        Handler.getInstance(c).addToRequestQueue(stringRequest);

    }

    public static void setRentasList(List<Renta> response) {
        listaRentas = response;
    }

    public static Auto getAuto(Context c, Renta renta){
        for (Auto auto: autosList) {
            if (auto.getId_vehiculo() == renta.getId_vehiculo()){
                return auto;
            }
        }
        return null;
    }

    public static Cliente getCliente(Context c, Renta renta){
        for (Cliente cliente: clientes) {
            if (cliente.getId_Usuario() == renta.getId_usuario()){
                return cliente;
            }
        }
        return clientes.get(0);
    }

    public static Cliente getCliente(int id_usuario){
        for (Cliente cliente: clientes) {
            if (cliente.getId_Usuario() == id_usuario){
                return cliente;
            }
        }
        return null;
    }

    public static void deleteCard(String numeracion_tarjeta, Context c){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Globals.ip + "deletecard.php",
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        Toast.makeText(c, "Tarjeta Eliminada", Toast.LENGTH_SHORT).show();


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(c, "Tarjeta Eliminada!", Toast.LENGTH_LONG).show();


                    }
                },
                error -> {})
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("numeracion_tarjeta", String.valueOf(numeracion_tarjeta));
                return params;
            }
        };

        Handler.getInstance(c).addToRequestQueue(stringRequest);

    }

}
