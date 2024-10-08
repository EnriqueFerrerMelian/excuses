package com.example.excusas.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

public abstract class AppDatabase extends RoomDatabase {
    public abstract ExcusesDao favoriteDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "excusa_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
