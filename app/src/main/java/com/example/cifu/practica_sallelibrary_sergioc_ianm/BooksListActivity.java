package com.example.cifu.practica_sallelibrary_sergioc_ianm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.cifu.practica_sallelibrary_sergioc_ianm.adapters.BookListAdapter;
import com.example.cifu.practica_sallelibrary_sergioc_ianm.models.BookModel;
import com.google.api.services.books.model.Volume;

import java.util.ArrayList;
import java.util.List;

public class BooksListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private boolean searching;

    private SearchBooksTask searchTask;

    private List<Volume> volumeList = new ArrayList<>();
    //private String latestQuery;
    private List<BookModel> data = new ArrayList<>();

    private BookListAdapter bookListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list);

        ListView listView = this.findViewById(R.id.books_list_view);

        bookListAdapter = new BookListAdapter(this, data, R.layout.book_list_item);
        listView.setAdapter(bookListAdapter);

        searchBooks("harry");

        listView.setOnItemClickListener(this);
    }

    public void searchBooks(String query) {
        /*if (query.equalsIgnoreCase(latestQuery)) {
            return;
        }*/
        if (searchTask != null) {
            searchTask.cancel(true);
        }
        searchTask = new SearchBooksTask();
        searchTask.setBookAdapter(bookListAdapter);
        searchTask.setData(data);
        searchTask.execute(query);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), "Prueba libro " + position, Toast.LENGTH_LONG).show();
    }
}
