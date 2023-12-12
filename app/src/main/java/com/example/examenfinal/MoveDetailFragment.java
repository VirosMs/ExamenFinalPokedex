package com.example.examenfinal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.examenfinal.databinding.FragmentMoveDetailBinding;
import com.example.examenfinal.models.Move;
import com.example.examenfinal.models.PokemonListItem;


/**
 * Un fragmento que representa los detalles de un movimiento.
 * <p>
 * Este fragmento permite la presentación de detalles del movimiento seleccionado.
 */
public class MoveDetailFragment extends Fragment {

    private FragmentMoveDetailBinding binding;
    MovesViewModel movesViewModel;

    /**
     * Se llama al crear el fragmento.
     *
     * @param savedInstanceState Si no es nulo, este fragmento se está reconstruyendo a partir de un estado guardado previo.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movesViewModel = new ViewModelProvider(requireActivity()).get(MovesViewModel.class);
    }

    /**
     * Se llama inmediatamente después de onCreateView(LayoutInflater, ViewGroup, Bundle) ha devuelto, pero antes de que se haya restaurado cualquier estado guardado en la vista.
     *
     * @param view               La Vista devuelta por onCreateView(LayoutInflater, ViewGroup, Bundle).
     * @param savedInstanceState Si no es nulo, este fragmento se está reconstruyendo a partir de un estado guardado previo.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        movesViewModel.getSelected().observe(getViewLifecycleOwner(), new Observer<Move>() {
            @Override
            public void onChanged(Move m) {
                binding.setMove(m);
            }
        });
    }

    /**
     * Infla el layout para este fragmento
     *
     * @param inflater           El objeto LayoutInflater que se puede usar para inflar cualquier vista en el fragmento,
     * @param container          Si no es nulo, esta es la vista padre a la que se debe adjuntar la UI del fragmento.
     * @param savedInstanceState Si no es nulo, este fragmento se está reconstruyendo a partir de un estado guardado previo.
     * @return Devuelve la Vista para la UI del fragmento, o nulo.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return (binding = FragmentMoveDetailBinding.inflate(inflater, container, false)).getRoot();
    }
}