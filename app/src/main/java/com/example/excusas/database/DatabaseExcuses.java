package com.example.excusas.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Intro.class, Core.class, Outcome.class, Favorite.class}, version = 2)
public abstract class DatabaseExcuses extends RoomDatabase {
    private static DatabaseExcuses instance;
    public abstract ExcusesDao excusesDao();

    public static synchronized DatabaseExcuses getDatabase(final Context context) {
        if (instance == null) {
            System.out.println("creando bd");
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            DatabaseExcuses.class, "excusa_db")
                    .build();
        }
        System.out.println("devolviendo bd");
        return instance;
    }
}
