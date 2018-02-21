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
public class LoginFragment extends Fragment {

    private TextView nombreUsuario;
    private EditText inputNombreU;
    private TextView contraseñaUsuario;
    private EditText inputContraseñaU;
    private Button login;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login_fragment, container, false);

        //nombreUsuario = view.findViewById(id del XML);
        // y asi con todos antes del return

        return view;
    }

}
