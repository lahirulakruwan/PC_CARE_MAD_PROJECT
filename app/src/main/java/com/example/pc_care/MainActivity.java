package com.example.pc_care;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import StockDatabase.SQLiteHelper_Stock_M;


public class MainActivity extends AppCompatActivity {


    public static SQLiteHelper_Stock_M mSQLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
