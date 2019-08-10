package com.example.pc_care;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class LoginOrRestPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_rest_password);


    }
    public void changeFragment(View view) {
        Fragment fragment;
        if (view == findViewById(R.id.btnFragment1)) {
            fragment = new LOGIN();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frgmntDefault, fragment);
            ft.commit();

        }

        if (view == findViewById(R.id.btnFragment2)) {
            fragment = new RESET();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frgmntDefault,fragment);
            ft.commit();


        }
        if (view == findViewById(R.id.btnFragment3)) {
            fragment = new SignUp();
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.frgmntDefault,fragment);
            ft.commit();


        }

    }

}
