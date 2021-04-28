package com.example.usuariocliente.Models;

import java.io.Serializable;

public class Renta implements Serializable {

    int id_renta;
    int id_vehiculo;
    int id_usuario;
    String ubicacion;
    int status;
    String fecha_inicio;
    String fecha_fin;

    public Renta(int id_renta, int id_vehiculo, int id_usuario, String ubicacion, int status, String fecha_inicio, String fecha_fin) {
        this.id_renta = id_renta;
        this.id_vehiculo = id_vehiculo;
        this.id_usuario = id_usuario;
        this.ubicacion = ubicacion;
        this.status = status;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
    }

    public int getId_renta() {
        return id_renta;
    }

    public void setId_renta(int id_renta) {
        this.id_renta = id_renta;
    }

    public int getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(int id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }
}
