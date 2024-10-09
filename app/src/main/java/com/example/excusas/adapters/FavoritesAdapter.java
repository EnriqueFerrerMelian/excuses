package com.example.excusas.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.excusas.R;
import com.example.excusas.database.ExcusesDao;
import com.example.excusas.database.Favorite;

import java.util.List;
import java.util.concurrent.Executors;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder>{
    private List<Favorite> favoriteList;
    private ExcusesDao excusesDao;

    public FavoritesAdapter(List<Favorite> favoriteList, ExcusesDao excusesDao) {
        this.favoriteList = favoriteList;
        this.excusesDao = excusesDao;
    }

    public static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        TextView favoriteTextView;
        ImageButton deleteButton;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            favoriteTextView = itemView.findViewById(R.id.text_favorite);
            deleteButton = itemView.findViewById(R.id.delete_button);
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
        holder.favoriteTextView.setText(" " + favorite.getIntro() + " " + favorite.getCore() + " " + favorite.getOutcome());
        holder.deleteButton.setOnClickListener(v -> {
            Executors.newSingleThreadExecutor().execute(() -> {
                excusesDao.deleteFavorite(favorite);

                ((Activity) holder.itemView.getContext()).runOnUiThread(() -> {
                    favoriteList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, favoriteList.size());
                });
            });
        });
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }
}
