package com.example.cifu.practica_sallelibrary_sergioc_ianm.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.cifu.practica_sallelibrary_sergioc_ianm.BooksListActivity;
import com.example.cifu.practica_sallelibrary_sergioc_ianm.MainActivity;
import com.example.cifu.practica_sallelibrary_sergioc_ianm.R;

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
        if (view.getId() == R.id.login_login_button) {
            Intent intent = new Intent(this.getActivity(), BooksListActivity.class);
            startActivity(intent);
        }

        if (view.getId() == R.id.login_signup_button) {
            MainActivity activity = (MainActivity) getActivity();
            activity.onRegisterSelected();
        }
    }

}
