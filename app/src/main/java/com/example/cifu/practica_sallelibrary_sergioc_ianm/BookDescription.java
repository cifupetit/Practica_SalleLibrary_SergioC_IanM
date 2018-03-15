package com.example.cifu.practica_sallelibrary_sergioc_ianm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.cifu.practica_sallelibrary_sergioc_ianm.models.BookModel;
import com.example.custombookdescription.CustomBookDescription;

import java.util.Arrays;

public class BookDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_description);

        BookModel book = this.getIntent().getExtras().getParcelable("book");

        CustomBookDescription bookDescription = findViewById(R.id.book_description);
        Glide.with(this).load(book.getImg()).into((ImageView) this.findViewById(R.id.imagen_desc));
        bookDescription.setImg(book.getImg());
        bookDescription.setTitulo(book.getTitle());
        bookDescription.setSubtitulo(book.getSubtitle());
        bookDescription.setEditorial(book.getPublisher());
        bookDescription.setAutores(Arrays.toString(book.getAuthors()));
        bookDescription.setFecha(book.getPublishDate());
        bookDescription.setPrecio(String.valueOf(book.getPrice()));
        bookDescription.setDescripcion(book.getDescription());

    }
}
