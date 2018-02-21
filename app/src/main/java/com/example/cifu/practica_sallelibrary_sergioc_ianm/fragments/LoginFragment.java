package com.example.cifu.practica_sallelibrary_sergioc_ianm.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cifu.practica_sallelibrary_sergioc_ianm.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment implements View.OnClickListener {

    private TextView nombreUsuarioText;
    private EditText nombreUsuarioValue;
    private TextView contraseñaUsuarioText;
    private EditText contraseñaUsuarioValue;
    private Button login;
    private Button register;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_fragment, container, false);

        nombreUsuarioText = view.findViewById(R.id.login_nombre_text);
        nombreUsuarioValue = view.findViewById(R.id.login_nombre_value);
        contraseñaUsuarioText = view.findViewById(R.id.login_password_text);
        contraseñaUsuarioValue = view.findViewById(R.id.login_nombre_value);
        login = view.findViewById(R.id.login_login_button);
        register = view.findViewById(R.id.login_register_button);

        login.setOnClickListener(this);
        register.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

    }
}
