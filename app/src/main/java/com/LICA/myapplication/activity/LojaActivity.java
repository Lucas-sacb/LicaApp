package com.LICA.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.LICA.myapplication.R;

public class LojaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loja);

        //Configurando toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("LICA Store");
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
