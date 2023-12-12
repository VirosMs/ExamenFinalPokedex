package com.example.examenfinal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.examenfinal.models.Move;
import com.example.examenfinal.models.MoveListItem;
import com.example.examenfinal.pokeapi.PokeAPI;

import java.util.List;

/**
 * ViewModel que maneja los datos de los movimientos.
 * <p>
 * Este ViewModel permite la presentación de detalles del movimiento seleccionado y la lista de todos los movimientos.
 */
public class MovesViewModel extends AndroidViewModel {
    MutableLiveData<Move> selectedMoveMutableLiveData = new MutableLiveData<>();
    MutableLiveData<List<MoveListItem>> listElementosMutableLiveData = new MutableLiveData<>();
    MoveListItem selected;

    /**
     * Constructor para el ViewModel.
     *
     * @param application La aplicación que posee este ViewModel.
     */
    public MovesViewModel(@NonNull Application application) {
        super(application);
        PokeAPI.getMoveList(listElementosMutableLiveData);
    }

    /**
     * Obtiene todos los movimientos.
     *
     * @return MutableLiveData que contiene la lista de todos los movimientos.
     */
    MutableLiveData<List<MoveListItem>> getAll(){
        return listElementosMutableLiveData;
    }

    /**
     * Selecciona un movimiento.
     *
     * @param moveListItem El movimiento a seleccionar.
     */
    public void select(MoveListItem moveListItem) {
        selected = moveListItem;
    }

    /**
     * Obtiene el movimiento seleccionado.
     *
     * @return MutableLiveData que contiene el movimiento seleccionado.
     */
    public MutableLiveData<Move> getSelected() {
        PokeAPI.getMove(selected.getName(), selectedMoveMutableLiveData);
        return selectedMoveMutableLiveData;
    }
}
