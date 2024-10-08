package com.example.excusas.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "outcome")
public class Outcome {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "outcome_text")
    private String outcomeText;

    public Outcome(String outcomeText) {
        this.outcomeText = outcomeText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOutcomeText() {
        return outcomeText;
    }

    public void setOutcomeText(String outcomeText) {
        this.outcomeText = outcomeText;
    }
}
