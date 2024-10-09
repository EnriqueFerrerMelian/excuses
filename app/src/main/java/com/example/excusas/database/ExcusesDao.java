package com.example.excusas.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface ExcusesDao {
    @Query("SELECT intro_text FROM intro ORDER BY RANDOM() LIMIT 1")
    String getRandomIntro();

    @Query("SELECT core_text FROM core ORDER BY RANDOM() LIMIT 1")
    String getRandomCore();

    @Query("SELECT outcome_text FROM outcome ORDER BY RANDOM() LIMIT 1")
    String getRandomOutcome();

    @Query("SELECT * FROM favorites")
    List<Favorite> getAllFavorites();

    @Insert
    void insertFavorite(Favorite favorite);

    @Insert
    void insertIntro(Intro intro);

    @Insert
    void insertCore(Core core);

    @Insert
    void insertOutcome(Outcome outcome);

    @Query("SELECT COUNT(*) FROM intro")
    int countIntro();

    @Query("SELECT COUNT(*) FROM Core")
    int countNudo();

    @Query("SELECT COUNT(*) FROM Outcome")
    int countDesenlace();

    @Insert
    void insertAllIntro(List<Intro> introList);

    @Insert
    void insertAllCore(List<Core> coreList);

    @Insert
    void insertAllOutcome(List<Outcome> outcomeList);

    @Query("DELETE FROM intro")
    void deleteAllIntro();

    @Query("DELETE FROM core")
    void deleteAllCore();

    @Query("DELETE FROM outcome")
    void deleteAllOutcome();

    @Query("DELETE FROM favorites")
    void deleteAllFavorites();

    @Delete
    void deleteFavorite(Favorite favorite);

    @Transaction
    default void clearTables() {
        deleteAllIntro();
        deleteAllCore();
        deleteAllOutcome();
    }
}
