package com.example.quakehistory.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.quakehistory.db.crud.CountryDao;
import com.example.quakehistory.db.crud.EqDao;
import com.example.quakehistory.db.room_tables.CtryAffected;
import com.example.quakehistory.db.room_tables.EarthQuake;

@Database(entities = {EarthQuake.class, CtryAffected.class}, version = 1)
public abstract class EarthQuakeDB extends RoomDatabase {
    public abstract EqDao eqDao();
    public abstract CountryDao countryDao();
    private static EarthQuakeDB EARTH_QUAKE_DB;

    public static EarthQuakeDB getDatabase(Context context) {
        if (EARTH_QUAKE_DB == null) {
            EARTH_QUAKE_DB = Room.databaseBuilder(context.getApplicationContext(),
                    EarthQuakeDB.class,
                    "earth_quake_db").allowMainThreadQueries().build();
        }
        return EARTH_QUAKE_DB;
    }
}
