package com.example.cifu.practica_sallelibrary_sergioc_ianm;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listView = this.findViewById(R.id.books_list_view);

        bookListAdapter = new BookListAdapter(this, data, R.layout.book_list_item);
        listView.setAdapter(bookListAdapter);

        searchBooks("A");

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
        //Toast.makeText(getApplicationContext(), "Prueba libro " + bookListAdapter.getItem(position).getTitle(), Toast.LENGTH_LONG).show();
        BookModel book = bookListAdapter.getItem(position);

        Intent intentDesc = new Intent(this, BookDescription.class);

        Bundle bookData = new Bundle();
        bookData.putParcelable("book", book);

        intentDesc.putExtras(bookData);
        startActivity(intentDesc);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            /*case R.id.add_action:
                //Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();
                dataModel.add(generateRandomString());
                adapter.notifyDataSetChanged();
                return true;*/

            case R.id.search_action:
                //Toast.makeText(this, "search", Toast.LENGTH_SHORT).show();
                String query = getIntent().getStringExtra(SearchManager.QUERY);
                Log.e("log", query);
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
                //bookListAdapter.notifyDataSetChanged();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //bookListAdapter.getFilter().filter(s);
                //searchBooks(s);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}
