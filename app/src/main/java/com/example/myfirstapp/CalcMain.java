package com.example.myfirstapp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class CalcMain extends AppCompatActivity {
    private TextView display;


    private Button btnNum1;
    private Button btnNum2;
    private Button btnNum3;
    private Button btnNum4;
    private Button btnNum5;
    private Button btnNum6;
    private Button btnNum7;
    private Button btnNum8;
    private Button btnNum9;
    private Button btnNum0;
    private Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc_main);


        display = findViewById(R.id.display);
        btnNum1 = findViewById(R.id.btnNum1);
        btnNum2 = findViewById(R.id.btnNum2);
        btnNum3 = findViewById(R.id.btnNum3);
        btnNum4 = findViewById(R.id.btnNum4);
        btnNum5 = findViewById(R.id.btnNum5);
        btnNum6 = findViewById(R.id.btnNum6);
        btnNum7 = findViewById(R.id.btnNum7);
        btnNum8 = findViewById(R.id.btnNum8);
        btnNum9 = findViewById(R.id.btnNum9);
        btnNum0 = findViewById(R.id.btnZero);

        btnClear = findViewById(R.id.btnClear);


        btnNum1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = display.getText().toString();

                if (currentText.equals("0")) {
                    display.setText("1");
                } else {

                    display.setText(currentText + "1");
                }
            }
        });

        btnNum2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = display.getText().toString();

                if (currentText.equals("0")) {
                    display.setText("2");
                } else {

                    display.setText(currentText + "2");
                }
            }
        });

        btnNum3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = display.getText().toString();

                if (currentText.equals("0")) {
                    display.setText("3");
                } else {

                    display.setText(currentText + "3");
                }
            }
        });

        btnNum4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = display.getText().toString();

                if (currentText.equals("0")) {
                    display.setText("4");
                } else {

                    display.setText(currentText + "4");
                }
            }
        });

        btnNum5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = display.getText().toString();

                if (currentText.equals("0")) {
                    display.setText("5");
                } else {

                    display.setText(currentText + "5");
                }
            }
        });

        btnNum6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = display.getText().toString();

                if (currentText.equals("0")) {
                    display.setText("6");
                } else {

                    display.setText(currentText + "6");
                }
            }
        });

        btnNum7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = display.getText().toString();

                if (currentText.equals("0")) {
                    display.setText("7");
                } else {

                    display.setText(currentText + "7");
                }
            }
        });

        btnNum8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = display.getText().toString();

                if (currentText.equals("0")) {
                    display.setText("8");
                } else {

                    display.setText(currentText + "8");
                }
            }
        });

        btnNum9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = display.getText().toString();

                if (currentText.equals("0")) {
                    display.setText("9");
                } else {

                    display.setText(currentText + "9");
                }
            }
        });

        btnNum0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentText = display.getText().toString();

                if (currentText.equals("0")) {
                    display.setText("0");
                } else {

                    display.setText(currentText + "0");
                }
            }
        });





        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                display.setText("0");
            }
        });
    }




}
