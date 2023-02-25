package com.example.quakehistory.db.room_tables;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity (
        tableName = "ctry_affected",
        primaryKeys = {"date", "country"},
        foreignKeys = {
                @ForeignKey(
                        entity = EarthQuake.class,
                        parentColumns = "date",
                        childColumns = "date",
                        onDelete = ForeignKey.CASCADE
                )
        }
)
public class CtryAffected {
    @NonNull
    public String date;

    @NonNull
    @ColumnInfo(name = "country")
    public String country;

    public CtryAffected(@NonNull String date, @NonNull String country) {
        this.date = date;
        this.country = country;
    }
}
