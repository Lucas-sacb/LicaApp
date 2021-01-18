package com.LICA.myapplication.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.LICA.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MensagensUserFragment extends Fragment {


    public MensagensUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mensagens_user, container, false);
    }

}
