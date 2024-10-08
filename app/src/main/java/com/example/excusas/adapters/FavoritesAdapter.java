package com.example.excusas.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.excusas.R;
import com.example.excusas.database.Favorite;

import java.util.List;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder>{
    private List<Favorite> favoriteList;

    public FavoritesAdapter(List<Favorite> favoriteList) {
        this.favoriteList = favoriteList;
    }

    public static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        public TextView textFavorite;

        public FavoriteViewHolder(View itemView) {
            super(itemView);
            textFavorite = itemView.findViewById(R.id.text_favorite);
        }
    }

    @NonNull
    @Override
    public FavoritesAdapter.FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorite, parent, false);
        return new FavoriteViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull FavoritesAdapter.FavoriteViewHolder holder, int position) {
        Favorite favorite = favoriteList.get(position);
        holder.textFavorite.setText(" " + favorite.getIntro() + " " + favorite.getCore() + " " + favorite.getOutcome());
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }
}
