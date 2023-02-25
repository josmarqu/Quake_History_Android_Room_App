package com.example.quakehistory.db.crud;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.quakehistory.db.room_tables.EarthQuake;

import java.util.List;

@Dao
public interface EqDao {
    // get all earth quakes
    @Query("SELECT * FROM earth_quake")
    public List<EarthQuake> getAll();

    // insert earth quake
    @Insert
    public long insert(EarthQuake earthQuake);
}
