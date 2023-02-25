package com.example.quakehistory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;


public class QuakeHistory extends AppCompatActivity implements OnDialogListener{
   private Button btnFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quake_history);
        initButton();
    }

    private void initButton() {
        btnFilter = findViewById(R.id.btnFilter);
        btnFilter.setOnClickListener(v -> {
            DialogFilter dialogFilter = new DialogFilter();
            dialogFilter.setCancelable(false);
            dialogFilter.show(getSupportFragmentManager(), "dialogFilter");
        });
    }

    @Override
    public void onDialogPositiveClick(String mag, float magValue, String ctry) {
        System.out.println("Mag: " + mag + " MagValue: " + magValue + " Ctry: " + ctry);
    }
}