package com.example.quakehistory.db.crud;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.quakehistory.db.room_tables.CtryAffected;

import java.util.List;

@Dao
public interface CountryDao {
    // get all countriesAffected
    @Query("SELECT * FROM ctry_affected")
    public List<CtryAffected> getAll();

    // insert countryAffected
    @Insert
    public long insert(CtryAffected ctryAffected);
}
