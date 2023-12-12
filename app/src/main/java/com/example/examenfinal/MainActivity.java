package com.example.examenfinal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.examenfinal.databinding.FragmentDrawerBinding;
import com.example.examenfinal.models.MoveListItem;
import com.example.examenfinal.pokeapi.PokeAPI;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FragmentDrawerBinding binding;

    /**
     * Se llama cuando se está iniciando la actividad. Aquí es donde se debe realizar la mayoría de las inicializaciones: llamar a setContentView(int) para inflar la interfaz de usuario de la actividad, usar findViewById(int) para interactuar con los widgets de la interfaz de usuario.
     *
     * @param savedInstanceState Si la actividad se está reiniciando después de una parada anterior, este contiene los datos que más recientemente se suministraron en onSaveInstanceState(Bundle). De lo contrario, es nulo.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MutableLiveData<List<MoveListItem>> moveList = new MutableLiveData<>();
        PokeAPI.getMoveList(moveList);

        setContentView((binding = FragmentDrawerBinding.inflate(getLayoutInflater())).getRoot());
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.moveListRecyclerFragment
        )
                .setOpenableLayout(binding.drawerLayout)
                .build();



        NavController navController = ((NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_drawer_host_fragment)).getNavController();
        NavigationUI.setupWithNavController(binding.navView, navController);
        NavigationUI.setupWithNavController(binding.toolbar, navController, appBarConfiguration);

        binding.toolbar.setTitle("Pokedex Charles");
    }
}

