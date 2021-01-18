package com.LICA.myapplication.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.LICA.myapplication.R;
import com.LICA.myapplication.activity.LojaActivity;
import com.LICA.myapplication.activity.MeusCursosUserActivity;
import com.LICA.myapplication.activity.MinhasMetasActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class AulaUserFragment extends Fragment {

    private Button Loja, Minhas_Metas, Cursando;


    public AulaUserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_aula_user, container, false);
        //Iniciando Componentes
        Loja = view.findViewById(R.id.buttonLoja);
        Minhas_Metas = view.findViewById(R.id.Metas);
        Cursando = view.findViewById(R.id.Cursando);

        Loja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), LojaActivity.class);
                startActivity(i);
            }
        });

        Minhas_Metas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MinhasMetasActivity.class);
                startActivity(i);

            }
        });

        Cursando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), MeusCursosUserActivity.class);
                startActivity(i);

            }
        });

        return view;


    }

}
