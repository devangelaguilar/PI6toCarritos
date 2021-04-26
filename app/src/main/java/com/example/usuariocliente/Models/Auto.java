package com.example.usuariocliente.Models;


import java.io.Serializable;

public class Auto implements Serializable {

    int id_vehiculo;
    String tipo_vehiculo;
    String placas;
    String modelo;
    String color;
    String foto;
    int status;
    String precio;
    String transmision;

    public Auto(int id_vehiculo, String tipo_vehiculo, String placas, String modelo, String color, String foto, int status, String precio, String transmision) {
        this.id_vehiculo = id_vehiculo;
        this.tipo_vehiculo = tipo_vehiculo;
        this.placas = placas;
        this.modelo = modelo;
        this.color = color;
        this.foto = foto;
        this.status = status;
        this.precio = precio;
        this.transmision = transmision;
    }

    public int getId_vehiculo() {
        return id_vehiculo;
    }

    public void setId_vehiculo(int id_vehiculo) {
        this.id_vehiculo = id_vehiculo;
    }

    public String getTipo_vehiculo() {
        return tipo_vehiculo;
    }

    public void setTipo_vehiculo(String tipo_vehiculo) {
        this.tipo_vehiculo = tipo_vehiculo;
    }

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }
}
