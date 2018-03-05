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
    //private SearchBooksTask.SearchListener searchListener;

    public List<Volume> volumeList = new ArrayList<>();
    //private String latestQuery;
    private List<BookModel> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list);

        searchBooks("harry");
        parseBooksData();

        ListView listView = this.findViewById(R.id.books_list_view);

        BookListAdapter bookListAdapter = new BookListAdapter(this, data, R.layout.book_list_item);
        listView.setAdapter(bookListAdapter);

        listView.setOnItemClickListener(this);
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        searchListener = (SearchBooksTask.SearchListener) context;
    }*/

    public void searchBooks(String query) {
        /*if (query.equalsIgnoreCase(latestQuery)) {
            return;
        }*/
        if (searchTask != null) {
            searchTask.cancel(true);
        }
        //latestQuery = query;
        searchTask = new SearchBooksTask();
        //searchTask.setSearchListener(this);
        searchTask.execute(query);
        volumeList = searchTask.getVolumes();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), "Prueba libro " + position, Toast.LENGTH_LONG).show();
    }

    /*@Override
    public void onSearching() {
        searching = true;
        volumeList.clear();
        searchListener.onSearching();
    }

    @Override
    public void onResult(List<Volume> volumes) {
        searching = false;
        volumeList = volumes;
        searchListener.onResult(volumeList);
    }*/

    /*public String getLatestQuery() {
        return latestQuery;
    }*/

    public boolean isSearching() {
        return searching;
    }

    private void parseBooksData() {
        //data = new ArrayList<BookModel>(volumeList.size());
        for (int i = 0; i < volumeList.size(); i++) {
            Volume.VolumeInfo.ImageLinks imageLinks = volumeList.get(i).getVolumeInfo().getImageLinks();

            if (imageLinks != null) {
                String medium = imageLinks.getMedium();
                String large = imageLinks.getLarge();
                String small = imageLinks.getSmall();
                String thumbnail = imageLinks.getThumbnail();
                String smallThumbnail = imageLinks.getSmallThumbnail();

                String imageLink = "";
                if (large != null) {
                    imageLink = large;
                } else if (medium != null) {
                    imageLink = medium;
                } else if (small != null) {
                    imageLink = small;
                } else if (thumbnail != null) {
                    imageLink = thumbnail;
                } else if (smallThumbnail != null) {
                    imageLink = smallThumbnail;
                }

                imageLink = imageLink.replace("edge=curl", "");
                System.out.println(imageLink);

                data.get(i).setImg(imageLink);
            } else {
                System.err.println("No images ??");
            }

            Volume.VolumeInfo volumeInfo = volumeList.get(i).getVolumeInfo();
            Volume.SaleInfo saleInfo = volumeList.get(i).getSaleInfo();

            if (volumeInfo != null) {
                //Volume.VolumeInfo.ImageLinks imageLinks = volumeInfo.getImageLinks();
                if (volumeInfo.getTitle() != null) {
                    data.get(i).setTitle(volumeInfo.getTitle());
                }
                if (volumeInfo.getSubtitle() != null) {
                    data.get(i).setSubtitle(volumeInfo.getSubtitle());
                }
                if (volumeInfo.getDescription() != null) {
                    data.get(i).setDescription(volumeInfo.getDescription());
                }
                if (volumeInfo.getPublisher() != null) {
                    data.get(i).setPublisher(volumeInfo.getPublisher());
                }
                if (volumeInfo.getAuthors() != null) {
                    data.get(i).setAuthors(volumeInfo.getAuthors().toArray(new String[volumeInfo.getAuthors().size()]));
                }
                if (volumeInfo.getPublishedDate() != null) {
                    data.get(i).setPublishDate(volumeInfo.getPublishedDate());
                }
                if (saleInfo != null) {
                    //Volume.SaleInfo.RetailPrice retailPrice = saleInfo.getRetailPrice();
                    Volume.SaleInfo.ListPrice listPrice = saleInfo.getListPrice();
                    /*if (retailPrice != null) {
                        metadata.putDouble(BookMetadata.RETAIL_PRICE, retailPrice.getAmount());
                        metadata.putString(BookMetadata.RETAIL_PRICE_CURRENCY_CODE, retailPrice.getCurrencyCode());
                    }*/
                    if (listPrice != null) {
                        //metadata.putDouble(BookMetadata.LIST_PRICE, listPrice.getAmount());
                        //metadata.putString(BookMetadata.LIST_PRICE_CURRENCY_CODE, listPrice.getCurrencyCode());
                        data.get(i).setPrice(listPrice.getAmount());
                    }
                }

            }
        }

    }

}
