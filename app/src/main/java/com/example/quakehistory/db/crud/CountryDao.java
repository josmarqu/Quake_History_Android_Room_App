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

    // get all countriesAffected names
    @Query("SELECT country FROM ctry_affected")
    public List<String> getAllCountries();

    // insert countryAffected
    @Insert
    public long insert(CtryAffected ctryAffected);
}
