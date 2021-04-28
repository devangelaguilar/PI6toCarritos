package com.example.usuariocliente.Models;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class Globals {
    public static String ip = "https://cinderellaride.000webhostapp.com/assets/php/";

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
                    autosList.add(a);
                }

            } catch (JSONException e) {

                e.printStackTrace();
            }
        }, error -> { });
        Handler.getInstance(c).addToRequestQueue(stringRequest);
        return autosList;
    }

    public static String getDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT-5"));
        c.setTime(date);
        String dayWeekText = new SimpleDateFormat("EEEE").format(date);
        return  dayWeekText;
    }

    public static String getMonth(Date date) {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT-5"));
        c.setTime(date);
        String month = new SimpleDateFormat("MMMM").format(date);
        return month;
    }

    public static String getDayNumber(Date date) {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT-5"));
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
}
