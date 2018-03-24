package com.example.cifu.practica_sallelibrary_sergioc_ianm.models;

import java.util.ArrayList;

/**
 * Created by assai on 23/03/2018.
 */

public class UserModel {

    private String usuario, nombre, apellido, contraseña;
    private ArrayList<BookModel> favoritos;

    public UserModel(String usuario, String nombre, String apellido, String contraseña, ArrayList<BookModel> favoritos) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contraseña = contraseña;
        this.favoritos = favoritos;
    }

    public String getContraseña() {
        return contraseña;
    }

    public ArrayList<BookModel> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(ArrayList<BookModel> favoritos) {
        this.favoritos = favoritos;
    }
}
