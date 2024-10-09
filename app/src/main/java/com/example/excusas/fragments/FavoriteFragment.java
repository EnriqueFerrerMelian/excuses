package com.example.excusas.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.excusas.R;
import com.example.excusas.adapters.FavoritesAdapter;
import com.example.excusas.database.DatabaseExcuses;
import com.example.excusas.database.Favorite;
import com.example.excusas.databinding.FragmentExcusesBinding;
import com.example.excusas.databinding.FragmentFavoriteBinding;

import java.util.List;

public class FavoriteFragment extends Fragment {
    FragmentFavoriteBinding binding;
    private DatabaseExcuses db;
    private RecyclerView recyclerView;
    private FavoritesAdapter favoritesAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadFavorites();

        return binding.getRoot();
    }

    private void loadFavorites() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                DatabaseExcuses db = DatabaseExcuses.getDatabase(getContext());
                List<Favorite> favorites = db.excusesDao().getAllFavorites();
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        favoritesAdapter = new FavoritesAdapter(favorites, db.excusesDao());
                        recyclerView.setAdapter(favoritesAdapter);
                    }
                });
            }
        }).start();
    }
}