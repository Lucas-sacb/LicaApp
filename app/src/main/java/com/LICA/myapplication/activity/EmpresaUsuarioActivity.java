package com.LICA.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.LICA.myapplication.R;

public class EmpresaUsuarioActivity extends AppCompatActivity {


    private Button botaoEmpresa, botaoPessoa;
    private ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa_usuario);

        inicializaComponente();

        botaoPessoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressbar.setVisibility(View.VISIBLE);
                Intent i = new Intent(EmpresaUsuarioActivity.this, CadastroActivity.class);
                startActivity(i);
                progressbar.setVisibility(View.GONE);
                finish();

            }
        });

        botaoEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressbar.setVisibility(View.VISIBLE);
                Intent i = new Intent(EmpresaUsuarioActivity.this, AutenticacaoEmpresaActivity.class);
                startActivity(i);
                progressbar.setVisibility(View.GONE);
                finish();
            }
        });



    }

    private void inicializaComponente(){

        botaoEmpresa = findViewById(R.id.buttonEmpresa);
        botaoPessoa = findViewById(R.id.buttonPessoa);
        progressbar = findViewById(R.id.progressBar4);
    }


}

