package com.example.usuariocliente.Driver.Info;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.usuariocliente.Login;
import com.example.usuariocliente.R;

public class DriverInfo extends Fragment {
    Button logout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.info_driver, container, false);
        logout = view.findViewById(R.id.logout);
        logout.setOnClickListener(v -> {
            logOut();
            Intent i = new Intent(getContext(), Login.class);
            startActivity(i);
            getActivity().finish();
        });
        return view;
    }

    private void logOut() {
        SharedPreferences preferences = getActivity().getSharedPreferences("Credenciales", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("sesion_iniciada", Boolean.FALSE);

        editor.apply();
    }
}
