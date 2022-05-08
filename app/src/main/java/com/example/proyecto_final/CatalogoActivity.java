package com.example.proyecto_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class CatalogoActivity extends AppCompatActivity {

    /*
        Todos los botones nos llevaran a la actividad de registro
        ya que debes tener una cuenta para realizar cualquier actividad
    */
    private Button btnMinexbox;
    private Button btnMineps4;
    private Button btnFalloutxbox;
    private Button btnFalloutps4;
    private Button btnEldenbox;
    private Button btnEldenps5;
    private ImageButton btnHomeicon;
    private ImageButton btnFavicon;
    private ImageButton btnCarticon;
    private ImageButton btnProfileicon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);
        btnMinexbox = findViewById(R.id.btn_minexbox);
        btnMineps4 = findViewById(R.id.btn_mineps4);
        btnFalloutxbox = findViewById(R.id.btn_falloutxbox);
        btnFalloutps4 = findViewById(R.id.btn_falloutps4);
        btnEldenbox = findViewById(R.id.btn_eldenxbox);
        btnEldenps5 = findViewById(R.id.btn_eldenps5);
        btnHomeicon = findViewById(R.id.btn_homeicon);
        btnFavicon = findViewById(R.id.btn_favicon);
        btnCarticon = findViewById(R.id.btn_carticon);
        btnProfileicon = findViewById(R.id.btn_profileicon);


        btnMinexbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent alPrimerejercicio = new Intent(
                        CatalogoActivity.this,
                        RegistroActivity.class
                );
                //Mandamos a la siguiente pantalla
                startActivity(alPrimerejercicio);
            }
        });

        btnMineps4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent alPrimerejercicio = new Intent(
                        CatalogoActivity.this,
                        RegistroActivity.class
                );
                //Mandamos a la siguiente pantalla
                startActivity(alPrimerejercicio);
            }
        });

        btnFalloutxbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent alPrimerejercicio = new Intent(
                        CatalogoActivity.this,
                        RegistroActivity.class
                );
                //Mandamos a la siguiente pantalla
                startActivity(alPrimerejercicio);
            }
        });

        btnFalloutps4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent alPrimerejercicio = new Intent(
                        CatalogoActivity.this,
                        RegistroActivity.class
                );
                //Mandamos a la siguiente pantalla
                startActivity(alPrimerejercicio);
            }
        });

        btnEldenbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent alPrimerejercicio = new Intent(
                        CatalogoActivity.this,
                        RegistroActivity.class
                );
                //Mandamos a la siguiente pantalla
                startActivity(alPrimerejercicio);
            }
        });

        btnEldenps5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent alPrimerejercicio = new Intent(
                        CatalogoActivity.this,
                        RegistroActivity.class
                );
                //Mandamos a la siguiente pantalla
                startActivity(alPrimerejercicio);
            }
        });

        btnHomeicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent alPrimerejercicio = new Intent(
                        CatalogoActivity.this,
                        CatalogoActivity.class
                );
                //Mandamos a la siguiente pantalla
                startActivity(alPrimerejercicio);
            }
        });

        btnFavicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent alPrimerejercicio = new Intent(
                        CatalogoActivity.this,
                        RegistroActivity.class
                );
                //Mandamos a la siguiente pantalla
                startActivity(alPrimerejercicio);
            }
        });

        btnCarticon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent alPrimerejercicio = new Intent(
                        CatalogoActivity.this,
                        RegistroActivity.class
                );
                //Mandamos a la siguiente pantalla
                startActivity(alPrimerejercicio);
            }
        });

        btnProfileicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent alPrimerejercicio = new Intent(
                        CatalogoActivity.this,
                        RegistroActivity.class
                );
                //Mandamos a la siguiente pantalla
                startActivity(alPrimerejercicio);
            }
        });
    }
}