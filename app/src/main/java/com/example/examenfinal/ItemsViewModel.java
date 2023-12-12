package com.example.examenfinal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.examenfinal.models.Item;
import com.example.examenfinal.models.ItemListDetail;
import com.example.examenfinal.pokeapi.PokeAPI;

import java.util.List;

/**
 * ViewModel que maneja los datos de los ítems.
 * <p>
 * Este ViewModel permite la presentación de detalles del ítem seleccionado y la lista de todos los ítems.
 */
public class ItemsViewModel extends AndroidViewModel {
    MutableLiveData<Item> selectedItemMutableLiveData = new MutableLiveData<>();
    MutableLiveData<List<ItemListDetail>> listElementosMutableLiveData = new MutableLiveData<>();
    ItemListDetail selected;

    /**
     * Constructor para el ViewModel.
     *
     * @param application La aplicación que posee este ViewModel.
     */
    public ItemsViewModel(@NonNull Application application) {
        super(application);
        PokeAPI.getItemList(listElementosMutableLiveData);
    }

    /**
     * Obtiene todos los ítems.
     *
     * @return MutableLiveData que contiene la lista de todos los ítems.
     */
    MutableLiveData<List<ItemListDetail>> getAll(){
        return listElementosMutableLiveData;
    }

    /**
     * Selecciona un ítem.
     *
     * @param itemListDetails El ítem a seleccionar.
     */
    public void select(ItemListDetail itemListDetails) {
        selected = itemListDetails;
    }

    /**
     * Obtiene el ítem seleccionado.
     *
     * @return MutableLiveData que contiene el ítem seleccionado.
     */
    public MutableLiveData<Item> getSelected() {
        PokeAPI.getItem(selected.getName(), selectedItemMutableLiveData);
        return selectedItemMutableLiveData;
    }
}