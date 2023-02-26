package com.example.quakehistory.db.crud;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.quakehistory.db.room_tables.EarthQuake;

import java.util.List;

@Dao
public interface EqDao {
    // get all earth quakes ordered by magnitude
    @Query("SELECT * FROM earth_quake ORDER BY magnitude DESC")
    public List<EarthQuake> getAll();

    // get all earth quakes by magnitude and operator
    @Query("SELECT * FROM earth_quake WHERE "
            + "CASE :operator "
            + "WHEN '>' THEN magnitude > :mag "
            + "WHEN '>=' THEN magnitude >= :mag "
            + "WHEN '<' THEN magnitude < :mag "
            + "WHEN '<=' THEN magnitude <= :mag "
            + "ELSE magnitude = :mag END")
    public List<EarthQuake> getAllByMag(double mag, String operator);

    // get all earth quakes by country
    @Query("SELECT * FROM earth_quake e, ctry_affected c WHERE country LIKE '%' || :country || '%' AND e.date = c.date")
    public List<EarthQuake> getAllByCountry(String country);

    // get all earth quakes by magnitude and operator and country
    @Query("SELECT * FROM earth_quake e, ctry_affected c WHERE "
            + "CASE :operator "
            + "WHEN '>' THEN magnitude > :mag "
            + "WHEN '>=' THEN magnitude >= :mag "
            + "WHEN '<' THEN magnitude < :mag "
            + "WHEN '<=' THEN magnitude <= :mag "
            + "ELSE magnitude = :mag END "
            + "AND country LIKE '%' || :country || '%' AND e.date = c.date")
    public List<EarthQuake> getAllByMagAndCountry(double mag, String operator, String country);

    // insert earth quake
    @Insert
    public long insert(EarthQuake earthQuake);
}
