package com.LICA.myapplication.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.LICA.myapplication.R;
import com.LICA.myapplication.helper.ConfiguracaoFirebase;
import com.google.firebase.auth.FirebaseAuth;

public class PerguntaUserActivity extends AppCompatActivity {

    private EditText campopergunta1, campopergunta2, campopergunta4;
    private Button botaoAcessar;
    private FirebaseAuth autenticacao;
    private ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta_user);

        //Configurando toolbar
        Toolbar toolbar = findViewById(R.id.toolbarSecond);
        toolbar.setTitle("Editar Perfil");
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Inicializa Componentes
        inicializaComponentes();

        //Autenticação do Firebase
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        //Ação de clique no botão registrar
        botaoAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressbar.setVisibility(View.VISIBLE);

                String pergunta1 = campopergunta1.getText().toString();
                String pergunta2 = campopergunta2.getText().toString();
                String pergunta4 = campopergunta4.getText().toString();

                if (!pergunta1.isEmpty()){
                    if (pergunta2.isEmpty()){
                        if (pergunta4.isEmpty()){

                        }else {

                        }

                    }else {

                    }

                }else {

                }


            }
        });

    }

    private void inicializaComponentes(){
        campopergunta1= findViewById(R.id.editPergunta1User);
        campopergunta2 = findViewById(R.id.editPergunta2User);
        campopergunta4 = findViewById(R.id.editPergunta4User);
        botaoAcessar = findViewById(R.id.buttonRedirectUser);
        progressbar = findViewById(R.id.progressBa5);
    }

}
