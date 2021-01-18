package com.LICA.myapplication.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.LICA.myapplication.R;
import com.LICA.myapplication.activity.PerguntaUserActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilUserFragment extends Fragment {

    private ProgressBar progressBar;
    private CircleImageView imagePerfil;
    private TextView textSeguidoresUser, textSeguindoUser, textrespostaUser1,
            textrespostaUser2, textrespostaUser3, textrespostaUser4;
    private Button buttonEditarPerfil;



    public PerfilUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil_user, container, false);

        //Configurações dos componentes
        imagePerfil = view.findViewById(R.id.imageUser);
        textSeguidoresUser = view.findViewById(R.id.SeguidoresUser);
        textSeguindoUser = view.findViewById(R.id.SeguindoUser);
        buttonEditarPerfil = view.findViewById(R.id.buttonEditPerfilUser);



        return view;
    }

}
