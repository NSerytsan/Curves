package com.example.curves;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String type = "type";
    public static final String e_A = "ellipseA";
    public static final String e_B = "ellipseB";
    public static final String h_A = "hyperbolaA";
    public static final String h_B = "hyperbolaB";
    public static final String p_A = "parabolaA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.curves_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.choises, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Group ellipse = findViewById(R.id.ellipse);
        Group hyperbola = findViewById(R.id.hyperbola);
        Group parabola = findViewById(R.id.parabola);

        EditText eA = findViewById(R.id.ellipseInputEditTextA);
        EditText eB = findViewById(R.id.ellipseInputEditTextB);
        EditText hA = findViewById(R.id.hyperbolaInputEditTextA);
        EditText hB = findViewById(R.id.hyperbolaInputEditTextB);
        EditText pA = findViewById(R.id.parabolaInputEditTextA);

        eA.setText("1.0");
        eB.setText("1.0");
        hA.setText("1.0");
        hB.setText("1.0");
        pA.setText("1.0");


        Button nextBtn = findViewById(R.id.next);

        if (text.equals("Еліпс")){
            ellipse.setVisibility(View.VISIBLE);
        }
        else{
            ellipse.setVisibility(View.GONE);
        }

        if(text.equals("Гіпербола")){
            hyperbola.setVisibility(View.VISIBLE);
        }
        else {
            hyperbola.setVisibility(View.GONE);
        }

        if(text.equals("Парабола")){
            parabola.setVisibility(View.VISIBLE);
        }
        else {
            parabola.setVisibility(View.GONE);
        }

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Double ellipseA = Double.parseDouble(eA.getText().toString());
                Double ellipseB = Double.parseDouble(eB.getText().toString());
                Double hyperbolaA = Double.parseDouble(hA.getText().toString());
                Double hyperbolaB = Double.parseDouble(hB.getText().toString());
                Double parabolaA = Double.parseDouble(pA.getText().toString());

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);

                intent.putExtra(type, text);
                intent.putExtra(e_A, ellipseA);
                intent.putExtra(e_B, ellipseB);
                intent.putExtra(h_A, hyperbolaA);
                intent.putExtra(h_B, hyperbolaB);
                intent.putExtra(p_A, parabolaA);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


}