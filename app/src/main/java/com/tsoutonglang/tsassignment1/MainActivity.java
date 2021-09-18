package com.tsoutonglang.tsassignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    EditText billTotal;

    RadioGroup tipGroup;
    RadioButton tipButton;
    TextView tipAmount, tipTotal;

    EditText peopleAmt;
    Button peopleGo;
    TextView personTotal, overage;
    double total;

    Button clearAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billTotal = findViewById(R.id.editTextNumberDecimal);

        // user selects how much they wanna tip
        tipGroup = findViewById(R.id.radioGroupTip);
        tipAmount = findViewById(R.id.textTipAmountCalc);
        tipTotal = findViewById(R.id.textTipTotalCalc);

        Button tip12 = findViewById(R.id.radioButtonTipOne);
        tip12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId = tipGroup.getCheckedRadioButtonId();
                tipButton = findViewById(radioId);
                checkButton(tipButton);
            }
        });

        Button tip15 = findViewById(R.id.radioButtonradioButtonTipTwo);
        tip15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId = tipGroup.getCheckedRadioButtonId();
                tipButton = findViewById(radioId);
                checkButton(tipButton);
            }
        });

        Button tip18 = findViewById(R.id.radioButtonradioButtonTipThree);
        tip18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId = tipGroup.getCheckedRadioButtonId();
                tipButton = findViewById(radioId);
                checkButton(tipButton);
            }
        });

        Button tip20 = findViewById(R.id.radioButtonradioButtonTipFour);
        tip20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId = tipGroup.getCheckedRadioButtonId();
                tipButton = findViewById(radioId);
                checkButton(tipButton);
            }
        });

        peopleAmt = findViewById(R.id.editPeopleAmt);
        personTotal = findViewById(R.id.textPersonTotalCalc);
        overage = findViewById(R.id.textOverageCalc);
        peopleGo = findViewById(R.id.buttonGo);

        // go button
        peopleGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check if the user put the number of people
                // no input = 1 person
                int people;
                if (peopleAmt.getText().toString().equals("") || Integer.parseInt(String.valueOf(peopleAmt.getText().toString()))<1) {
                    people = 1;
                } else {
                    people = Integer.parseInt(String.valueOf(peopleAmt.getText().toString()));
                    Log.d("debug - amount of people", Integer.toString(people));
                }

                calculateSplit(people);
            }
        });

        // TODO: clear button
        clearAll = findViewById(R.id.buttonClear);
        clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void checkButton(RadioButton tipButton){
        // check if the user inputted nothing or a value
        // no input bill is set to zero and selection is cleared
        double bill;
        if (billTotal.getText().toString().equals("")) {
            bill = 0.00;
            tipGroup.clearCheck();
        } else {
            bill = Double.parseDouble(String.valueOf(billTotal.getText().toString()));
        }

        // get the percent tip the user selected
        int percent = Integer.parseInt(tipButton.getText().toString().substring(0, tipButton.getText().toString().indexOf('%')));

        // calculate how much tip is
        double tip = calculateTip(percent, bill);

        // calculate the bill total with the tip
        calculateTotal(tip, bill);
    }

    public double calculateTip(double percent, double bill){
        double tip = Math.round(bill*100.0*percent/100.00)/100.0;
        tipAmount.setText("$" + String.format("%1$,.2f", tip));
        return tip;
    }

    public void calculateTotal(double tip, double bill){
        total = bill+tip;
        tipTotal.setText("$" + String.format("%1$,.2f", total));
    }

    public void calculateSplit(int people){
        double split = Math.ceil(total*100.0/people)/100.0;
        Log.d("debug - split amount", Double.toString(split));

        // display split amount
        personTotal.setText("$" + String.format("%1$,.2f", split));
        calculateOverage(split, people);
    }

    public void calculateOverage(double split, int people){
        double splitTotal = split*people;
        Log.d("debug - split total", Double.toString(splitTotal));
        double over = ((splitTotal*100)-(total*100))/100.00;
        Log.d("debug - split total", Double.toString(over));
        overage.setText("$" + String.format("%1$,.2f", over));
    }
}