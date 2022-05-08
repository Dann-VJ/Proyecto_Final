package com.example.proyecto_final.pub.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyecto_final.R;
import com.example.proyecto_final.databinding.FragmentCatalogoBinding;
import com.example.proyecto_final.pub.ProductosAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CatalogoFragment extends Fragment {

    private FragmentCatalogoBinding binding;
    private RequestQueue conServ;
    private ProductosAdapter adaptador;
    private final String URL = "https://sofcodeqro.com.mx/GameStore/back/carrito/getproductos";
    private final String URL2 = "https://sofcodeqro.com.mx/GameStore/back/carrito/agregadeseo";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCatalogoBinding.inflate(inflater, container, false);

        conServ = Volley.newRequestQueue(getActivity());
        /*
        Ponemos en modo carga el swipeRefresh y cargamos
        los productos justo al inicio
         */
        binding.slrProd.post(() -> {
            cargaProductos();
        });

        /*
        Al arrastrar y soltar cargmos el frgmento
         */
        binding.slrProd.setOnRefreshListener(() -> {
            cargaProductos();
        });

        //Click de cualquier elemento del ListView de productos
        binding.lvProd.setOnItemClickListener((adapterView, view, i, l) -> {
            //al darle click a un producto, tomamos su idProducto
            TextView tvid = view.findViewById(R.id.tv_id);

            //pasamos como parámetro el string del id de producto
            agregarListaDeseos(
                    tvid.getText().toString()
            );
        });

        return binding.getRoot();
    }

    public void cargaProductos() {
        binding.slrProd.setRefreshing(true);

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
                        Creamos un adaptador (ProductosAdapter) y le pasamos
                        el arreglo de productos
                         */
                        adaptador = new ProductosAdapter(
                            getActivity(),
                                objRespuesta.getJSONArray("productos")
                        );

                        /*
                        Asignamos el adaptador al listview
                         */
                        binding.lvProd.setAdapter(adaptador);
                    }
                }
                catch(Exception e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
                finally {
                    // Quitamos el setRefreshing
                    binding.slrProd.setRefreshing(false);
                }
            },
            error -> {
                // Quitamos el setRefreshing
                binding.slrProd.setRefreshing(false);
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
            })
            {

        };
        // Petición al servicio
        conServ.add(petServ);
    }

    /*
    Creamos un metodo para agregar a la lista de deseos el producto seleccionado
     */
    public void agregarListaDeseos(String idProducto) {
        //Buscamos los valores guardados de token y id
        final SharedPreferences sPrefs = getActivity().getSharedPreferences(
                "datos",
                Context.MODE_PRIVATE
        );

        /*
        Tomamos el token y el idusuario de las preferencias,
        si no lo encontramos guardamos un nulo
         */
        final String token = sPrefs.getString("uat", null);
        final String idusu = sPrefs.getString("idusu", null);

        /*
        Si el token o el idusuario es nulo significa que no ha iniciado
        sesión
         */
        if (token == null || idusu == null) {
            final AlertDialog.Builder alerta = new AlertDialog.Builder(
                    getActivity()
            );

            /*
            Mostramos una alerta
             */
            alerta.setTitle("ERROR");
            alerta.setMessage("Debes inicia sesión para agregar a la lista de deseos");
            alerta.setPositiveButton(
                    "Iniciar sesión",
                    (dialogInterface,i) -> {
                        final NavController navController = Navigation.findNavController(
                                getActivity(),
                                R.id.host_fragments
                        );
                        navController.navigateUp();
                        navController.navigate(R.id.SesionFragment);
                    }
            );
            alerta.setNegativeButton("Cancelar", null);
            alerta.setCancelable(false);
            alerta.show();

            //Terminamos la ejecucion del método
            return;
        }

        /*
        Insertamos el producto en la lista de deseos, para ello
        necesitamos:
        idproducto
        idusuario
        token
         */
        final StringRequest petAgregaListaDeseos = new StringRequest(
                Request.Method.POST,
                URL2,
                response -> {
                    //intentamos convertir la respuesta del servicio
                    //a un objeto json
                    try {
                        JSONObject objRespuesta = new JSONObject(response);

                        //Mostramos el mensaje del servicio
                        Toast.makeText(getActivity(), objRespuesta.getString("mensaje"), Toast.LENGTH_SHORT).show();
                    }

                    catch(Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                }
        ) {
            @Nullable
            @Override
            /*
            Mandamos los parámetros POST que necesita el
            servicio
             */
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("idusu", idusu);
                parametros.put("idproducto", idProducto);
                parametros.put("token", token);

                return parametros;
            }
        };
        //Ejecutamos el servicio
        conServ.add(petAgregaListaDeseos);
    }
}