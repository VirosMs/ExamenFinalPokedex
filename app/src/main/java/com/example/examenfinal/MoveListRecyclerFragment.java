package com.example.examenfinal;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.examenfinal.databinding.FragmentMoveListRecyclerBinding;
import com.example.examenfinal.databinding.ViewholderMoveListBinding;

import com.example.examenfinal.models.MoveListItem;

import java.util.List;

/**
 * Un fragmento que representa una lista de movimientos.
 * <p>
 * Este fragmento permite la presentación de detalles del movimiento cuando se hace clic en un movimiento.
 */
public class MoveListRecyclerFragment extends Fragment {
    private FragmentMoveListRecyclerBinding binding;
    private MovesViewModel movesViewModel;
    private NavController navController;

    /**
     * Infla el layout para este fragmento
     *
     * @param inflater           El objeto LayoutInflater que se puede usar para inflar cualquier vista en el fragmento,
     * @param container          Si no es nulo, esta es la vista padre a la que se debe adjuntar la UI del fragmento.
     * @param savedInstanceState Si no es nulo, este fragmento se está reconstruyendo a partir de un estado guardado previo.
     * @return Devuelve la Vista para la UI del fragmento, o nulo.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentMoveListRecyclerBinding.inflate(inflater, container, false)).getRoot();
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
        movesViewModel = new ViewModelProvider(requireActivity()).get(MovesViewModel.class);
        navController = Navigation.findNavController(view);
        MovesAdapter movesAdapter = new MovesAdapter();
        binding.recyclerView.setAdapter(movesAdapter);

        // Cuando cambia el viewmodel se actualiza la lista con setList(List<MoveListItem> moveList)
        movesViewModel.getAll().observe(getViewLifecycleOwner(), movesAdapter::setList);
    }

    /**
     * El adaptador para el RecyclerView en este fragmento.
     * <p>
     * Este adaptador llena las vistas en el RecyclerView y las actualiza según sea necesario.
     */
    class MovesAdapter extends RecyclerView.Adapter<MoveViewHolder> {
        List<MoveListItem> moveList;

        /**
         * Se llama cuando RecyclerView necesita un nuevo RecyclerView.ViewHolder del tipo dado para representar un ítem.
         *
         * @param parent   El ViewGroup en el que se agregará la nueva Vista después de que se vincule a una posición del adaptador.
         * @param viewType El tipo de vista de la nueva Vista.
         * @return Un nuevo ViewHolder que contiene una Vista del tipo de vista dado.
         */
        @NonNull
        @Override
        public MoveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new MoveViewHolder(ViewholderMoveListBinding.inflate(getLayoutInflater(), parent, false));
        }

        /**
         * Se llama por RecyclerView para mostrar los datos en la posición especificada.
         *
         * @param holder   El ViewHolder que debe actualizarse para representar el contenido del ítem en la posición dada en el conjunto de datos.
         * @param position La posición del ítem dentro del conjunto de datos del adaptador.
         */
        @Override
        public void onBindViewHolder(@NonNull MoveViewHolder holder, int position) {
            MoveListItem element = moveList.get(position);
            holder.binding.setMove(element);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    movesViewModel.select(element);
                    navController.navigate(R.id.action_moveListRecyclerFragment_to_moveDetailFragment);
                }
            });
        }

        /**
         * Devuelve el número total de ítems en el conjunto de datos que tiene el adaptador.
         *
         * @return El número total de ítems en este adaptador.
         */
        @Override
        public int getItemCount() {
            return moveList != null ? moveList.size() : 0;
        }

        /**
         * Actualiza la lista de movimientos y notifica a cualquier observador registrado que el conjunto de datos ha cambiado.
         *
         * @param moveList La nueva lista de movimientos.
         */
        public void setList(List<MoveListItem> moveList){
            this.moveList = moveList;
            notifyDataSetChanged();
        }
    }

    /**
     * Un ViewHolder describe una vista de ítem y metadatos sobre su lugar dentro del RecyclerView.
     * <p>
     * Cada ViewHolder contiene una Vista para un ítem en el RecyclerView, y define la interfaz para esa Vista.
     */
    static class MoveViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderMoveListBinding binding;

        /**
         * Constructor para el ViewHolder.
         *
         * @param binding El binding para la vista del ítem.
         */
        public MoveViewHolder(ViewholderMoveListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}