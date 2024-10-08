package com.example.excusas.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "core")
public class Core {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int id;

    @ColumnInfo(name = "core_text")
    private String coreText;

    public Core(String coreText) {
        this.coreText = coreText;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoreText() {
        return coreText;
    }

    public void setCoreText(String coreText) {
        this.coreText = coreText;
    }
}
