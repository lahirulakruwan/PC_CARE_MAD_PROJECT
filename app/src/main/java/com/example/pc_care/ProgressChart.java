package com.example.pc_care;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

public class ProgressChart extends AppCompatActivity {

    private BarChart barChart;
    private BarData barData;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_chart);




     barChart =   (BarChart)findViewById(R.id.barChart);
     barData = new BarData(getXvalues(),getBarvalues());

     barChart.setData(barData);


        barChart.getXAxis().setTextColor(Color.parseColor("#FBC702"));
        barChart.setDescription("Progress chart");

        barChart.setGridBackgroundColor(Color.parseColor("#FBC702"));
        barChart.animateXY(3000,3000);
        barChart.invalidate();


     //xValues
        //BarValues

    }

    private ArrayList<IBarDataSet> getBarvalues() {

        ArrayList<IBarDataSet> barDataSets ;
       ArrayList<BarEntry> barEntries = new ArrayList<>();
        BarEntry barEntry1 = new BarEntry(20.0f,0);
        BarEntry barEntry2 = new BarEntry(80.0f,1);
        BarEntry barEntry3 = new BarEntry(10.0f,2);
        BarEntry barEntry4 = new BarEntry(50.0f,3);
        BarEntry barEntry5 = new BarEntry(40.0f,4);
        BarEntry barEntry6 = new BarEntry(90.0f,5);
        BarEntry barEntry7 = new BarEntry(30.0f,6);
        barEntries.add(barEntry1);
        barEntries.add(barEntry2);
        barEntries.add(barEntry3);
        barEntries.add(barEntry4);
        barEntries.add(barEntry5);
        barEntries.add(barEntry6);
        barEntries.add(barEntry7);


        BarDataSet barDataSet = new BarDataSet(barEntries,"Weekly Progress");

       barDataSet.setColor(Color.parseColor("#002366"));

        barDataSets = new ArrayList<>();
       barDataSets.add(barDataSet);

        return barDataSets;
    }


    private ArrayList<String> getXvalues() {
        ArrayList <String> xvalues = new ArrayList<String>();
        xvalues.add("Mon");
        xvalues.add("Tue");
        xvalues.add("Wed");
        xvalues.add("Thurs");
        xvalues.add("Fri");
        xvalues.add("Sat");
        xvalues.add("Sun");

      return xvalues;
    }
}
