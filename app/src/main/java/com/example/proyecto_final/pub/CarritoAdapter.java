package com.example.proyecto_final.pub;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.proyecto_final.R;

import org.json.JSONArray;
import org.json.JSONObject;

public class CarritoAdapter extends BaseAdapter {

    private JSONArray carrito; // -> Arreglo de productos de mi servicio
    private LayoutInflater inflater; // -> Componente para ligar el xml acada elemento de datos

    public CarritoAdapter(Context contexto, JSONArray datos) {
        /*
        Inicializamos a los elementos del adapter
         */
        inflater = LayoutInflater.from(contexto);
        this.carrito = datos;
    }

    /*
    Retornamos el número de elementos de la lista
     */
    @Override
    public int getCount() {
        return carrito.length();  // -> datos
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    /*
    Este método se ejecuta por cada elemento del arreglo
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        /*
        Si la vista ya cargó, reutilizamos el mismo layout
        de lo contrario en view montamos nuestro layout
         */
        if (view == null) {
            view = inflater.inflate(R.layout.item_carrito, null);
        }

        /*
        Indicamos los valores que deben mostrarse en cada elemento
         */
        try {
            /*
            Creamos un obj json por cada elemento del arreglo
             */
            JSONObject objProd = carrito.getJSONObject(i);

            /*
            View accede al xml y pude tomar valores por medio de id de
            los componentes graficos
             */
            TextView tvid = view.findViewById(R.id.tv_id);
            tvid.setText(objProd.getString("idproducto"));  // ->   Propidedad del obj json

            TextView tvprod = view.findViewById(R.id.tv_prod);
            tvprod.setText(objProd.getString("nomproducto"));   // -> Propidedad del obj json

            TextView tvtipo = view.findViewById(R.id.tv_des);
            tvtipo.setText(objProd.getString("descripcion"));   // -> Propidedad del obj json

            TextView tvpre = view.findViewById(R.id.tv_precio);
            tvpre.setText(objProd.getString("precio"));   // -> Propidedad del obj json
        }
        catch(Exception e) {
            Log.e("Error carrito", e.getMessage());
        }
        return view;
    }
}
