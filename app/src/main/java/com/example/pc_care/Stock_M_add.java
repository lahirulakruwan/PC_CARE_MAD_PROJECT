package com.example.pc_care;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import StockDatabase.SQLiteHelper_Stock_M;

public class Stock_M_add extends AppCompatActivity {
    EditText medtItemcode, medtModel, medtBrand, medtAvailability;
    Button mbtnAdd,mBtnList;
    String itemcode,model,brand,availability;
    int availa;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock__m_add);

        medtItemcode = findViewById(R.id.edtItemcode);
        medtModel = findViewById(R.id.edtModel);
        medtBrand = findViewById(R.id.edtBrand);
        medtAvailability = findViewById(R.id.edtAvailability);
        mbtnAdd = findViewById(R.id.btnAdd);
        mBtnList = findViewById(R.id.List);
        Stock_M_ListActivity.mSQLiteHelper_Stock_M = new SQLiteHelper_Stock_M(this);

        mBtnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Stock_M_add.this,Stock_M_ListActivity.class));

            }
        });
    }

        public void AddInfo(View view){
            itemcode = medtItemcode.getText().toString();
            model = medtModel.getText().toString();
            brand = medtBrand.getText().toString();
            availa = Integer.parseInt(medtAvailability.getText().toString());

            boolean result = Stock_M_ListActivity.mSQLiteHelper_Stock_M.insertData(itemcode,model,brand,availability);
            if (result == true){
                Toast.makeText(getApplicationContext(),"Added Successfully",Toast.LENGTH_LONG).show();
            }else if (result == false){
                Toast.makeText(getApplicationContext(),"Adding Error",Toast.LENGTH_LONG).show();
            }

            medtItemcode.setText("");
            medtModel.setText("");
            medtBrand.setText("");
            medtAvailability.setText("");

        }



    }

