package com.example.usuariocliente.Models;

public class Cliente {
    int id_Usuario;
    int clase_usuario;
    String correo;
    String nombres;
    String apellido_paterno;
    String apellido_materno;
    String telefono;
    String direccion;
    String fecha_de_nacimiento;

    public Cliente(int id_Usuario, int clase_usuario, String correo, String nombres, String apellido_paterno, String apellido_materno, String telefono, String direccion, String fecha_de_nacimiento) {
        this.id_Usuario = id_Usuario;
        this.clase_usuario = clase_usuario;
        this.correo = correo;
        this.nombres = nombres;
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fecha_de_nacimiento = fecha_de_nacimiento;
    }

    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public int getClase_usuario() {
        return clase_usuario;
    }

    public void setClase_usuario(int clase_usuario) {
        this.clase_usuario = clase_usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFecha_de_nacimiento() {
        return fecha_de_nacimiento;
    }

    public void setFecha_de_nacimiento(String fecha_de_nacimiento) {
        this.fecha_de_nacimiento = fecha_de_nacimiento;
    }
}
