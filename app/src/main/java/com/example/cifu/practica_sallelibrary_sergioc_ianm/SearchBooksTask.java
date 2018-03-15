package com.example.cifu.practica_sallelibrary_sergioc_ianm;

import android.os.AsyncTask;

import com.example.cifu.practica_sallelibrary_sergioc_ianm.adapters.BookListAdapter;
import com.example.cifu.practica_sallelibrary_sergioc_ianm.models.BookModel;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.services.books.Books;
import com.google.api.services.books.model.Volume;
import com.google.common.primitives.Ints;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by cifu on 05/03/2018.
 */

public class SearchBooksTask extends AsyncTask<String, Void, List<Volume>> {
    private List<BookModel> data;
    private BookListAdapter bookAdapter;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Volume> doInBackground(String... params) {

        String query = params[0];

        // If the query seems to be an ISBN we add the isbn special keyword https://developers.google.com/books/docs/v1/using#PerformingSearch
        if (Ints.tryParse(query) != null && (query.length() == 13 || query.length() == 10)) {
            query = query.concat("+isbn:" + query);
        }

        // Creates the books api client
        Books books = new Books.Builder(AndroidHttp.newCompatibleTransport(), AndroidJsonFactory.getDefaultInstance(), null)
                .setApplicationName(BuildConfig.APPLICATION_ID)
                .build();

        try {
            // Executes the query
            Books.Volumes.List list = books.volumes().list(query).setProjection("LITE");
            return list.execute().getItems();
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    protected void onPostExecute(List<Volume> volumes) {
        super.onPostExecute(volumes);
        parseBooksData(volumes == null ? Collections.<Volume>emptyList() : volumes);
        bookAdapter.notifyDataSetChanged();
    }

    public void setData(List<BookModel> data) {
        this.data = data;
    }

    public void setBookAdapter(BookListAdapter bookAdapter) {
        this.bookAdapter = bookAdapter;
    }

    public void parseBooksData( List<Volume> volumes ) {
        //data = new ArrayList<>(volumes.size());
        for (int i = 0; i < volumes.size(); i++) {
            Volume.VolumeInfo.ImageLinks imageLinks = volumes.get(i).getVolumeInfo().getImageLinks();

            String image = "";

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

                image = imageLink;
            } else {
                System.err.println("No images ??");
            }

            Volume.VolumeInfo volumeInfo = volumes.get(i).getVolumeInfo();
            Volume.SaleInfo saleInfo = volumes.get(i).getSaleInfo();

            String title = "", subtitle = "", description = "", publisher = "", publishDate = "";
            //String[] authors = new String[volumeInfo.getAuthors().size()];
            String authors = "";
            Double price = 0.0;

            if (volumeInfo != null) {
                //Volume.VolumeInfo.ImageLinks imageLinks = volumeInfo.getImageLinks();
                if (volumeInfo.getTitle() != null) {
                    title = volumeInfo.getTitle();
                }
                if (volumeInfo.getSubtitle() != null) {
                    subtitle = volumeInfo.getSubtitle();
                }
                if (volumeInfo.getDescription() != null) {
                    description = volumeInfo.getDescription();
                }
                if (volumeInfo.getPublisher() != null) {
                    publisher = volumeInfo.getPublisher();
                }
                if (volumeInfo.getAuthors() != null) {
                    //authors = volumeInfo.getAuthors().toArray(new String[volumeInfo.getAuthors().size()]);
                    authors = Arrays.toString(volumeInfo.getAuthors().toArray());
                }
                if (volumeInfo.getPublishedDate() != null) {
                    publishDate = volumeInfo.getPublishedDate();
                }
                if (saleInfo != null) {
                    //Volume.SaleInfo.RetailPrice retailPrice = saleInfo.getRetailPrice();
                    Volume.SaleInfo.ListPrice listPrice = saleInfo.getListPrice();
                    /*if (retailPrice != null) {
                        metadata.putDouble(BookMetadata.RETAIL_PRICE, retailPrice.getAmount());
                        metadata.putString(BookMetadata.RETAIL_PRICE_CURRENCY_CODE, retailPrice.getCurrencyCode());
                    }*/
                    if (listPrice != null) {
                        price = listPrice.getAmount();
                    }
                }

            }
            BookModel book = new BookModel(image, title, subtitle, description, publisher, authors, publishDate, price);
            data.add(book);
        }
    }
}
