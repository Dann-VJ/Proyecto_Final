package com.example.proyecto_final.pub;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyecto_final.Helper;
import com.example.proyecto_final.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

public class FavoritoAdapter extends BaseAdapter {
    public JSONArray datos;
    private LayoutInflater inflater;  // -> Componente para ligar el xml acada elemento de datos

    public FavoritoAdapter(Context contexto, JSONArray datos) {
        this.datos = datos;
        inflater = LayoutInflater.from(contexto);
    }

    @Override
    public int getCount() {
        return datos.length();  // -> datos
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
            view = inflater.inflate(R.layout.item_fav, null);
        }

        /*
        Indicamos los valores que deben mostrarse en cada elemento
         */
        try {
            /*
            Creamos un obj de tipo prod por cada elemento de la lista
             */
            JSONObject objProducto = datos.getJSONObject(i);

            /*
            View accede al xml y pude tomar valores por medio de id de
            los componentes graficos
             */
            TextView tvid = view.findViewById(R.id.tv_id);
            tvid.setText(objProducto.getString("idproducto"));

            ImageView ivImagen = view.findViewById(R.id.iv_img);
            Picasso.get()
                    .load(
                            Helper.baseUrl() +
                                    "back/static/upload/img/" +
                                    objProducto.getString("idproducto") + ".png"
                    ).placeholder(R.drawable.file_clock)
                    .into(ivImagen);

            TextView tvprod = view.findViewById(R.id.tv_prod);
            tvprod.setText(objProducto.getString("nomproducto"));   // -> Propidedad del obj json

        }
        catch(Exception e) {
            Log.e("Error prod", e.getMessage());
        }

        return view;
    }

}
