package com.example.quakehistory;

import android.os.Bundle;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements OnDialogListener{
    private Button btnShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButton();
    }

    private void initButton() {
        btnShow = findViewById(R.id.btnShow);
        btnShow.setOnClickListener(v -> {
            DialogFilter dialogFilter = new DialogFilter();
            dialogFilter.setCancelable(false);
            dialogFilter.show(getSupportFragmentManager(), "dialogFilter");
        });
        }

    @Override
    public void onDialogPositiveClick(String mag, String magValue, String ctry) {
    }

}
