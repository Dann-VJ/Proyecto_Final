package com.example.proyecto_final.pub;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.proyecto_final.R;
import com.example.proyecto_final.RegistroActivity;
import com.example.proyecto_final.SesionActivity;
import com.example.proyecto_final.databinding.ActivityPublicBinding;
import com.example.proyecto_final.pub.fragments.CarritoFragment;

public class PublicActivity extends AppCompatActivity {

    private ActivityPublicBinding binding;

    // Appbar
    private AppBarConfiguration appBarConfiguration;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPublicBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /*
        Controlador del fragmento
         */
        navController = Navigation.findNavController(
                PublicActivity.this,
                R.id.host_fragments
        );

        /*
        Controlamos el actionbar
         */
        appBarConfiguration = new AppBarConfiguration.Builder(
                navController.getGraph() //Me regresa todos los elementos de navegación
        ).build();

        /*
        Indicamos quien controla el actionbar
         */
        NavigationUI.setupActionBarWithNavController(
                PublicActivity.this,
                navController,
                appBarConfiguration
        );

        /*
        Ckick en floatingactionbutton(fab) del carrito
        Función landa
         */
        binding.fabCarrito.setOnClickListener(view -> {
            // Me muestra el fragment del carrito
            navController.navigateUp();
            navController.navigate(R.id.CarritoFragment);
        });

    } // oncreate

    /*
    El cambio del actionbar
     */

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }

    /*
    Creamos un menú
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_public, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /*
    Click de cada elemento del menú
     */

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        /*
        Dependiendo del ID del menú, indicamos que fragmento se muestra
         */
        switch(item.getItemId()) {
            case R.id.catalogo:
                navController.navigateUp();
                navController.navigate(R.id.CatalogoFragment);
                break;

            case R.id.favorito:
                navController.navigateUp();
                navController.navigate(R.id.FavoritoFragment);
                break;

            case R.id.sesion:
                Bundle datos = new Bundle();

                navController.navigateUp();
                navController.navigate(R.id.SesionFragment);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}