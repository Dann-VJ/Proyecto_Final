package com.example.proyecto_final.pub.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyecto_final.R;
import com.example.proyecto_final.databinding.FragmentCarritoBinding;
import com.example.proyecto_final.pub.CarritoAdapter;
import com.example.proyecto_final.pub.ProductosAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

public class CarritoFragment extends Fragment {

    private FragmentCarritoBinding binding;
    private RequestQueue conServ;
    private CarritoAdapter adaptador;
    private final String URL = "https://sofcodeqro.com.mx/GameStore/back/carrito/getcarrito";
    private final String URL2= "https://sofcodeqro.com.mx/GameStore/front/app/carrito";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCarritoBinding.inflate(inflater, container, false);

        // Inicializamos a Volley
        conServ = Volley.newRequestQueue(getActivity());

        /*
        Ponemos en modo carga el swipeRefresh
         */
        binding.srlCarrito.post(() -> {
            cargaCarrito();
        });

         /*
        Al arrastrar y soltar cargmos el frgmento
         */
        binding.srlCarrito.setOnRefreshListener(() -> {
            cargaCarrito();
        });

        return binding.getRoot();
    }

    public void carrito(){


    }

    public void cargaCarrito() {
        binding.srlCarrito.setRefreshing(true);

        /*
        Cargamos una petición al servicio
         */
        final StringRequest petServ = new StringRequest(
                Request.Method.POST,
                URL,
                response -> {
                /*
                Intentamos crear un objeto JSON con la respuesta del servidor
                 */
                    try {
                        JSONObject objRespuesta = new JSONObject(response);

                        // Si resultado es verdadero
                        if(objRespuesta.getBoolean("resultado")) {

                            /*
                            Tomamos el arreglo de items
                             */
                            JSONArray arrCarrito = objRespuesta.getJSONArray("items");

                            /*
                            Creamos un adaptador (CarritoAdapter) y le pasamos
                            el arreglo de productos
                             */
                            adaptador = new CarritoAdapter(
                                    getActivity(),
                                    arrCarrito
                            );

                        /*
                        Asignamos el adaptador al listview
                         */
                            binding.lvCarrito.setAdapter(adaptador);
                        }
                    }
                    catch(Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                    finally {
                        // Quitamos el setRefreshing
                        binding.srlCarrito.setRefreshing(false);
                    }
                },
                error -> {
                    // Quitamos el setRefreshing
                    binding.srlCarrito.setRefreshing(false);
                    Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                }
        );
        // Petición al servicio
        conServ.add(petServ);
    }
}