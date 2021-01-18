package com.LICA.myapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.LICA.myapplication.R;
import com.LICA.myapplication.helper.ConfiguracaoFirebase;
import com.LICA.myapplication.helper.UsuarioFirebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class AutenticacaoActivity extends AppCompatActivity {

    private Button botaoAcessar, buttonCadastrar;
    private EditText campoEmail, campoSenha;
    private FirebaseAuth autenticacao;
    private ProgressBar progessbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autenticacao);
        inicializaComponentes();
        progessbar.setVisibility(View.VISIBLE);


        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        //autenticacao.signOut(); //Desloga


        //Verificar o Usuario logado
        VerificarUsuarioLogado();

        botaoAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = campoEmail.getText().toString();
                String senha = campoSenha.getText().toString();

                if ( !email.isEmpty() ){
                    if ( !senha.isEmpty() ){

                            autenticacao.signInWithEmailAndPassword(
                                    email, senha
                            ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){

                                        UsuarioFirebase.redirecionaUsuarioLogado(AutenticacaoActivity.this);
                                        progessbar.setVisibility(View.VISIBLE);
                                        Toast.makeText(AutenticacaoActivity.this,
                                                "Logado com sucesso",
                                                Toast.LENGTH_SHORT).show();
                                        progessbar.setVisibility(View.GONE);


                                    } else {
                                        Toast.makeText(AutenticacaoActivity.this,
                                                "Erro ao fazer login" + task.getException(),
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                    }else {
                        Toast.makeText(AutenticacaoActivity.this,
                                "Preencha o campo Senha!",
                                Toast.LENGTH_SHORT).show();
                    }
                    }else {
                    Toast.makeText(AutenticacaoActivity.this,
                            "Preencha o campo Email!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), EmpresaUsuarioActivity.class));

            }
        });

    }


    private void VerificarUsuarioLogado(){
        FirebaseUser usuarioAtual = autenticacao.getCurrentUser();
        if ( usuarioAtual != null ){
            UsuarioFirebase.redirecionaUsuarioLogado(AutenticacaoActivity.this);
            progessbar.setVisibility(View.GONE);

        }else {
            progessbar.setVisibility(View.GONE);
        }

    }

    private void inicializaComponentes(){
        campoEmail = findViewById(R.id.editLoginNome);
        campoSenha = findViewById(R.id.editLoginSenha);
        botaoAcessar = findViewById(R.id.buttonAcesso);
        buttonCadastrar = findViewById(R.id.buttonCadastrar);
        progessbar = findViewById(R.id.progressBar2);
    }

}
