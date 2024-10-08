package com.example.excusas.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorites")
public class Favorite {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "intro_text")
    private String intro;
    @ColumnInfo(name = "core_text")
    private String core;
    @ColumnInfo(name = "outcome_text")
    private String outcome;

    public Favorite(String intro, String core, String outcome) {
        this.intro = intro;
        this.core = core;
        this.outcome = outcome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getCore() {
        return core;
    }

    public void setCore(String core) {
        this.core = core;
    }

    public String getOutcome() {
        return outcome;
    }

    public void setOutcome(String outcome) {
        this.outcome = outcome;
    }

    @Override
    public String toString() {
        return intro + " " + core + " " + outcome;
    }
}
