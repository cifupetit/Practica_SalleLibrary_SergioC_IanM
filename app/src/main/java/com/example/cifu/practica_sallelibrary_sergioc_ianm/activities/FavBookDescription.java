package com.example.cifu.practica_sallelibrary_sergioc_ianm.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.cifu.practica_sallelibrary_sergioc_ianm.R;
import com.example.cifu.practica_sallelibrary_sergioc_ianm.models.BookModel;
import com.example.custombookdescription.CustomBookDescription;

public class FavBookDescription extends AppCompatActivity {

    private BookModel book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_book_description);

        book = this.getIntent().getExtras().getParcelable(getResources().getString(R.string.book));

        CustomBookDescription bookDescription = findViewById(R.id.fav_book_description);
        Glide.with(this).load(book.getImg()).into((ImageView) this.findViewById(R.id.imagen_desc));
        bookDescription.setTitulo(book.getTitle());
        bookDescription.setSubtitulo(book.getSubtitle());
        bookDescription.setEditorial(book.getPublisher());
        bookDescription.setAutores(book.getAuthors());
        bookDescription.setFecha(book.getPublishDate());
        bookDescription.setPrecio(String.valueOf(book.getPrice()));
        bookDescription.setDescripcion(book.getDescription());
        bookDescription.disableFavButton();

    }
}
