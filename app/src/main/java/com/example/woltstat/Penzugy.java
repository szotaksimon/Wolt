package com.example.woltstat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Penzugy extends AppCompatActivity {

    /*
    * editBefolytBrutto
    * editBefolytNetto
    * btnHozzaad
    * btnVisszaPenzugy
    * */

    private EditText editBefolytBrutto;
    private EditText editBefolytNetto;
    private Button btnHozzaad;
    private Button btnVisszaPenzugy;
    private int beBruttoInt;
    private int beNettoInt;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penzugy);

        inti();
        btnHozzaad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String beBrutto = editBefolytBrutto.getText().toString().trim();
                String beNetto = editBefolytNetto.getText().toString().trim();
                beBruttoInt = Integer.parseInt(beBrutto);
                beNettoInt = Integer.parseInt(beNetto);

                db.updateStat(beBruttoInt,beNettoInt,0,0);
            }
        });

        btnVisszaPenzugy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent visszaPenz = new Intent(Penzugy.this, MainActivity.class);
                startActivity(visszaPenz);
                finish();
            }
        });
    }

    private int editToInt(String beString){
        if (beString.isEmpty()){
            return 0;
        }else{
         return Integer.parseInt(beString);
        }
    }


    private void inti() {
        editBefolytBrutto = findViewById(R.id.editBefolytBrutto);
        editBefolytNetto = findViewById(R.id.editBefolytNetto);
        btnHozzaad = findViewById(R.id.btnHozzaad);
        btnVisszaPenzugy = findViewById(R.id.btnVisszaPenzugy);
        db = new DBHelper(this);
    }
}