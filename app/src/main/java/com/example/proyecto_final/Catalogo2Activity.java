package com.example.proyecto_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Catalogo2Activity extends AppCompatActivity {

    private ImageButton btnHomeicon;
    private ImageButton btnFavicon;
    private ImageButton btnCarticon;
    private ImageButton btnProfileicon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo2);

        // Inicializamos las variables del menú
        btnHomeicon = findViewById(R.id.btn_homeicon);
        btnFavicon = findViewById(R.id.btn_favicon);
        btnCarticon = findViewById(R.id.btn_carticon);
        btnProfileicon = findViewById(R.id.btn_profileicon);

        // Nos lleva al catalogo
        btnHomeicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent alPrimerejercicio = new Intent(
                        Catalogo2Activity.this,
                        Catalogo2Activity.class
                );
                //Mandamos a la siguiente pantalla
                startActivity(alPrimerejercicio);
            }
        });

        // Nos lleva a la vista de losta de deseos
        btnFavicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent alPrimerejercicio = new Intent(
                        Catalogo2Activity.this,
                        FavoritoActivity.class
                );
                //Mandamos a la siguiente pantalla
                startActivity(alPrimerejercicio);
            }
        });

        // Nos lleva al carrito de compras
        btnCarticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent alPrimerejercicio = new Intent(
                        Catalogo2Activity.this,
                        CarritoActivity.class
                );
                //Mandamos a la siguiente pantalla
                startActivity(alPrimerejercicio);
            }
        });

        // Nos lleva al perfil de usuario
        btnProfileicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent alPrimerejercicio = new Intent(
                        Catalogo2Activity.this,
                        PerfilActivity.class
                );
                //Mandamos a la siguiente pantalla
                startActivity(alPrimerejercicio);
            }
        });
    }
}