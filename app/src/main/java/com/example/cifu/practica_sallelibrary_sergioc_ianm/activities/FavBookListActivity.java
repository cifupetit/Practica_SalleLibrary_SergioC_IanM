package com.example.cifu.practica_sallelibrary_sergioc_ianm.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.cifu.practica_sallelibrary_sergioc_ianm.R;
import com.example.cifu.practica_sallelibrary_sergioc_ianm.adapters.BookListAdapter;
import com.example.cifu.practica_sallelibrary_sergioc_ianm.models.BookModel;

import java.util.ArrayList;
import java.util.List;

public class FavBookListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<BookModel> data = new ArrayList<>();
    private BookListAdapter bookListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_book_list);

        data = getIntent().getParcelableArrayListExtra(getResources().getString(R.string.favoritos));

        ListView listView = this.findViewById(R.id.fav_books_list_view);

        bookListAdapter = new BookListAdapter(this, data, R.layout.book_list_item);
        listView.setAdapter(bookListAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BookModel book = bookListAdapter.getItem(position);

        Intent intentFavDesc = new Intent(this, FavBookDescription.class);

        Bundle bookData = new Bundle();
        bookData.putParcelable(getResources().getString(R.string.book), book);

        intentFavDesc.putExtras(bookData);

        startActivity(intentFavDesc);
    }
}
