package com.example.woltstat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnPenzugy;
    private Button btnTankolas;
    private Button btnStatisztika;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        btnPenzugy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent penzugyek = new Intent(MainActivity.this, Penzugy.class);
                startActivity(penzugyek);
                finish();
            }
        });

        btnTankolas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tankolas = new Intent(MainActivity.this, Tankolas.class);
                startActivity(tankolas);
                finish();
            }
        });

        btnStatisztika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent statisztika = new Intent(MainActivity.this, Statisztika.class);
                startActivity(statisztika);
                finish();
            }
        });
    }

    private void init(){
        btnPenzugy = findViewById(R.id.btnPenzugy);
        btnTankolas = findViewById(R.id.btnTankolas);
        btnStatisztika = findViewById(R.id.btnStatisztika);
    }
}