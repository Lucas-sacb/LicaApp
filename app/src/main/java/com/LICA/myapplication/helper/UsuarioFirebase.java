package com.LICA.myapplication.helper;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;

import com.LICA.myapplication.activity.EmpresaHomeActivity;
import com.LICA.myapplication.activity.HomeActivity;
import com.LICA.myapplication.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class UsuarioFirebase {

    public static FirebaseUser getUsuarioAtual(){
        FirebaseAuth usuario = ConfiguracaoFirebase.getFirebaseAutenticacao();
        return usuario.getCurrentUser();
    }

    public static boolean atualizarNomeUsuario(String nome){

        try {

            FirebaseUser user = getUsuarioAtual();
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setDisplayName( nome )
                    .build();
            user.updateProfile( profile ).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if ( !task.isSuccessful() ){
                        Log.d("Perfil", "Erro ao atualizar nome do perfil.");
                    }

                }
            });

            return true;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public static void redirecionaUsuarioLogado(final Activity activity){

        DatabaseReference usuariosRef = ConfiguracaoFirebase.getFirebase()
                .child("usuarios")
                .child( getIdentificadorUsuario() );
        usuariosRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Usuario usuario = snapshot.getValue( Usuario.class );
                String tipoUsuario = usuario.getTipo();
                if ( tipoUsuario.equals("Empresa") ){
                    activity.startActivity(new Intent(activity, EmpresaHomeActivity.class));

                }else{
                    activity.startActivity(new Intent(activity, HomeActivity.class));

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public static String getIdentificadorUsuario(){
        return getUsuarioAtual().getUid();
    }

}
