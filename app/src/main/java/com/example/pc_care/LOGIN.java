package com.example.pc_care;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Database_company.DatabseHelper2;

public class LOGIN extends Fragment {
     public static Button b4;
    public static Button dashboardbtn;
    // EditText e4, e5;
    //DatabseHelper2 db;

    //String s4 = e4.getText().toString();
    //String s5 = e5.getText().toString();

    @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
    Bundle savedInstanceState) {

    return inflater.inflate(R.layout.fragment_login, container, false);

}

}