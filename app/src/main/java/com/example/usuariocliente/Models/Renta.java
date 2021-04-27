package com.example.usuariocliente.Models;

import java.io.Serializable;

public class Renta implements Serializable {

    int id_renta;
    int id_vehiculo;
    int id_usuario;
    int ubicacion;
    int status;



    public Renta(int id_renta, int id_vehiculo, int id_usuario, int ubicacion, int status ) {
        this.id_renta = id_renta;
        this.id_vehiculo = id_vehiculo;
        this.id_usuario = id_usuario;
        this.ubicacion = ubicacion;
        this.status = status;

    }

    public int getid_renta() {
        return id_renta;
    }

    public void setid_renta(int id_renta) {
        this.id_renta = id_renta;
    }

    public int getid_vehiculo() {
        return id_vehiculo;
    }

    public void setid_vehiculo(int id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public int getid_usuario() {
        return id_usuario;
    }

    public void setid_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getubicacion() {
        return ubicacion;
    }

    public void setubicacion(int ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getstatus() {
        return status;
    }

    public void setstatus(int status) {
        this.status = status;
    }


}
