package com.LICA.myapplication.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.LICA.myapplication.R;
import com.LICA.myapplication.activity.EntreEmContato;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedUserFragment extends Fragment {

    private Button Contato;


    public FeedUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_feed_user, container, false);

        //Iniciando componentes
        Contato = view.findViewById(R.id.Contato);

        Contato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), EntreEmContato.class);
                startActivity(i);

            }
        });



        return view;
    }

}
