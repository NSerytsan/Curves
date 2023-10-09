package com.example.curves;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity2 extends AppCompatActivity {

    private LineGraphSeries<DataPoint> series1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent intent = getIntent();
        String type = intent.getStringExtra(MainActivity.type);
        TextView text1 = (TextView) findViewById(R.id.textView);

        Double eA = intent.getDoubleExtra(MainActivity.e_A, 1);
        Double eB = intent.getDoubleExtra(MainActivity.e_B, 1);
        Double hA = intent.getDoubleExtra(MainActivity.h_A, 1);
        Double hB = intent.getDoubleExtra(MainActivity.h_B, 1);
        Double pA = intent.getDoubleExtra(MainActivity.p_A, 1);

        String eLable = "Коефіцієнти еліпса: a = " + eA + "; " + "b = " + eB;
        String hLable = "Коефіцієнти гіперболи: a = " + hA + "; " + "b = " + hB;
        String pLable = "Коефіцієнт параболи: a = " + pA;

        double x, y;
        x=-100;

        GraphView graph = (GraphView) findViewById(R.id.graph);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(-50);
        graph.getViewport().setMaxX(50);

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(-50);
        graph.getViewport().setMaxY(50);

        series1 = new LineGraphSeries<>();
        int numDataPoints = 2000;

        if(type.equals("Еліпс")){
            /*for (double t = 0; t < 2 * Math.PI; t += 0.01) {
                x = 5 * Math.cos(t);
                y = 6 * Math.sin(t);

                series1.appendData(new DataPoint(x, y), true, 500);
            }
            graph.addSeries(series1);*/

            text1.setText(eLable);
        }
        else if(type.equals("Гіпербола")){
            for(int i=0; i<numDataPoints; i++){
                x += 0.1;
                y = 5*Math.sqrt(-1+(x*x)/36);
                series1.appendData(new DataPoint(x, y), true, 2000);
            }
            graph.addSeries(series1);

            text1.setText(hLable);
        }
        else if(type.equals("Парабола")){
            for(int i=0; i<numDataPoints; i++){
                x += 0.1;
                y = pA*x*x;
                series1.appendData(new DataPoint(x, y), true, 2000);
            }
            graph.addSeries(series1);

            text1.setText(pLable);
        }


        /*double a, b;
        a = 5;
        b = 6;

        for (x = -100; x <= 100; x += 0.1) {
            y = b / a * Math.sqrt(x * x - a * a);
            series1.appendData(new DataPoint(x, y), true, 2000); // Додавання точки до графіка
        }
        graph.addSeries(series1);*/

        Button save = findViewById(R.id.saveBtn);
        Button info = findViewById(R.id.infoBtn);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
            }
        });

        /*String filename = "myFile.txt";
        String filepath = "";
        if(!isExternalStorageAvailableForRW()){
            save.setEnabled(false);
        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File myExternalFile = new File(getExternalFilesDir(filepath), filename);
                FileOutputStream fos = null;
                fos = new FileOutputStream(myExternalFile);
                fos.write();
            }
        });*/
    }

    private boolean isExternalStorageAvailableForRW(){
        String extStorageState = Environment.getExternalStorageState();
        if(extStorageState.equals(Environment.MEDIA_MOUNTED)){
            return true;
        }
        return false;
    }
}