package com.example.cifu.practica_sallelibrary_sergioc_ianm.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cifu.practica_sallelibrary_sergioc_ianm.R;
import com.example.cifu.practica_sallelibrary_sergioc_ianm.models.BookModel;
import com.example.cifu.practica_sallelibrary_sergioc_ianm.models.UserModel;
import com.example.custombookdescription.CustomBookDescription;
import com.google.gson.Gson;

import java.util.ArrayList;

public class BookDescription extends AppCompatActivity implements View.OnClickListener {

    private Button favoritos;
    private BookModel book;
    private String usuario;
    private SharedPreferences sharedPreferences;
    private UserModel user;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_description);

        book = this.getIntent().getExtras().getParcelable(getResources().getString(R.string.book));
        usuario = this.getIntent().getExtras().getString(getResources().getString(R.string.usuario_intent));
        sharedPreferences = this.getSharedPreferences(getResources().getString(R.string.shared_name), Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(usuario, getResources().getString(R.string.vacio));

        gson = new Gson();
        user = gson.fromJson(json, UserModel.class);

        CustomBookDescription bookDescription = findViewById(R.id.book_description);
        Glide.with(this).load(book.getImg()).into((ImageView) this.findViewById(R.id.imagen_desc));
        bookDescription.setTitulo(book.getTitle());
        bookDescription.setSubtitulo(book.getSubtitle());
        bookDescription.setEditorial(book.getPublisher());
        bookDescription.setAutores(book.getAuthors());
        bookDescription.setFecha(book.getPublishDate());
        bookDescription.setPrecio(String.valueOf(book.getPrice()));
        bookDescription.setDescripcion(book.getDescription());

        favoritos = bookDescription.getFavoritos();
        favoritos.setOnClickListener(this);

        checkFav();
    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View view) {
        ColorDrawable btnncolor = (ColorDrawable) favoritos.getBackground();
        int colorId = btnncolor.getColor();
        favoritos.getBackground();
        String colorIdS = colorId + getResources().getString(R.string.vacio);
        String colorRed = Color.RED + getResources().getString(R.string.vacio);

        if (colorIdS.equals(colorRed)) {
            changeFav();
            Toast.makeText(getBaseContext(), getResources().getString(R.string.libro_añadido), Toast.LENGTH_LONG).show();
        } else {
            changeNotFav();
            Toast.makeText(getBaseContext(), getResources().getString(R.string.libro_quitado), Toast.LENGTH_LONG).show();
        }

    }

    @SuppressLint("ResourceAsColor")
    private void changeFav() {
        ArrayList<BookModel> favArr = user.getFavoritos();
        favArr.add(book);
        user.setFavoritos(favArr);

        favoritos.setBackgroundColor(getResources().getColor(com.example.custombookdescription.R.color.fav));

        gson = new Gson();
        String json = gson.toJson(user);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(usuario);
        editor.putString(usuario, json);
        editor.apply();
    }

    @SuppressLint("ResourceAsColor")
    private void changeNotFav() {
        ArrayList<BookModel> favArr = user.getFavoritos();
        for (BookModel b: favArr) {
            if (b.getTitle().equals(book.getTitle())) {
                favArr.remove(b);
            }
        }

        user.setFavoritos(favArr);

        favoritos.setBackgroundColor(getResources().getColor(com.example.custombookdescription.R.color.not_fav));

        gson = new Gson();
        String json = gson.toJson(user);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(usuario);
        editor.putString(usuario, json);
        editor.apply();
    }

    private void checkFav() {

        ArrayList<BookModel> favArr = user.getFavoritos();
        favoritos.setBackgroundColor(getResources().getColor(com.example.custombookdescription.R.color.not_fav));

        for (BookModel b : favArr) {
            if (b.compareTo(book)) {
                favoritos.setBackgroundColor(getResources().getColor(com.example.custombookdescription.R.color.fav));
            }
        }

    }

}
