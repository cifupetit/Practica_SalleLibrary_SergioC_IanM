package com.example.cifu.practica_sallelibrary_sergioc_ianm.fragments;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cifu.practica_sallelibrary_sergioc_ianm.BooksListActivity;
import com.example.cifu.practica_sallelibrary_sergioc_ianm.MainActivity;
import com.example.cifu.practica_sallelibrary_sergioc_ianm.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private EditText usuarioValue, contraseñaValue;
    private Button login,signup;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        usuarioValue = view.findViewById(R.id.login_usuario_value);
        contraseñaValue = view.findViewById(R.id.login_password_value);
        login = view.findViewById(R.id.login_login_button);
        signup = view.findViewById(R.id.login_signup_button);

        login.setOnClickListener(this);
        signup.setOnClickListener(this);

        return view;
    }


    @Override
    public void onClick(View view) {
        try {
            if (view.getId() == R.id.login_signup_button) {
                MainActivity activity = (MainActivity) getActivity();
                activity.onRegisterSelected();
            } else if (checkEmpty() && checkUser()) {
                Toast.makeText(getActivity().getBaseContext(), "¡Datos correctos!", Toast.LENGTH_LONG).show();
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

        if (user.equals("")) {
            Toast.makeText(getActivity().getBaseContext(), "¡No existe este usuario!", Toast.LENGTH_LONG).show();
            return false;
        } else {
            JSONObject json = new JSONObject(user);
            String contraseña = json.getString("contraseña");
            if (!(contraseñaValue.getText().toString().equals(contraseña))) {
                Toast.makeText(getActivity().getBaseContext(), "¡Contraseña incorrecta!", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        return true;
    }

    private boolean checkEmpty() {
        if (usuarioValue.getText().toString().equals("") || usuarioValue.getText().toString().equals(" ")) {
            Toast.makeText(getActivity().getBaseContext(), "¡El campo usuario no puede estar vacío!", Toast.LENGTH_LONG).show();
            return false;
        }

        if (contraseñaValue.getText().toString().equals("") || contraseñaValue.getText().toString().equals(" ")) {
            Toast.makeText(getActivity().getBaseContext(), "¡El campo contraseña no puede estar vacío!", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

}
