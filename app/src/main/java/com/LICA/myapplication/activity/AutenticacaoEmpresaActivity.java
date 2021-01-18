package com.LICA.myapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.Toast;

import com.LICA.myapplication.R;
import com.LICA.myapplication.helper.ConfiguracaoFirebase;
import com.LICA.myapplication.helper.UsuarioFirebase;
import com.LICA.myapplication.helper.ValidaCPF;
import com.LICA.myapplication.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.LICA.myapplication.helper.ValidaCNPJ;
import java.util.Scanner;

public class AutenticacaoEmpresaActivity extends AppCompatActivity {

    private Switch swit;
    private Button botaoAcessar;
    private EditText campoCNPJ, campoEmail, campoSenha, campoNome;
    private ProgressBar progessbar;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autenticacao_empresa);

        inicializaComponentes();

        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        botaoAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progessbar.setVisibility(View.VISIBLE);
                String email = campoEmail.getText().toString();
                String senha = campoSenha.getText().toString();
                String nome = campoNome.getText().toString();
                String CNPJ = campoCNPJ.getText().toString();

                if ( !nome.isEmpty() ){
                    if ( !CNPJ.isEmpty() ) {
                        if (!email.isEmpty()) {
                            if (!senha.isEmpty()) {
                                if(ValidaCNPJ.isCNPJ(CNPJ) == true) {

                                    final Usuario usuario = new Usuario();
                                    usuario.setNome(nome);
                                    usuario.setCNPJ(CNPJ);
                                    usuario.setEmail(email);
                                    usuario.setSenha(senha);
                                    usuario.setTipo( VerificaTipoUsuario() );

                                    autenticacao.createUserWithEmailAndPassword(
                                            email, senha
                                    ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {//Redireciona o usuário criado

                                                String idUsuario =task.getResult().getUser().getUid();
                                                usuario.setId(idUsuario);
                                                usuario.salvar();

                                                //Atualizar nome no UserProfile
                                                UsuarioFirebase.atualizarNomeUsuario( usuario.getNome() );

                                                Toast.makeText(AutenticacaoEmpresaActivity.this,
                                                        "Cadastro Realizado com sucesso!",
                                                        Toast.LENGTH_SHORT).show();
                                                abrirTelaPrincipal();

                                            } else {//Tratando as exceções

                                                String erroExcecao = "";

                                                try {
                                                    throw task.getException();
                                                } catch (FirebaseAuthWeakPasswordException e) {
                                                    erroExcecao = "digite uma senha mais forte!";
                                                } catch (FirebaseAuthInvalidCredentialsException e) {
                                                    erroExcecao = "por favor, digite um e-mail válido!";
                                                } catch (FirebaseAuthUserCollisionException e) {
                                                    erroExcecao = "esta conta já foi cadastrada";
                                                } catch (Exception e) {
                                                    erroExcecao = "ao cadastrar usuário: " + e.getMessage();
                                                    e.printStackTrace();
                                                }

                                                Toast.makeText(AutenticacaoEmpresaActivity.this,
                                                        "Erro " + erroExcecao,
                                                        Toast.LENGTH_SHORT).show();
                                                progessbar.setVisibility(View.GONE);
                                            }


                                        }
                                    });
                                }else{
                                    Toast.makeText(AutenticacaoEmpresaActivity.this,
                                            "Digite um CNPJ Válido!",
                                            Toast.LENGTH_SHORT).show();
                                    progessbar.setVisibility(View.GONE);
                                }

                            } else {
                                Toast.makeText(AutenticacaoEmpresaActivity.this,
                                        "Preencha o campo senha!",
                                        Toast.LENGTH_SHORT).show();
                                progessbar.setVisibility(View.GONE);
                            }
                        } else {
                            Toast.makeText(AutenticacaoEmpresaActivity.this,
                                    "Preencha o campo email!",
                                    Toast.LENGTH_SHORT).show();
                            progessbar.setVisibility(View.GONE);
                        }
                    }else {
                        Toast.makeText(AutenticacaoEmpresaActivity.this,
                                "Preencha o Campo CPF!",
                                Toast.LENGTH_SHORT).show();
                        progessbar.setVisibility(View.GONE);
                    }

                }else {
                    Toast.makeText(AutenticacaoEmpresaActivity.this,
                            "Preencha o campo nome!",
                            Toast.LENGTH_SHORT).show();
                    progessbar.setVisibility(View.GONE);
                }



            }
        });

    }

    public String VerificaTipoUsuario(){
        return swit.isChecked()?"Erro":"Empresa";

    }

    private void abrirTelaPrincipal(){//Manda para a tela inicial
        Intent i = new Intent(AutenticacaoEmpresaActivity.this, EmpresaHomeActivity.class);
        startActivity(i);
        progessbar.setVisibility(View.GONE);
        finish();

    }

    private void inicializaComponentes(){
        campoEmail = findViewById(R.id.editCadastroEmailEmpresa);
        campoSenha = findViewById(R.id.editLoginSenhaEmpresa);
        campoNome = findViewById(R.id.editLoginNomeEmpresa);
        campoCNPJ = findViewById(R.id.editCadastroCNPJ);
        botaoAcessar = findViewById(R.id.buttonRegistrarEmpresa);
        swit = findViewById(R.id.switc);
        progessbar = findViewById(R.id.progressBar3);
    }

}
