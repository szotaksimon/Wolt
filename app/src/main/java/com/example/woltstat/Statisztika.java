package com.example.woltstat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Statisztika extends AppCompatActivity {


    private Button vissza;
    private DBHelper db;
    private TextView brutto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statisztika);

        init();

        Cursor lekerdez = db.listaz();
        lekerdez.moveToFirst();
        brutto.setText(String.valueOf(lekerdez.getInt(0)));

        vissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent visszaStatisztika = new Intent(Statisztika.this, MainActivity.class);
                startActivity(visszaStatisztika);
                finish();
            }
        });
    }


    private void init() {
        vissza = findViewById(R.id.btnVisszaStatisztika);
        brutto = findViewById(R.id.txtBruttoStatisztika);
        db = new DBHelper(this);
    }

}