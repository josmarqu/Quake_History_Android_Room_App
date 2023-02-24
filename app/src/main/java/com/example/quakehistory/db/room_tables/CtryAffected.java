package com.example.quakehistory.db.room_tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity (
        foreignKeys = {
                @ForeignKey(
                        entity = EarthQuake.class,
                        parentColumns = "date",
                        childColumns = "date",
                        onDelete = ForeignKey.CASCADE
                )
        },
        tableName = "ctry_affected"
)
public class CtryAffected {
    @PrimaryKey
    public String date;
    @PrimaryKey
    public String country;
}
