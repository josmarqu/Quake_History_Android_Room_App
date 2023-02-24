package com.example.quakehistory.db.room_tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity (
        indices = {@Index(value = {"device_name"}, unique = true)},
        tableName = "earth_quake"
)
public class EarthQuake {
    @PrimaryKey
    public String date;

    @ColumnInfo(name = "device_name")
    public String deviceName;

    @ColumnInfo(name = "magnitude")
    public float magnitude;

    @ColumnInfo(name = "coordinates")
    public String coordinates;

    @ColumnInfo(name = "location")
    public String location;

    @ColumnInfo(name = "death_toll")
    public int deathToll;
}
