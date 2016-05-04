package com.example.unique.myapplication.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.unique.myapplication.R;

/**
 * Created by Unique on 4/19/2016.
 */
public class Menu2_Fragment extends Fragment{
    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.main_background,container,false);
        return rootView;

    }
}
