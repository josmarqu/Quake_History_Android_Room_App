package com.example.quakehistory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.quakehistory.db.EarthQuakeDB;
import com.example.quakehistory.db.room_tables.CtryAffected;
import com.example.quakehistory.db.room_tables.EarthQuake;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;


public class QuakeHistory extends AppCompatActivity implements OnDialogListener {
    private Button btnFilter;
    private Button tittle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quake_history);
        initButtons();
        initDataLoad();
        ArrayList<EarthQuake> earthQuakes = new ArrayList<>();
        for (EarthQuake eq : EarthQuakeDB.getDatabase(this).eqDao().getAll()) {
            earthQuakes.add(eq);
        }
        initRvLoad(earthQuakes);
    }


    private void initDataLoad() {
        File dbFile = getDatabasePath("earth_quake_db");
        if (dbFile.exists()) {
            return;
        }
        ArrayList<EarthQuake> earthQuakes = new ArrayList<>();
        earthQuakes = loadArrayEq();
        ArrayList<CtryAffected> ctryAffected = new ArrayList<>();
        ctryAffected = loadArrayCtry();

        EarthQuakeDB eqDB = EarthQuakeDB.getDatabase(this);
        for (EarthQuake eq : earthQuakes) {
            eqDB.eqDao().insert(eq);
        }

        for (CtryAffected ctry : ctryAffected) {
            eqDB.countryDao().insert(ctry);
        }
    }


    private void initRvLoad(ArrayList<EarthQuake> earthQuakes) {
        EqAdapter adapter = new EqAdapter(earthQuakes, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView rvEarthQuake = findViewById(R.id.rvQuakeHistory);
        rvEarthQuake.setLayoutManager(layoutManager);
        rvEarthQuake.setItemAnimator(new DefaultItemAnimator());
        rvEarthQuake.setAdapter(adapter);
    }


    private ArrayList<CtryAffected> loadArrayCtry() {
        ArrayList<CtryAffected> listCtry = new ArrayList<>(Arrays.asList(
                new CtryAffected("May 22, 1960, 15:11", "Chile"),
                new CtryAffected("December 26, 2004, 07:58", "Indonesia"),
                new CtryAffected("March 27 1964, 17:36", "United States"),
                new CtryAffected("March 11 2011, 14:46", "Japan"),
                new CtryAffected("November 4, 1952, 16:58", "Russia"),
                new CtryAffected("August 13, 1868, 21:30", "Chile"),
                new CtryAffected("October 28, 1746, 22:30", "Peru"),
                new CtryAffected("January 26, 1700, 21:30", "United States"),
                new CtryAffected("February 27, 2010, 03:34", "Chile"),
                new CtryAffected("February 6, 2023, 01:17", "Turkey"),
                new CtryAffected("February 6, 2023, 01:17", "Syria"),
                new CtryAffected("January 31, 1906, 15:36", "Ecuador"),
                new CtryAffected("January 31, 1906, 15:36", "Colombia"),
                new CtryAffected("November 25, 1833, 20:00", "Indonesia"),
                new CtryAffected("November 1, 1755, 10:16", "Portugal"),
                new CtryAffected("July 8, 1730, 04:45", "Chile"),
                new CtryAffected("April 11, 2012, 15:38", "Indonesia"),
                new CtryAffected("March 28, 2005, 23:09", "Indonesia"),
                new CtryAffected("March 9, 1957, 14:22", "United States"),
                new CtryAffected("August 15, 1950", "India"),
                new CtryAffected("August 15, 1950", "China"),
                new CtryAffected("November 10, 1922, 23:53", "Argentina"),
                new CtryAffected("March 28, 1787, 11:30", "Mexico"),
                new CtryAffected("February 3, 1923, 04:58", "Russia"),
                new CtryAffected("October 20, 1687, 09:15", "Peru"),
                new CtryAffected("December 16, 1575, 14:30", "Chile"),
                new CtryAffected("September 16, 2015, 19:54", "Chile"),
                new CtryAffected("June 23, 2001, 15:33", "Peru")
        ));
        return listCtry;
    }

    private ArrayList<EarthQuake> loadArrayEq() {
        ArrayList<EarthQuake> listEq = new ArrayList<>(Arrays.asList(
        new EarthQuake("May 22, 1960, 15:11", 9.5, "1960 Valdivia earthquake",
                "Valdivia, Los Ríos Region", "38°14′24′′S 73°3′0′′W", "1655 to 2000")
                , new EarthQuake("December 26, 2004, 07:58", 9.3, "2004 Indian Ocean earthquake and tsunami",
                "Off the west coast of northern Sumatra", "No data", "230270")
                , new EarthQuake("March 27, 1964, 17:36", 9.2, "1964 Alaska earthquake",
                "Anchorage, Alaska", "61°N 148°W", "128"),
                new EarthQuake("March 11, 2011, 14:46", 9.1, "2011 Tōhoku earthquake and tsunami",
                        "East coast of Tōhoku, Honshū", "38°19′19.20′′N 142°22′8.40′′E",
                        "15897"),
                new EarthQuake("November 4, 1952, 16:58", 9.0, "1952 Kamchatka earthquakes",
                        "Kamchatka Peninsula", "52°48′N 159°30′E", "2366"),
                new EarthQuake("August 13, 1868, 21:30", 9.0, "1868 Arica earthquake", "Arica",
                        "18°36′S 71°0′W", "693"),
                new EarthQuake("October 28, 1746, 22:30", 9.0, "1746 Lima–Callao earthquake",
                        "Lima and Callao", "11°21′00′′S 77°16′48′′W", "15000 to 20000"),
                new EarthQuake("January 26, 1700, 21:30", 9.0, "1700 Cascadia earthquake",
                        "California, Oregon, Washington and British Columbia", "No data", "No data"),
                new EarthQuake("February 27, 2010, 03:34", 8.8, "2010 Chile earthquake",
                        "Cobquecura, Biobío Region (now Ñuble)", "35°50′45.6′′S 72°42′57.6′′W",
                        "525"),
                new EarthQuake("January 31, 1906, 15:36", 8.8, "1906 Ecuador–Colombia earthquake",
                        "Off the coast of Esmeraldas", "1°0′N 81°30′W", "1500"),
                new EarthQuake("November 25, 1833, 20:00", 8.8, "1833 Sumatra earthquake",
                        "In the sea south of the island of Sumatra, 175 km south of Padang",
                        "3°30′S 102°12′E", "No data"),
                new EarthQuake("November 1, 1755, 10:16", 8.7, "1755 Lisbon earthquake", "Lisbon",
                        "36°N 11°W", "60,000-100,000"),
                new EarthQuake("July 8, 1730, 04:45", 8.7, "1730 Valparaiso earthquake",
                        "Valparaiso and La Serena", "33°30′S 71°36′W", "300"),
                new EarthQuake("April 11, 2012, 15:38", 8.6, "2012 Indian Ocean earthquake",
                        "Off the north coast of Sumatra", "02°18′39.6′′N 93°03′46.8′′E", "10"),
                new EarthQuake("March 28, 2005, 23:09", 8.6, "2005 Sumatra earthquake",
                        "Off the west coast of northern Sumatra", "2°36′N 97°6′E", "1,300"),
                new EarthQuake("March 9, 1957, 14:22", 8.6, "1957 Andreanof Islands earthquake",
                        "Andreanof Islands, Alaska", "51°33′36′′N 175°23′24′′W", "0"),
                new EarthQuake("August 15, 1950", 8.6, "1950 Assam-Tibet earthquake",
                        "Assam (India), Tibet (China)", "28°30′N 96°30′E", "1,526"),
                new EarthQuake("November 10, 1922, 23:53", 8.6, "1922 Vallenar earthquake",
                        "Atacama Region and Catamarca Province", "Unknown", "1,500"),
                new EarthQuake("March 28, 1787, 11:30", 8.6, "1787 Mexico earthquake",
                        "Coasts of Oaxaca and Guerrero", "Unknown", "11"),
                new EarthQuake("February 3, 1923, 04:58", 8.5, "1923 Kamchatka earthquake",
                        "Kamchatka Peninsula", "54°N 161°E", "Unknown"),
                new EarthQuake("October 20, 1687, 09:15", 8.5, "1687 Lima–Callao earthquake",
                        "Lima and Callao", "Unknown", "5,000"),
                new EarthQuake("December 16, 1575, 14:30", 8.5, "1575 Valdivia earthquake",
                        "Valdivia", "39°48′S 73°12′W", "1,221"),
                new EarthQuake("September 16, 2015, 19:54", 8.4, "2015 Illapel earthquake",
                        "Off the coast of Canela, Coquimbo Region", "31°34′52′′S 71°45′07′′W", "12"),
                new EarthQuake("June 23, 2001, 15:33", 8.4, "2001 southern Peru earthquake",
                        "Departments of Arequipa, Moquegua, and Tacna", "16°15′36′′S 73°38′24′′W", "102"),
                new EarthQuake("6 de febrero de 2023, 01:17", 7.8, "Terremoto de Turquía y Siria 2023",
                        "Turquía, Siria", "37°10′26′′N 37°01′55′′E", "40120")));
        return listEq;
    }

    private void initButtons() {
        btnFilter = findViewById(R.id.btnFilter);
        btnFilter.setOnClickListener(v -> {
            DialogFilter dialogFilter = new DialogFilter();
            dialogFilter.setCancelable(false);
            dialogFilter.show(getSupportFragmentManager(), "dialogFilter");
        });
        tittle = findViewById(R.id.tittle);
    }

    @Override
    public void onDialogPositiveClick(String operator, double magValue, String ctry) {
        tittle.setTextSize(14);
        tittle.setText("Magnitude: " + operator + " " + magValue + " " + "Country: " + ctry);
        ArrayList<EarthQuake> earthQuakes = new ArrayList<>();
        if (operator.equals("Any") && ctry.equals("All")) {
            for (EarthQuake eq : EarthQuakeDB.getDatabase(this).eqDao().getAll()) {
                earthQuakes.add(eq);
            }
        }
        else if (operator.equals("Any") && !ctry.equals("All")) {
            for (EarthQuake eq : EarthQuakeDB.getDatabase(this).eqDao().getAllByCountry(ctry)) {
                earthQuakes.add(eq);
            }

        }
        else if (ctry.equals("All") && !operator.equals("Any")) {
            for (EarthQuake eq : EarthQuakeDB.getDatabase(this).eqDao().getAllByMag(magValue, operator)) {
                earthQuakes.add(eq);
            }
        }
        else{
            for (EarthQuake eq : EarthQuakeDB.getDatabase(this).eqDao().getAllByMagAndCountry(magValue, operator, ctry)) {
                earthQuakes.add(eq);
            }
        }
        if (earthQuakes.size() == 0) {
            Toast.makeText(this, "No results found", Toast.LENGTH_SHORT).show();
        }
        initRvLoad(earthQuakes);
    }
}