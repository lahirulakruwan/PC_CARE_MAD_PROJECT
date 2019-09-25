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


public class SignUp extends Fragment {
    EditText e1, e2, e3;
    DatabseHelper2 db;
    Button b4;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=  inflater.inflate(R.layout.fragment_sign_up, container, false);
       db = new DatabseHelper2(getActivity());
        //e1=(EditText)findViewById(R.id.Username);
        final EditText e1 = (EditText) rootView.findViewById(R.id.Username);
        final EditText e2 = (EditText) rootView.findViewById(R.id.mail);
        final EditText e3 = (EditText) rootView.findViewById(R.id.pwd);
        final Button b4 = (Button) rootView.findViewById(R.id.submit);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = e1.getText().toString();
                String s2 = e2.getText().toString();
                String s3 = e3.getText().toString();
                if (b4.equals(R.id.signup)) {

                    Toast.makeText(getContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                if (s2.equals(s2)) {

                    Boolean chkemail = db.chkemail(s1);
                    if (chkemail == true) {
                        Boolean insert = db.insert(s1, s2, s3);
                        if (insert == true) {
                            Toast.makeText(getContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                        }
                    } else {

                        Toast.makeText(getContext(), "Email already  exists", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        return rootView;


        }

}