package com.example.proyecto_final.pub.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyecto_final.Catalogo2Activity;
import com.example.proyecto_final.R;
import com.example.proyecto_final.databinding.FragmentSesionBinding;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SesionFragment extends Fragment {

    private FragmentSesionBinding binding;
    private NavController navController;

    private ProgressDialog progress;
    private final String URL_SERV = "https://sofcodeqro.com.mx/GameStore/back/usuarios/acceso";

    /*
    Peticiones romotas con Volley
     */
    private RequestQueue conServidor;
    private StringRequest petServidor;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSesionBinding.inflate(inflater, container, false);

        navController = Navigation.findNavController(
                getActivity(),
                R.id.host_fragments
        );

        /*
        Inicializamos los componentes
         */
        conServidor = Volley.newRequestQueue(getActivity());

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
                    getActivity()
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
                            if (objrespuesta.getBoolean("resultado")) {

                                final JSONObject objUsuario = objrespuesta.getJSONObject("usuario");
                                final String token = objrespuesta.getString("token");

                                /*
                                Toast.makeText(
                                        getActivity(),
                                        "Bienvenido a Game Store",
                                        Toast.LENGTH_LONG
                                ).show();*/
                                Snackbar.make(
                                        view,
                                        objrespuesta.getString("mensaje"),
                                        Snackbar.LENGTH_LONG
                                ).setBackgroundTint(getResources().getColor(R.color.success)).show();

                                SharedPreferences sPrefs = getActivity().getSharedPreferences(
                                        "datos",
                                        Context.MODE_PRIVATE
                                );

                                //Guardamos en las preferencias el token del usuario
                                //tambien guardamos el id encriptado del usuario
                                SharedPreferences.Editor sPrefsEditor = sPrefs.edit();
                                sPrefsEditor.putString("uat", token);
                                sPrefsEditor.putString(
                                        "uai",
                                        Utils.md5(objUsuario.getString("idusu"))
                                );

                                sPrefsEditor.putString(
                                        "idusu",
                                        objUsuario.getString("idusu")
                                );
                                sPrefsEditor.commit();

                                navController.navigateUp();
                                navController.navigate(R.id.CatalogoFragment);

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
                                    getActivity(),
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
                                getActivity(),
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
                    parametros.put("contrasenia", Utils.md5(contra));
                    return parametros;
                }
            };

            /*
            Consumir el servicio
             */
            conServidor.add(petServidor);
        });

        binding.btnRegistro.setOnClickListener(view -> {
            navController.navigateUp();
            navController.navigate(R.id.RegistroFragment);
        });

        return binding.getRoot();
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
}