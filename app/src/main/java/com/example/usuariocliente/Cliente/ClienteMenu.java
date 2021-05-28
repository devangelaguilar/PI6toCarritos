package com.example.usuariocliente.Cliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.usuariocliente.Cliente.History.ClienteHistory;
import com.example.usuariocliente.Cliente.Home.ClienteHome;
import com.example.usuariocliente.Cliente.Options.ClienteSettings;
import com.example.usuariocliente.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ClienteMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_menu);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ClienteHome()).commit();
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = item -> {
        Fragment selectedFragment = null;

        switch (item.getItemId()) {
            case R.id.nav_home:
                selectedFragment = new ClienteHome();
                break;
            case R.id.nav_history:
                selectedFragment = new ClienteHistory();
                break;
            case R.id.nav_settings:
                selectedFragment = new ClienteSettings();
                break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
        return true;
    };
}