package com.example.cifu.practica_sallelibrary_sergioc_ianm.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cifu.practica_sallelibrary_sergioc_ianm.BooksListActivity;
import com.example.cifu.practica_sallelibrary_sergioc_ianm.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment implements View.OnClickListener {

    private EditText usuarioValue, contraseñaValue, nombreValue, apellidoValue;
    private Button signup;

    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.signup_fragment, container, false);

        usuarioValue = view.findViewById(R.id.signup_usuario_value);
        nombreValue = view.findViewById(R.id.signup_nombre_value);
        apellidoValue = view.findViewById(R.id.signup_apellido_value);
        contraseñaValue = view.findViewById(R.id.signup_password_value);
        signup = view.findViewById(R.id.signup_signup_button);

        signup.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        try {
            deletePreferences();
            if (checkEmpty() && checkUser()) {
                safeInfo();
                if (view.getId() == R.id.login_login_button) {
                    Intent intent = new Intent(this.getActivity(), BooksListActivity.class);
                    startActivity(intent);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private boolean checkUser() throws JSONException {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("usersInfo", Context.MODE_PRIVATE);
        String user = sharedPreferences.getString(usuarioValue.getText().toString(), "");

        if (!user.equals("")) {
            Toast.makeText(getActivity().getBaseContext(), "¡Este usuario ya existe!", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    private boolean checkEmpty() {
        if (usuarioValue.getText().toString().equals("") || usuarioValue.getText().toString().equals(" ")) {
            Toast.makeText(getActivity().getBaseContext(), "¡El campo usuario no puede estar vacío!", Toast.LENGTH_LONG).show();
            return false;
        }

        if (nombreValue.getText().toString().equals("") || nombreValue.getText().toString().equals(" ")) {
            Toast.makeText(getActivity().getBaseContext(), "¡El campo nombre no puede estar vacío!", Toast.LENGTH_LONG).show();
            return false;
        }

        if (apellidoValue.getText().toString().equals("") || apellidoValue.getText().toString().equals(" ")) {
            Toast.makeText(getActivity().getBaseContext(), "¡El campo apellido no puede estar vacío!", Toast.LENGTH_LONG).show();
            return false;
        }

        if (contraseñaValue.getText().toString().equals("") || contraseñaValue.getText().toString().equals(" ")) {
            Toast.makeText(getActivity().getBaseContext(), "¡El campo contraseña no puede estar vacío!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    //Hashear contraseña antes de guardarla

    public void safeInfo() throws JSONException {

        JSONObject json = new JSONObject();
        json.put("usuario", usuarioValue.getText().toString());
        json.put("nombre", nombreValue.getText().toString());
        json.put("apellido", apellidoValue.getText().toString());
        json.put("contraseña", contraseñaValue.getText().toString());

        JSONArray favArray = new JSONArray();
        json.put("favoritos", favArray);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("usersInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(usuarioValue.getText().toString(), json.toString());
        editor.apply();

        Toast.makeText(getActivity().getBaseContext(), "¡Usuario creado!", Toast.LENGTH_LONG).show();

    }

    public void deletePreferences () {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("usersInfo", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
    }


}
