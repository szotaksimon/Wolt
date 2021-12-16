package com.example.woltstat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Tankolas extends AppCompatActivity {

    /*
    * editTankolasOsszege
    * editTavolsag
    * editKmAzAutoban
    * btnHozzaadTankolas
    * btnVisszaTankolas
    * */

    private EditText editTankolasOsszege;
    private EditText editTavolsag;
    private EditText editKmAzAutoban;
    private Button btnHozzaadTankolas;
    private Button btnVisszaTankolas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tankolas);

        init();

        btnVisszaTankolas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visszaMain = new Intent(Tankolas.this, MainActivity.class);
                startActivity(visszaMain);
                finish();
            }
        });
    }

    private void init(){
        editTankolasOsszege = findViewById(R.id.editTankolasOsszege);
        editTavolsag = findViewById(R.id.editTavolsag);
        editKmAzAutoban = findViewById(R.id.editKmAzAutoban);
        btnHozzaadTankolas = findViewById(R.id.btnHozzaadTankolas);
        btnVisszaTankolas = findViewById(R.id.btnVisszaTankolas);
    }
}