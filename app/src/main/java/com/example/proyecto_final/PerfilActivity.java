package com.example.proyecto_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.proyecto_final.databinding.ActivityPerfilBinding;
import com.google.android.material.button.MaterialButton;

public class PerfilActivity extends AppCompatActivity {

    /*
        Implementamos el viewBinding en este Screen
     */

    private ActivityPerfilBinding binding;
    // Atributo Intent
    private Intent datosScreenRegistro = getIntent();

    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPerfilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*
            Tomamos las variables tipo extra que nos mandó el screen anterior,
            si estos datos lo necesitamos en otras pantallas lo declaramos como atributo
            En este caso sera como variable.

            Intent datosScreenRegistro = getIntent();
         */

        datosScreenRegistro = getIntent();
        actionBar = getSupportActionBar();

        /*
            Accedemos a cada valor a partir del tipo de dato
            Si esperamos recibir un entero: getIntExtra("nombre", valor_defecto);
            Si esperamos recibir un String: getStringExtra("nombre");
         */
        final String nombre = datosScreenRegistro. getStringExtra("nombre");

        /*
            Mostramos el nombre en nuetro TextView
         */
        binding.tvNombre.setText(nombre);

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}