package com.eph.datetimecalculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDateTime;

public class MainActivity extends AppCompatActivity {
    private Button calculate;
    private EditText s_year,s_month,s_day,s_hour,s_minute;
    private EditText e_year,e_month,e_day,e_hour,e_minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        s_year = findViewById(R.id.et_s_year);
        s_month = findViewById(R.id.et_s_month);
        s_day = findViewById(R.id.et_s_day);
        s_hour = findViewById(R.id.et_s_hour);
        s_minute = findViewById(R.id.et_s_minute);

        e_year = findViewById(R.id.et_e_year);
        e_month = findViewById(R.id.et_e_month);
        e_day = findViewById(R.id.et_e_day);
        e_hour = findViewById(R.id.et_e_hour);
        e_minute = findViewById(R.id.et_e_minute);

        calculate = findViewById(R.id.btn_calculate);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    int start_year = Integer.parseInt(s_year.getText().toString());
                    int start_month = Integer.parseInt(s_month.getText().toString());
                    int start_day = Integer.parseInt(s_day.getText().toString());
                    int start_hour = Integer.parseInt(s_hour.getText().toString());
                    int start_minute = Integer.parseInt(s_minute.getText().toString());

                    int end_year = Integer.parseInt(e_year.getText().toString());
                    int end_month = Integer.parseInt(e_month.getText().toString());
                    int end_day = Integer.parseInt(e_day.getText().toString());
                    int end_hour = Integer.parseInt(e_hour.getText().toString());
                    int end_minute = Integer.parseInt(e_minute.getText().toString());

                    try{
                        CalculatorLogic calculatorLogic = new CalculatorLogic();
                        LocalDateTime start_date = calculatorLogic.buildLocalDateTime(start_year,start_month,start_day,start_hour,start_minute);
                        LocalDateTime end_date = calculatorLogic.buildLocalDateTime(end_year,end_month,end_day,end_hour,end_minute);
                        DateDto dateDto = calculatorLogic.calculateDateDifference(start_date,end_date);
                        String response = dateDtoToString(dateDto);

                        AlertDialog.Builder alerter = new AlertDialog.Builder(MainActivity.this);
                        alerter.setTitle("Result!");
                        alerter.setMessage(response);
                        alerter.show();
                    }catch (Exception ex){
                        AlertDialog.Builder alerter = new AlertDialog.Builder(MainActivity.this);
                        alerter.setTitle("Error!");
                        alerter.setMessage("Service failing. \nPlease check that your input values correct. If problem still exists, please contact developer!");
                        alerter.show();
                    }
                }catch(NumberFormatException numberFormatException){
                    Toast.makeText(MainActivity.this, "Empty field or bad field value detected",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String dateDtoToString(DateDto dateDto){
        return String.format("%s year(s), %s month(s), %s day(s), %s hour(s) and %s minute(s).",
                dateDto.getYear(),dateDto.getMonth(),dateDto.getDay(),dateDto.getHour(),dateDto.getMinute());
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        AlertDialog.Builder alerter = new AlertDialog.Builder(this);
        alerter.setTitle("Warning!");
        alerter.setMessage("Are you sure to close this application?");
        alerter.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //do nothing
            }
        });
        alerter.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.exit(1);
            }
        });
        alerter.show();
    }
}