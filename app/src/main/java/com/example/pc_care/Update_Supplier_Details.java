package com.example.pc_care;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class Update_Supplier_Details extends AppCompatActivity {

    Button updatebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__supplier__details);

        updatebtn= (Button)findViewById(R.id.updatesup);


        updatebtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Animation addanimation = AnimationUtils.loadAnimation(Update_Supplier_Details.this,R.anim.bounce);
                        updatebtn.startAnimation(addanimation);

            }
        });



    }

}
