package com.example.usuariocliente.Models;

public class Card {
    String numero_tarjeta;
    String fecha_vencimiento;

    public Card(String numero_tarjeta, String fecha_vencimiento) {
        this.numero_tarjeta = numero_tarjeta;
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public String getNumero_tarjeta() {
        return numero_tarjeta;
    }

    public void setNumero_tarjeta(String numero_tarjeta) {
        this.numero_tarjeta = numero_tarjeta;
    }

    public String getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(String fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }
}
