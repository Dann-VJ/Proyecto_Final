package com.example.proyecto_final;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyecto_final.databinding.ActivityPerfilBinding;
import com.example.proyecto_final.databinding.ActivityRegistroBinding;
import com.example.proyecto_final.databinding.ActivitySesionBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class SesionActivity extends AppCompatActivity {

    private ActivitySesionBinding binding;
    private ActionBar actionBar;

    private final String URL_SERV = "https://sofcodeqro.com.mx/GameStore/back/usuarios/acceso";

    /*
    Peticiones romotas con Volley
     */
    private RequestQueue conServidor;
    private StringRequest petServidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySesionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        /*
        Inicializamos los componentes
         */
        conServidor = Volley.newRequestQueue(SesionActivity.this);

        // Validamos lo que se escribe en el campo correo
        binding.tietCorreo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                validarCorreo(editable.toString());
            }
        });

        // Validamos lo que se va escribiendo en el campo de contraseña
        binding.tietContraseA.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                validarContrasena(editable.toString());
            }
        });



        // Nos lleva al catalogo donde nos deja entrar a todas las opciones
        binding.btnCuentain.setOnClickListener(view -> {

            /*
            Validamos el correo
            */
            if ( !validarCorreo(binding.tietCorreo.getText().toString()) ) {
                return;
            }

            /*
            Validamos la contraseña
            */
            if ( !validarContrasena(binding.tietContraseA.getText().toString()) ) {
                return;
            }

            final ProgressDialog progress = new ProgressDialog(
                    SesionActivity.this
            );

            /*
            Ya validado los valores, tomar los valores que el servicio necesita
             */
            final String correo = binding.tietCorreo.getText().toString();
            final String contra = binding.tietContraseA.getText().toString();

            progress.setTitle("Iniciando sesión");
            progress.setMessage("Por favor espera...");
            progress.setIndeterminate(true);
            progress.setCancelable(false);
            progress.show();

            // Utilizamos las funciones landa
            petServidor = new StringRequest(
                Request.Method.POST, URL_SERV,
                response -> {

                    /*
                    Si el servicio responde intentamos convertir la respuesta
                    en un objeto json
                     */
                    try {
                        /*
                        Toast.makeText(
                                this,
                                response,
                                Toast.LENGTH_LONG
                        ).show();*/
                        JSONObject objrespuesta = new JSONObject(response);

                        /*
                        Si resultado es verdadero, redireccionamos
                        al carrito
                         */
                        if (objrespuesta.getBoolean("resultado")== true) {
                            Toast.makeText(
                                    this,
                                    "Bienvenido a Game Store",
                                    Toast.LENGTH_LONG
                            ).show();

                            startActivity(
                                new Intent(
                                    SesionActivity.this,
                                    Catalogo2Activity.class
                                )
                            );
                        }
                        // Si el correo o contraseña son incorrectos
                        else {
                            Snackbar
                                    .make(view, objrespuesta.getString("mensaje"), Snackbar.LENGTH_INDEFINITE)
                                    .setAction("Aceptar", viewSnack -> {
                                        binding.tietCorreo.setText("");
                                        binding.tietContraseA.setText("");
                                        binding.tietCorreo.requestFocus();
                                    }).show();
                        }
                        progress.hide();
                    }

                    /*
                    Si no responde servicio responde intentamos convertir la respuesta
                    en un objeto json
                     */
                    catch(Exception e) {
                        Toast.makeText(
                                this,
                                e.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();

                        progress.hide();
                    }
                },

                /*
                Si no responde o responde un codigo de error
                 */
                errorResponse -> {
                    Toast.makeText(
                            this,
                            errorResponse.toString(),
                            Toast.LENGTH_LONG
                    ).show();
                }
            )
            {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> parametros = new HashMap<>();
                    parametros.put("correo", correo);
                    parametros.put("contra", Utils.md5(contra));
                    return parametros;
                }
            };

            /*
            Consumir el servicio
             */
            conServidor.add(petServidor);
        });
    }

    public boolean validarCorreo(String texto) {
        /*
            Librería android.Patterns evalua si un texto tiene el formato
            correcto para un correo
         */
        if(Patterns.EMAIL_ADDRESS.matcher(texto).matches()) {
            //Eliminamos el error
            binding.tilCorreo.setErrorEnabled(false);
            return true;
        }
        // no cumple el formato adecuado
        else {
            // Indicamos el error
            binding.tilCorreo.setError("El formato del correo no es el adecuado");
            // Quitamos el icono de error
            binding.tilCorreo.setErrorIconDrawable(null);
            return false;
        }
    }

    public boolean validarContrasena(String texto){
        /*
            Si el texto es menor a 8 caracteres, el nombre es invalido
        */
        if (texto.trim().length() < 1) {
            binding.tilContraseA.setError("Es requerida la contraseña");
            // Quitamos el icono de error
            binding.tilContraseA.setErrorIconDrawable(null);
            return false;
        }
        else {
            binding.tilContraseA.setErrorEnabled(false);
            return true;
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