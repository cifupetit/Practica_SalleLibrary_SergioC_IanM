package com.example.cifu.practica_sallelibrary_sergioc_ianm.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cifu.practica_sallelibrary_sergioc_ianm.R;

import org.json.JSONException;
import org.json.JSONObject;

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
            safeInfo(view);
        } catch (JSONException e) {
            System.err.println("Error parsing JSONObject!");
        }

    }

    //Hashear contraseña antes de guardarla

    public void safeInfo(View view) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("usuario", usuarioValue.getText().toString());
        json.put("nombre", nombreValue.getText().toString());
        json.put("apellido", apellidoValue.getText().toString());
        json.put("contraseña", contraseñaValue.getText().toString());

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("usersInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(usuarioValue.getText().toString(), json.toString());
        editor.apply();

        /*
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("usersInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("usuario", usuarioValue.getText().toString());
        editor.putString("nombre", usuarioValue.getText().toString());
        editor.putString("apellido", usuarioValue.getText().toString());
        editor.putString("contraseña", usuarioValue.getText().toString());
        editor.apply();
        */

        Toast.makeText(getActivity().getBaseContext(), "User saved!", Toast.LENGTH_LONG).show();
    }


}
