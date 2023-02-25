package com.example.quakehistory.db.room_tables;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity (
        indices = {@Index(value = {"device_name"}, unique = true)},
        tableName = "earth_quake"
)
public class EarthQuake {
    @NonNull
    @PrimaryKey
    public String date;

    @ColumnInfo(name = "device_name")
    public String deviceName;

    @ColumnInfo(name = "magnitude")
    public Double magnitude;

    @ColumnInfo(name = "coordinates")
    public String coordinates;

    @ColumnInfo(name = "location")
    public String location;

    @ColumnInfo(name = "death_toll")
    public String deathToll;

    public EarthQuake(@NonNull String date, Double magnitude, String deviceName,  String location, String coordinates, String deathToll) {
        this.date = date;
        this.deviceName = deviceName;
        this.magnitude = magnitude;
        this.coordinates = coordinates;
        this.location = location;
        this.deathToll = deathToll;
    }

    @Override
    public String toString() {
        return "EarthQuake{" +
                "date='" + date + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", magnitude=" + magnitude +
                ", coordinates='" + coordinates + '\'' +
                ", location='" + location + '\'' +
                ", deathToll='" + deathToll + '\'' +
                '}';
    }
}
