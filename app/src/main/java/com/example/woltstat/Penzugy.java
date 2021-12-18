package com.example.woltstat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
    private Button btnStatisztikaPenzugy;
    private int beBruttoInt;
    private int beNettoInt;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penzugy);

        init();
        btnHozzaad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String beBrutto = editBefolytBrutto.getText().toString().trim();
                String beNetto = editBefolytNetto.getText().toString().trim();
                if(beBrutto.isEmpty()){
                    beBrutto = "0";
                }
                if(beNetto.isEmpty()){
                    beNetto = "0";
                }
                beBruttoInt = Integer.parseInt(beBrutto);
                beNettoInt = Integer.parseInt(beNetto);

                db.updateBruttoNetto(beBruttoInt,beNettoInt);
                keyboardClose();
                setDefaultValueEditTextsClearFocus();
                Toast.makeText(getApplicationContext(), "Sikeres felvétel", Toast.LENGTH_SHORT).show();
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

        btnStatisztikaPenzugy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tovabbStatisztikara = new Intent(Penzugy.this, Statisztika.class);
                startActivity(tovabbStatisztikara);
                finish();
            }
        });
    }

    private void init() {
        editBefolytBrutto = findViewById(R.id.editBefolytBrutto);
        editBefolytNetto = findViewById(R.id.editBefolytNetto);
        btnHozzaad = findViewById(R.id.btnHozzaad);
        btnVisszaPenzugy = findViewById(R.id.btnVisszaPenzugy);
        btnStatisztikaPenzugy = findViewById(R.id.btnStatisztikaPenzugy);
        db = new DBHelper(this);
    }

    private void keyboardClose(){
        View view = this.getCurrentFocus();
        if (view != null){
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void setDefaultValueEditTextsClearFocus(){
        editBefolytBrutto.setText("");
        editBefolytNetto.setText("");
        editBefolytBrutto.clearFocus();
        editBefolytNetto.clearFocus();
    }
}