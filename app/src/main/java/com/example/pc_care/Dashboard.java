package com.example.pc_care;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Dashboard extends AppCompatActivity {
    ImageButton button;
    ImageButton customer;
    ImageButton supplier;
    ImageButton employeee;
    ImageButton income;
    ImageButton progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        button = findViewById(R.id.imagbtnstock);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),StockHome.class);
                startActivity(intent);
            }
        });

        customer = findViewById(R.id.imagbtncustomer);
        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Customer_Add_Details.class);
                startActivity(intent);
            }
        });

        supplier = findViewById(R.id.imagesupplierbtn);
        supplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Supplier_Add_Details.class);
                startActivity(intent);
            }
        });

        employeee = findViewById(R.id.imgbtnemployee);
        employeee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Employee.class);
                startActivity(intent);
            }
        });

        income = findViewById(R.id.imagbtnincome);
        income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Income.class);
                startActivity(intent);
            }
        });


        progress = findViewById(R.id.imgbtnprogress);
        progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ProgressChart.class);
                startActivity(intent);
            }
        });





    }
}
