package com.example.cifu.practica_sallelibrary_sergioc_ianm.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.cifu.practica_sallelibrary_sergioc_ianm.R;
import com.example.cifu.practica_sallelibrary_sergioc_ianm.models.BookModel;

import java.util.List;

/**
 * Created by cifu on 04/03/2018.
 */

public class BookListAdapter extends BaseAdapter {
    private Activity activity;
    private List<BookModel> data;
    private LayoutInflater inflater;
    private int bookItemLayout;

    public BookListAdapter(Activity activity, List<BookModel> data, int bookItemLayout) {
        this.activity = activity;
        this.data = data;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.bookItemLayout = bookItemLayout;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public BookModel getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //set custom layout
        if (convertView == null) {convertView = this.inflater.inflate(this.bookItemLayout, null);}
        //get views from custom layout
        TextView booTitle = (TextView) convertView.findViewById(R.id.titulo_book_list);
        TextView bookSubtitle = (TextView) convertView.findViewById(R.id.subtitulo_book_list);
        TextView bookAuthors = (TextView) convertView.findViewById(R.id.autores_book_list);
        TextView bookPublisher = (TextView) convertView.findViewById(R.id.editorial_book_list);
        TextView bookDate = (TextView) convertView.findViewById(R.id.fecha_book_list);
        TextView bookPrice = (TextView) convertView.findViewById(R.id.precio_book_list);
        TextView bookDescription = (TextView) convertView.findViewById(R.id.descripcion_book_list);

        //set values for item at position
        Glide.with(this.activity).load(data.get(position).getImg()).into((ImageView) convertView.findViewById(R.id.imagen_book_list));
        booTitle.setText(data.get(position).getTitle());
        bookSubtitle.setText(data.get(position).getSubtitle());
        bookAuthors.setText(data.get(position).getAuthors());
        bookPublisher.setText(data.get(position).getPublisher());
        bookDate.setText(data.get(position).getPublishDate());
        bookPrice.setText(String.valueOf(data.get(position).getPrice()));
        bookDescription.setText(data.get(position).getDescription());

        return convertView;
    }
}
