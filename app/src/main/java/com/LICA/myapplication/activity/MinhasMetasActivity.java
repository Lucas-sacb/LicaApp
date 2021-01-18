package com.LICA.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.LICA.myapplication.R;

public class MinhasMetasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_metas);

        //Configurando toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Minhas Metas ");
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
