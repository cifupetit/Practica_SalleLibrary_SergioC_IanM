package com.example.cifu.practica_sallelibrary_sergioc_ianm.activities;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.cifu.practica_sallelibrary_sergioc_ianm.R;
import com.example.cifu.practica_sallelibrary_sergioc_ianm.fragments.LoginFragment;
import com.example.cifu.practica_sallelibrary_sergioc_ianm.fragments.SignupFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoginFragment loginFragment = new LoginFragment();
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().
                add(R.id.main_screen, loginFragment)
                .commit();


    }


    public void onRegisterSelected() {

        FragmentManager fragmentManager = getFragmentManager();
        SignupFragment signupFragment = new SignupFragment();
        fragmentManager.beginTransaction().
                replace(R.id.main_screen, signupFragment).
                addToBackStack(null).
                commit();

    }

}
