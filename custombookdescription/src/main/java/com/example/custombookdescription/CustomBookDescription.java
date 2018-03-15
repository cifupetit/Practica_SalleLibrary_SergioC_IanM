package com.example.custombookdescription;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * TODO: document your custom view class.
 */
public class CustomBookDescription extends LinearLayout {
    private ImageView img;
    private TextView titulo, subtitulo, editorial, fecha, precio, autores, descripcion;

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
}
