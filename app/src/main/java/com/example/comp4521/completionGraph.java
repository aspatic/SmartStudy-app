//COMP 4521 TING Kai Chung 20435275 kcting
//COMP 4521 CHEN Yu feng 20492352 ychenec
//COMP 4521 KONG Wai Yong 2462462 wykongaa

package com.example.comp4521;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

/*THIS IS THE PAST TASKS' COMPLETION ACTIVITY IN GRAPH FORMAT THAT ALWAYS SHOWS LAST MONTH AND CURRRENT RECORDS */
/*THIS IS WHERE YOU NEED TO DEAL WITH THE DATA WITH DATABASE*/

public class completionGraph extends AppCompatActivity {
    private LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completion_graph);

        mChart = findViewById(R.id.graph);
        //mChart.setOnChartGestureListener(this);
        // mChart.setOnChartValueSelectedListener(this);
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);

        mChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);


        YAxis right = mChart.getAxisRight();
        right.setEnabled(false);

        YAxis left = mChart.getAxisLeft();
        left.setAxisMaximum(100);
        left.setAxisMinimum(0);
        left.enableGridDashedLine(10f,10f,0);

        mChart.getDescription().setEnabled(false);

        ArrayList<Entry> yValues1 = new ArrayList<>();
        ArrayList<Entry> yValues2 = new ArrayList<>();

        for(int i = 1; i<=30;i++){
            float value = (float)(( (int)(Math.random()*10) )*10.0);
            yValues1.add(new Entry(i,value));
        }
        for(int i = 1; i<=15;i++){
            float value = (float)(( (int)(Math.random()*10) )*10.0);
            yValues2.add(new Entry(i,value));
        }

        LineDataSet set1 = new LineDataSet(yValues1,"Record for Last Month");
        set1.setFillAlpha(110);
        set1.setColor(Color.GRAY);
        set1.setLabel("Record for Last Month");
        set1.setLineWidth(2f);
        set1.setDrawValues(false);


        LineDataSet set2 = new LineDataSet(yValues2,"Record for This Month");
        set2.setFillAlpha(110);
        set2.setColor(Color.BLUE);
        set2.setLabel("Record for This Month");
        set2.setLineWidth(2f);
        set2.setCircleColor(Color.BLACK);
        set2.setValueTextSize(8f);

        ArrayList<ILineDataSet> dataset =  new ArrayList<>();
        dataset.add(set1);
        dataset.add(set2);

        LineData data = new LineData(dataset);
        mChart.setData(data);

    }
}
