package com.LICA.myapplication.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.LICA.myapplication.R;
import com.LICA.myapplication.fragment.AulaEmpresaFragment;
import com.LICA.myapplication.fragment.FeedEmpresaFragment;
import com.LICA.myapplication.fragment.MensagensEmpresaFragment;
import com.LICA.myapplication.fragment.PerfilEmpresaFragment;
import com.LICA.myapplication.fragment.PostagemEmpresaFragment;
import com.LICA.myapplication.helper.ConfiguracaoFirebase;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class EmpresaHomeActivity extends AppCompatActivity {

    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresa_home);


        //Configurando toolbar
        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("LICA - Empresa");
        setSupportActionBar( toolbar );

        //Configurações de Objetos
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        //Configurações do BottonNavigationView
        configuraBottonNavigationView();
    }

    private void configuraBottonNavigationView(){

        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavigation_empresa);

        //Configurações iniciais do Bottom Navigation
        bottomNavigationViewEx.setTextVisibility(false);

        //Habilitar navegação
        habilitarNavegacao( bottomNavigationViewEx );

        //Inicia home inst e seleciona como item
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.viewPager_empresa, new FeedEmpresaFragment()).commit();
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);


    }

    //Metodo responsável por tratar eventos de clock na BottomNavigation
    private void habilitarNavegacao(BottomNavigationViewEx viewEx){

        viewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch (menuItem.getItemId()){
                    case R.id.ic_home_empresa :
                        fragmentTransaction.replace(R.id.viewPager_empresa, new FeedEmpresaFragment()).commit();
                        return true;
                    case R.id.ic_aulas_empresa :
                        fragmentTransaction.replace(R.id.viewPager_empresa, new AulaEmpresaFragment()).commit();
                        return true;
                    case R.id.ic_mensagem_empresa :
                        fragmentTransaction.replace(R.id.viewPager_empresa, new MensagensEmpresaFragment()).commit();
                        return true;
                    case R.id.ic_perfil_empresa :
                        fragmentTransaction.replace(R.id.viewPager_empresa, new PerfilEmpresaFragment()).commit();
                        return true;

                }

                return false;
            }
        });

    }

    //Menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_Sair :
                deslogarUsuario();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void deslogarUsuario(){
        try {
            autenticacao.signOut();
            Intent i = new Intent(EmpresaHomeActivity.this, AutenticacaoActivity.class);
            startActivity(i);
            finish();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
