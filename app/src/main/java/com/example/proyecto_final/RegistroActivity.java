package com.example.proyecto_final;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import androidx.appcompat.app.AlertDialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyecto_final.databinding.ActivityRegistroBinding;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RegistroActivity extends AppCompatActivity {

    /*
        Podemos realizar una vinculación automática de los elementos de la vista
        con los componentes del controlador
        utilizando la librería ViewBindign

        Para ello debemos activarla en la configuración del proyecto
        ViewBinding se activa desde el archivo app.gradle

        Binding genera una clase intermediaria entre el Controlador y la vista
        con el nombre:
        Activity/Fragment{NOMBRE CLASE}Binding
        Ejem:
        FragmentInicioBinding
        ActivityValidacionesBinding
    */

    private ActivityRegistroBinding binding;
    private ActionBar actionBar;

    private ProgressDialog progress;
    private final String URL_SERV = "https://sofcodeqro.com.mx/GameStore/back/usuarios/registrausuario";

    /*
    Peticiones romotas con Volley
     */
    private RequestQueue conServidor;
    private StringRequest petServidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*
            Indicanos que vamos a mostcar la vista que binding
            interprete a partir del contexto
        */
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        actionBar = getSupportActionBar();


        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        /*
        Inicializamos los componentes
         */
        progress = new ProgressDialog(RegistroActivity.this);
        conServidor = Volley.newRequestQueue(RegistroActivity.this);

        // Validamos lo que se va escribiendo en el campo de nombre
        binding.tietNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                validarNombre(editable.toString());
            }
        });

        // Validamos lo que se va escribiendo en el campo de apellidos
        binding.tietApellidos.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                validarApellidos(editable.toString());
            }
        });

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

        /*
        // Validamos lo que se escribe en el campo dirección
        binding.tietDireccion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                validarDomicilio(editable.toString());
            }
        });

        // Validamos lo que se escribe en el campo telefono
        binding.tietTelefono.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                validarTelefono(editable.toString());
            }
        });*/

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

        /*
        // Validamos lo que se va escribiendo en el campo de contraseña 2
        binding.tietContraseA2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                validarContrasena2(editable.toString());
            }
        });*/

        // Click en el boton registrarse
        binding.btnRegistro.setOnClickListener(view -> {
            /*
            Validamos el nombre
            */
            if ( !validarNombre(binding.tietNombre.getText().toString()) ) {
                return;
            }

            /*
            Validamos los apellidos
            */
            if ( !validarApellidos(binding.tietApellidos.getText().toString()) ) {
                return;
            }

            /*
            Validamos el correo
            */
            if ( !validarCorreo(binding.tietCorreo.getText().toString()) ) {
                return;
            }

            /*
            Validamos la dirreción

            if ( !validarDomicilio(binding.tietDireccion.getText().toString()) ) {
                return;
            }


            Validamos el teléfono

            if ( !validarTelefono(binding.tietTelefono.getText().toString()) ) {
                return;
            }
            */

            /*
            Validamos la contraseña
            */
            if ( !validarContrasena(binding.tietContraseA.getText().toString()) ) {
                return;
            }

            /*
            Validamos la contraseña 2

            if ( !validarContrasena2(binding.tietContraseA2.getText().toString()) ) {
                return;
            }
             */

            progress.setTitle("Registrandote");
            progress.setMessage("Por favor espera...");
            progress.setIndeterminate(true);
            progress.setCancelable(false);
            progress.show();

            /*
            Ya validado los valores, tomar los valores que el servicio necesita
             */
            final String nombre = binding.tietNombre.getText().toString();
            final String apellidos = binding.tietApellidos.getText().toString();
            final String correo = binding.tietCorreo.getText().toString();
            final String contra = binding.tietContraseA.getText().toString();

            /*
            Preparamos la petición al servicio:
             1. Método (POST/GET)
             2. URL
             3. ResponseListener
             4. ErrorListener
            *5. (Solo para POST
                Antes de ejecutar el servicio, debemos indicar las variables
                tipo POST que vamos a enviar por medio de la petición
                sobreescribiendo el método getParams y creando un mapa
                de datos
             */
            petServidor = new StringRequest(Request.Method.POST, URL_SERV, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progress.hide();

                    /*
                    Intentamos convertir la respuesta
                    en un objeto json
                     */
                    try {
                        JSONObject objrespuesta = new JSONObject(response);

                        // Tomamos el valor de resultado
                        boolean resultado = objrespuesta.getBoolean("resultado");

                        /*
                        Si resultado es true, el usuario pudo registrarse
                         */
                        String mensaje = objrespuesta.getString("mensaje");
                        if (resultado) {
                            Snackbar
                                    .make(view, mensaje, Snackbar.LENGTH_INDEFINITE)
                                    .setAction("Iniciar Sesión", view -> {
                                        // Redireccionamos al login
                                        startActivity(
                                            new Intent(
                                                RegistroActivity.this,
                                                SesionActivity.class
                                            )
                                        );
                                    })
                                    .show();
                        } else {
                            Snackbar.make(view, mensaje, Snackbar.LENGTH_LONG).show();
                        }
                    }

                    catch (Exception e) {

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progress.hide();
                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    // Creamos un mapa con claves tipo String
                    // Y valores tipo String
                    Map<String, String> parametros = new HashMap<>();

                    /*
                    Por medio del método put podemos agregar una clave
                    y valor
                     */
                    parametros.put("nombre", nombre);
                    parametros.put("apellidos", apellidos);
                    parametros.put("correo", correo);
                    parametros.put("contra", contra);
                    return parametros;
                } // get Params
            };

            /*
            Consumir el servicio
             */
            conServidor.add(petServidor);

        }); // btn registro

        // Click en el boton Inicio de sesión
        binding.btnInicios.setOnClickListener(view -> {
            startActivity(
                new Intent(
                        RegistroActivity.this,
                        SesionActivity.class
                )
            );
        });

    }

    public boolean validarNombre(String texto) {
        /*
            Si el texto es menor a 8 caracteres, el nombre es invalido
        */
        if (texto.trim().length() < 8) {
            binding.tilNombre.setError("El nombre es muy corto, agrega un nombre mayor a 8 letras");
            // Quitamos el icono de error
            binding.tilNombre.setErrorIconDrawable(null);
            return false;
        }
        else {
            binding.tilNombre.setErrorEnabled(false);
            return true;
        }
    }

    public boolean validarApellidos(String texto) {
        /*
            Si el texto es menor a 8 caracteres, el nombre es invalido
        */
        if (texto.trim().length() < 8) {
            binding.tilApellidos.setError("El Apellido es muy corto, agrega un Apellido mayor a 8 letras");
            // Quitamos el icono de error
            binding.tilApellidos.setErrorIconDrawable(null);
            return false;
        }
        else {
            binding.tilApellidos.setErrorEnabled(false);
            return true;
        }
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

    /*
    public boolean validarDomicilio(String texto) {

            Si el texto es menor a 8 caracteres, el nombre es invalido

        if (texto.trim().length() < 8) {
            binding.tilDireccion.setError("La dirección es muy corta, agrega una dirreción mayor a 8 letras");
            // Quitamos el icono de error
            binding.tilDireccion.setErrorIconDrawable(null);
            return false;
        }
        else {
            binding.tilDireccion.setErrorEnabled(false);
            return true;
        }
    }

    public boolean validarTelefono(String texto) {
         Validamos con Patterns
        if (Patterns.PHONE.matcher(texto).matches() && texto.length() == 10 && !texto.contains(" ") && !texto.contains(".") && !texto.contains("-")) {
            // Mi vallidación no permitira los siguientes caracteres: '-', ' ', '.'
            binding.tilTelefono.setErrorEnabled(false);
            return true;
        }
        else {
            binding.tilTelefono.setError("Ingresa un número teléfonico válido a 10 dígitos");
            return false;
        }
    }

    public boolean validarContrasena2(String texto){

            Si el texto es menor a 8 caracteres, el nombre es invalido

        if (texto.trim().length() < 8) {
            binding.tilContraseA2.setError("La contraseña es muy corta, agrega una contraseña mayor a 8 caracteres");
            // Quitamos el icono de error
            binding.tilContraseA2.setErrorIconDrawable(null);
            return false;
        }
        else {
            binding.tilContraseA2.setErrorEnabled(false);
            return true;
        }
    }
    */

    public boolean validarContrasena(String texto){
        /*
            Si el texto es menor a 8 caracteres, el nombre es invalido
        */
        if (texto.trim().length() < 8) {
            binding.tilContraseA.setError("La contraseña es muy corta, agrega una contraseña mayor a 8 caracteres");
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