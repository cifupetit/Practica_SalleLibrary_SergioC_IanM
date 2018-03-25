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

import com.example.cifu.practica_sallelibrary_sergioc_ianm.activities.BooksListActivity;
import com.example.cifu.practica_sallelibrary_sergioc_ianm.R;
import com.example.cifu.practica_sallelibrary_sergioc_ianm.models.BookModel;
import com.example.cifu.practica_sallelibrary_sergioc_ianm.models.UserModel;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.ArrayList;

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
            //deletePreferences();
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
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getResources().getString(R.string.shared_name), Context.MODE_PRIVATE);
        String user = sharedPreferences.getString(usuarioValue.getText().toString(), getResources().getString(R.string.vacio));

        if (!user.equals(getResources().getString(R.string.vacio))) {
            Toast.makeText(getActivity().getBaseContext(), getResources().getString(R.string.usuario_ya_existe), Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    private boolean checkEmpty() {
        if (usuarioValue.getText().toString().equals(getResources().getString(R.string.vacio)) || usuarioValue.getText().toString().equals(getResources().getString(R.string.blanco))) {
            Toast.makeText(getActivity().getBaseContext(), getResources().getString(R.string.usuario_vacio), Toast.LENGTH_LONG).show();
            return false;
        }

        if (nombreValue.getText().toString().equals(getResources().getString(R.string.vacio)) || nombreValue.getText().toString().equals(getResources().getString(R.string.blanco))) {
            Toast.makeText(getActivity().getBaseContext(), getResources().getString(R.string.nombre_vacio), Toast.LENGTH_LONG).show();
            return false;
        }

        if (apellidoValue.getText().toString().equals(getResources().getString(R.string.vacio)) || apellidoValue.getText().toString().equals(getResources().getString(R.string.blanco))) {
            Toast.makeText(getActivity().getBaseContext(), getResources().getString(R.string.apellido_vacio), Toast.LENGTH_LONG).show();
            return false;
        }

        if (contraseñaValue.getText().toString().equals(getResources().getString(R.string.vacio)) || contraseñaValue.getText().toString().equals(" ")) {
            Toast.makeText(getActivity().getBaseContext(), getResources().getString(R.string.contraseña_vacia), Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public void safeInfo() throws JSONException {

        ArrayList<BookModel> favoritos = new ArrayList<>();
        UserModel userModel = new UserModel(usuarioValue.getText().toString(), nombreValue.getText().toString(), apellidoValue.getText().toString(), contraseñaValue.getText().toString(), favoritos);

        Gson gson = new Gson();
        String json = gson.toJson(userModel);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getResources().getString(R.string.shared_name), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(usuarioValue.getText().toString(), json);
        editor.apply();

        Toast.makeText(getActivity().getBaseContext(), getResources().getString(R.string.usuario_creado), Toast.LENGTH_LONG).show();
    }

    public void deletePreferences () {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getResources().getString(R.string.shared_name), Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
    }

}