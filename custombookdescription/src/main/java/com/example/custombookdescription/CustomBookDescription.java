package com.example.custombookdescription;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * TODO: document your custom view class.
 */
public class CustomBookDescription extends LinearLayout implements View.OnClickListener {
    private ImageView img;
    private TextView titulo, subtitulo, editorial, fecha, precio, autores, descripcion;
    private Button favoritos;

    public CustomBookDescription(Context context) {
        super(context);
        init(null, 0);
    }

    public CustomBookDescription(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CustomBookDescription(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }


    private void init(AttributeSet attrs, int defStyle) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_book_description, this);

        img = (ImageView) findViewById(R.id.imagen_desc);
        titulo = (TextView) findViewById(R.id.titulo_desc);
        subtitulo = (TextView) findViewById(R.id.subtitulo_desc);
        editorial = (TextView) findViewById(R.id.editorial_desc);
        fecha = (TextView) findViewById(R.id.fecha_desc);
        precio = (TextView) findViewById(R.id.precio_desc);
        autores = (TextView) findViewById(R.id.autores_desc);
        descripcion = (TextView) findViewById(R.id.descripcion_desc);
        favoritos = (Button) findViewById(R.id.fav_button);

        checkFav();

        favoritos.setOnClickListener(this);

        // Load attributes
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomBookDescription, defStyle, 0);

        Drawable auxImg = a.getDrawable(R.styleable.CustomBookDescription_img);
        if (auxImg != null) {setImg(auxImg);}

        float tamañoTexto = a.getDimension(R.styleable.CustomBookDescription_tamano_texto, 16);
        if (tamañoTexto > 0) {setTamañoTexto(tamañoTexto);}

        descripcion.setMovementMethod(new ScrollingMovementMethod());

        a.recycle();
    }

    public void setImg(Drawable img) {
        this.img.setImageDrawable(img);
        invalidate();
        requestLayout();
    }

    public void setTamañoTexto(float tamañoTexto) {
        this.titulo.setTextSize(tamañoTexto);
        this.subtitulo.setTextSize(tamañoTexto);
        this.editorial.setTextSize(tamañoTexto);
        this.fecha.setTextSize(tamañoTexto);
        this.precio.setTextSize(tamañoTexto);
        this.autores.setTextSize(tamañoTexto);
        this.descripcion.setTextSize(tamañoTexto);
        invalidate();
        requestLayout();
    }

    public void setTitulo(String titulo) {
        this.titulo.setText(titulo);
        invalidate();
        requestLayout();
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo.setText(subtitulo);
        invalidate();
        requestLayout();
    }

    public void setEditorial(String editorial) {
        this.editorial.setText(editorial);
        invalidate();
        requestLayout();
    }

    public void setFecha(String fecha) {
        this.fecha.setText(fecha);
        invalidate();
        requestLayout();
    }

    public void setPrecio(String precio) {
        this.precio.setText(precio);
        invalidate();
        requestLayout();
    }

    public void setAutores(String autores) {
        this.autores.setText(autores);
        invalidate();
        requestLayout();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.setText(descripcion);
        invalidate();
        requestLayout();
    }

    @SuppressLint("ResourceType")
    @Override
    public void onClick(View view) {
        ColorDrawable btnncolor = (ColorDrawable) favoritos.getBackground();
        int colorId = btnncolor.getColor();
        favoritos.getBackground();
        String colorIdS = colorId + "";
        String colorRed = Color.RED + "";
        String colorGreen = Color.GREEN + "";
        if (colorIdS.equals(colorRed)) {
            changeFav();
            Toast.makeText(this.getContext(),"Libro añadido a favoritos", Toast.LENGTH_LONG).show();
        } else {
            changeNotFav();
            Toast.makeText(this.getContext(),"Libro quitado de favoritos", Toast.LENGTH_LONG).show();
        }

        //if (favoritos.getDrawingCacheBackgroundColor()) {

        //}
    }

    @SuppressLint("ResourceAsColor")
    private void changeFav() {
        try {
            SharedPreferences sharedPreferences = this.getContext().getSharedPreferences("usersInfo", Context.MODE_PRIVATE);
            //String user = sharedPreferences.getString(usuarioValue.getText().toString(), "");
            String user = sharedPreferences.getString("a", "");
            JSONObject json = new JSONObject(user);
            String favoritosS = json.getString("favoritos");
            String[] favoritosArray = new String[] {favoritosS};
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < favoritosArray.length; i++) {
                jsonArray.put(favoritosArray[i]);
            }
            jsonArray.put(titulo.getText().toString());

            json.remove("favoritos");
            json.put("favoritos", jsonArray);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            //editor.remove(usuarioValue.getText().toString());
            editor.remove("a");
            //editor.putString(usuarioValue.getText().toString(), json.toString());
            editor.putString("a", json.toString());
            editor.apply();

            favoritos.setBackgroundColor(getResources().getColor(R.color.fav));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("ResourceAsColor")
    private void changeNotFav() {
        try {
            SharedPreferences sharedPreferences = this.getContext().getSharedPreferences("usersInfo", Context.MODE_PRIVATE);
            //String user = sharedPreferences.getString(usuarioValue.getText().toString(), "");
            String user = sharedPreferences.getString("a", "");
            JSONObject json = new JSONObject(user);
            String favoritosS = json.getString("favoritos");
            String[] favoritosArray = new String[] {favoritosS};
            JSONArray jsonArray = new JSONArray();
            for (int i = 0; i < favoritosArray.length; i++) {
                if (!(favoritosArray[i].equals(titulo.getText().toString()))) {
                    jsonArray.put(favoritosArray[i]);
                }
            }

            json.remove("favoritos");
            json.put("favoritos", jsonArray);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            //editor.remove(usuarioValue.getText().toString());
            editor.remove("a");
            //editor.putString(usuarioValue.getText().toString(), json.toString());
            editor.putString("a", json.toString());
            editor.apply();

            favoritos.setBackgroundColor(getResources().getColor(R.color.not_fav));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void checkFav() {
        SharedPreferences sharedPreferences = this.getContext().getSharedPreferences("usersInfo", Context.MODE_PRIVATE);
        String favoritosS = sharedPreferences.getString("favoritos", "");
        favoritos.setBackgroundColor(getResources().getColor(R.color.not_fav));
        if (!(favoritosS.equals(""))) {
            String[] favoritosArray = new String[] {favoritosS};
            for (int i = 0; i < favoritosArray.length; i++) {
                if (favoritosArray[i].equals(titulo.toString())) {
                    favoritos.setBackgroundColor(getResources().getColor(R.color.fav));
                }
            }
        }
    }

}
