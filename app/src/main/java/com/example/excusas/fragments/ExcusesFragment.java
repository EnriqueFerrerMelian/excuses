package com.example.excusas.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.excusas.MainActivity;
import com.example.excusas.R;
import com.example.excusas.database.DatabaseExcuses;
import com.example.excusas.database.Excuse;
import com.example.excusas.database.Favorite;
import com.example.excusas.databinding.FragmentExcusesBinding;

import java.util.Objects;

public class ExcusesFragment extends Fragment {
    FragmentExcusesBinding binding;
    private DatabaseExcuses db;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentExcusesBinding.inflate(inflater, container, false);
        db = ((MainActivity) requireActivity()).getDatabase();
        binding.generateExcuse.setOnClickListener(v -> {
            new Thread(() -> {
                String intro = db.excusesDao().getRandomIntro();
                String nudo = db.excusesDao().getRandomCore();
                String desenlace = db.excusesDao().getRandomOutcome();
                Excuse excuse = new Excuse(intro, nudo, desenlace);

                requireActivity().runOnUiThread(() -> {
                    binding.receptor.setText(excuse.toString());

                    // Botón para añadir a favoritos
                    binding.addFavoritesButton.setOnClickListener(fav -> {
                        new Thread(() -> {
                            db.excusesDao().insertFavorite(new Favorite(intro, nudo, desenlace));
                        }).start();
                    });
                });
            }).start();
        });


        return binding.getRoot();
    }
}