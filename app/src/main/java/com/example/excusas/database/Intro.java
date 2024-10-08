package com.example.excusas.database;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "intro")
public class Intro {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "intro_text")
    private String introText;

    public Intro(String introText) {
        this.introText = introText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntroText() {
        return introText;
    }

    public void setIntroText(String introText) {
        this.introText = introText;
    }
}
