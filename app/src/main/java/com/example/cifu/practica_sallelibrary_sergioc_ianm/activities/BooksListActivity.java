package com.example.cifu.practica_sallelibrary_sergioc_ianm.activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.cifu.practica_sallelibrary_sergioc_ianm.R;
import com.example.cifu.practica_sallelibrary_sergioc_ianm.SearchBooksTask;
import com.example.cifu.practica_sallelibrary_sergioc_ianm.adapters.BookListAdapter;
import com.example.cifu.practica_sallelibrary_sergioc_ianm.models.BookModel;
import com.example.cifu.practica_sallelibrary_sergioc_ianm.models.UserModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class BooksListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private SearchBooksTask searchTask;

    private List<BookModel> data = new ArrayList<>();
    private BookListAdapter bookListAdapter;

    private String usuario;
    private SharedPreferences sharedPreferences;
    private UserModel user;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listView = this.findViewById(R.id.books_list_view);

        bookListAdapter = new BookListAdapter(this, data, R.layout.book_list_item);
        listView.setAdapter(bookListAdapter);

        searchBooks("A");

        usuario = this.getIntent().getExtras().getString(getResources().getString(R.string.usuario_intent));
        sharedPreferences = this.getSharedPreferences(getResources().getString(R.string.shared_name), Context.MODE_PRIVATE);
        String json = sharedPreferences.getString(usuario, getResources().getString(R.string.vacio));
        gson = new Gson();
        user = gson.fromJson(json, UserModel.class);

        listView.setOnItemClickListener(this);
    }

    public void searchBooks(String query) {
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
        BookModel book = bookListAdapter.getItem(position);

        Intent intentDesc = new Intent(this, BookDescription.class);

        Bundle bookData = new Bundle();
        bookData.putParcelable(getResources().getString(R.string.book), book);

        intentDesc.putExtras(bookData);
        intentDesc.putExtra(getResources().getString(R.string.usuario_intent), this.getIntent().getExtras().getString(getResources().getString(R.string.usuario_intent)));

        startActivity(intentDesc);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.fav_action:
                usuario = this.getIntent().getExtras().getString(getResources().getString(R.string.usuario_intent));
                sharedPreferences = this.getSharedPreferences(getResources().getString(R.string.shared_name), Context.MODE_PRIVATE);
                String json = sharedPreferences.getString(usuario, getResources().getString(R.string.vacio));
                gson = new Gson();
                user = gson.fromJson(json, UserModel.class);

                Intent intent = new Intent(this, FavBookListActivity.class);
                intent.putParcelableArrayListExtra(getResources().getString(R.string.favoritos), user.getFavoritos());
                startActivity(intent);
                return true;

            case R.id.search_action:
                String query = getIntent().getStringExtra(SearchManager.QUERY);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search_action);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchBooks(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //searchBooks(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
