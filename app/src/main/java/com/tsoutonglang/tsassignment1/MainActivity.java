package com.tsoutonglang.tsassignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
    double tip;
    TextView tipAmount, tipTotal;
    double total;

    EditText peopleAmt;
    Button peopleGo;
    TextView personTotal, overage;
    double split;
    double over;

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

        clearAll = findViewById(R.id.buttonClear);
        clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                billTotal.setText("");
                tipGroup.clearCheck();

                tipAmount.setText("$0.00");
                tip = 0;
                tipTotal.setText("$0.00");
                total = 0;
                peopleAmt.setText("");
                personTotal.setText("$0.00");
                overage.setText("$0.00");

                Log.d("clear tip", Double.toString(tip));
                Log.d("clear total", Double.toString(total));
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
        tip = calculateTip(percent, bill);

        // calculate the bill total with the tip
        calculateTotal(tip, bill);
    }

    public double calculateTip(double percent, double bill){
        tip = Math.round(bill*100.0*percent/100.00)/100.0;
        tipAmount.setText("$" + String.format("%1$,.2f", tip));
        return tip;
    }

    public void calculateTotal(double tip, double bill){
        total = bill+tip;
        tipTotal.setText("$" + String.format("%1$,.2f", total));
    }

    public void calculateSplit(int people){
        split = Math.ceil(total*100.0/people)/100.0;

        // display split amount
        personTotal.setText("$" + String.format("%1$,.2f", split));
        calculateOverage(split, people);
    }

    public void calculateOverage(double split, int people){
        double splitTotal = split*people;
        over = ((splitTotal*100)-(total*100))/100.00;
        overage.setText("$" + String.format("%1$,.2f", over));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("my lifecycle", " onSaveInstanceState called");

        outState.putDouble("tipAmount", tip);
        outState.putDouble("totalWithTip", total);
        outState.putDouble("peopleSplit", split);
        outState.putDouble("overageTotal", over);

        Log.d("saved tip", String.valueOf(tip));
        Log.d("saved total", String.valueOf(total));
        Log.d("saved split", String.valueOf(split));
        Log.d("saved overage", String.valueOf(over));
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("my lifecycle", "onRestoreInstanceState called");

        tip = savedInstanceState.getDouble("tipAmount");
        if (tip != 0){
            tipAmount.setText("$" + String.format("%1$,.2f", tip));
        }

        total = savedInstanceState.getDouble("totalWithTip");
        if (total != 0){
            tipTotal.setText("$" + String.format("%1$,.2f", total));
        }

        split = savedInstanceState.getDouble("peopleSplit");
        if (split != 0){
            personTotal.setText("$" + String.format("%1$,.2f", split));
        }

        over = savedInstanceState.getDouble("overageTotal");
        if (over != 0){
            overage.setText("$" + String.format("%1$,.2f", over));
        }

        Log.d("restored tip", String.valueOf(tip));
        Log.d("restored total", String.valueOf(total));
        Log.d("restored split", String.valueOf(split));
        Log.d("restored overage", String.valueOf(over));
    }
}