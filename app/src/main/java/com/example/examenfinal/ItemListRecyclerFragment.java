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

import com.example.examenfinal.databinding.FragmentItemListRecyclerBinding;
import com.example.examenfinal.databinding.ViewholderItemListBinding;
import com.example.examenfinal.models.ItemListDetail;

import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p>
 * This fragment allows the presentation of item details when an item is clicked.
 */
public class ItemListRecyclerFragment extends Fragment {
    private FragmentItemListRecyclerBinding binding;
    private ItemsViewModel itemsViewModel;
    private NavController navController;

    /**
     * Inflates the layout for this fragment
     *
     * @param inflater           The LayoutInflater object that can be used to inflate any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return (binding = FragmentItemListRecyclerBinding.inflate(inflater, container, false)).getRoot();
    }

    /**
     * Called immediately after onCreateView(LayoutInflater, ViewGroup, Bundle) has returned, but before any saved state has been restored in to the view.
     *
     * @param view               The View returned by onCreateView(LayoutInflater, ViewGroup, Bundle).
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        itemsViewModel = new ViewModelProvider(requireActivity()).get(ItemsViewModel.class);
        navController = Navigation.findNavController(view);
        ItemAdapter itemAdapter = new ItemAdapter();
        binding.itemRecyclerView.setAdapter(itemAdapter);

        // When the viewmodel changes, the list is updated with setList(List<ItemListDetails> itemList)
        itemsViewModel.getAll().observe(getViewLifecycleOwner(), itemAdapter::setList);
    }

    /**
     * The adapter for the RecyclerView in this fragment.
     * <p>
     * This adapter populates the views in the RecyclerView and updates them as necessary.
     */
    class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        List<ItemListDetail> itemsList;

        /**
         * Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.
         *
         * @param parent   The ViewGroup into which the new View will be added after it is bound to an adapter position.
         * @param viewType The view type of the new View.
         * @return A new ViewHolder that holds a View of the given view type.
         */
        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ItemViewHolder(ViewholderItemListBinding.inflate(getLayoutInflater(), parent, false));
        }

        /**
         * Called by RecyclerView to display the data at the specified position.
         *
         * @param holder   The ViewHolder which should be updated to represent the contents of the item at the given position in the data set.
         * @param position The position of the item within the adapter's data set.
         */
        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            ItemListDetail element = itemsList.get(position);
            holder.binding.setItem(element);
            holder.itemView.setOnClickListener(v -> {
                itemsViewModel.select(element);
                navController.navigate(R.id.action_itemListRecyclerFragment_to_itemsDetailsFragment);
            });
        }

        /**
         * Returns the total number of items in the data set held by the adapter.
         *
         * @return The total number of items in this adapter.
         */
        @Override
        public int getItemCount() {
            return itemsList != null ? itemsList.size() : 0;
        }

        /**
         * Update the list of items and notify any registered observers that the data set has changed.
         *
         * @param itemList The new list of items.
         */
        public void setList(List<ItemListDetail> itemList){
            this.itemsList = itemList;
            notifyDataSetChanged();
        }
    }

    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     * <p>
     * Each ViewHolder holds a View for one item in the RecyclerView, and defines the interface for that View.
     */
    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderItemListBinding binding;

        /**
         * Constructor for the ViewHolder.
         *
         * @param binding The binding for the item view.
         */
        public ItemViewHolder(ViewholderItemListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}