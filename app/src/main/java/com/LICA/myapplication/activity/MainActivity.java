package com.LICA.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.LICA.myapplication.R;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progessbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progessbar = findViewById(R.id.progressBar);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                abrirAutenticacao();
            }
        }, 4000);
    }

    private void abrirAutenticacao(){
        progessbar.setVisibility(View.VISIBLE);
        Intent i = new Intent(MainActivity.this, AutenticacaoActivity.class);
        startActivity(i);
        progessbar.setVisibility(View.GONE);
        finish();
    }

}
