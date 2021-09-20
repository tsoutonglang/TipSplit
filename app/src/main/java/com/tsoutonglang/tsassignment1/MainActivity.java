package com.tsoutonglang.tsassignment1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText billTotal;

    RadioGroup tipGroup;
    RadioButton tipButton;
    double tip = 0;
    TextView tipAmount, tipTotal;
    double total = 0;

    EditText peopleAmt;
    Button peopleGo;
    TextView personTotal, overage;
    double split = 0;
    double over = 0;

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

        tipAmount.setText(getString(R.string.tip_amount, tip));
        tipTotal.setText(getString(R.string.total_with_tip, total));

        Button tip12 = findViewById(R.id.radioButtonTipOne);
        tip12.setOnClickListener(view -> {
            int radioId = tipGroup.getCheckedRadioButtonId();
            tipButton = findViewById(radioId);
            checkButton(tipButton);
        });

        Button tip15 = findViewById(R.id.radioButtonradioButtonTipTwo);
        tip15.setOnClickListener(view -> {
            int radioId = tipGroup.getCheckedRadioButtonId();
            tipButton = findViewById(radioId);
            checkButton(tipButton);
        });

        Button tip18 = findViewById(R.id.radioButtonradioButtonTipThree);
        tip18.setOnClickListener(view -> {
            int radioId = tipGroup.getCheckedRadioButtonId();
            tipButton = findViewById(radioId);
            checkButton(tipButton);
        });

        Button tip20 = findViewById(R.id.radioButtonradioButtonTipFour);
        tip20.setOnClickListener(view -> {
            int radioId = tipGroup.getCheckedRadioButtonId();
            tipButton = findViewById(radioId);
            checkButton(tipButton);
        });

        peopleAmt = findViewById(R.id.editPeopleAmt);
        personTotal = findViewById(R.id.textPersonTotalCalc);
        overage = findViewById(R.id.textOverageCalc);
        peopleGo = findViewById(R.id.buttonGo);

        personTotal.setText(getString(R.string.total_per_person, split));
        overage.setText(getString(R.string.overage, over));

        // go button
        peopleGo.setOnClickListener(view -> {
            // check if the user put the number of people
            // no input = 1 person
            int people;
            if (peopleAmt.getText().toString().equals("") || Integer.parseInt(peopleAmt.getText().toString())<1) {
                people = 1;
            } else {
                people = Integer.parseInt(peopleAmt.getText().toString());
            }

            calculateSplit(people);
        });

        clearAll = findViewById(R.id.buttonClear);
        clearAll.setOnClickListener(view -> {
            billTotal.setText("");
            tipGroup.clearCheck();
            tip = 0;
            tipAmount.setText(getString(R.string.tip_amount, tip));
            total = 0;
            tipTotal.setText(getString(R.string.total_with_tip, total));
            peopleAmt.setText("");
            split = 0;
            personTotal.setText(getString(R.string.total_per_person, split));
            over = 0;
            overage.setText(getString(R.string.overage, over));
        });

    }

    public void checkButton(RadioButton tipButton){
        // check if the user inputted nothing or a value
        // no input bill is set to zero and selection is cleared
        double bill = 0;
        if (billTotal.getText().toString().equals("")) {
            tipGroup.clearCheck();
        } else {
            bill = (Double.parseDouble(billTotal.getText().toString()));
        }

        // get the percent tip the user selected
        int percent = Integer.parseInt(tipButton.getText().toString().substring(0, tipButton.getText().toString().indexOf('%')));

        // calculate how much tip is
        tip = calculateTip(percent, bill);

        // calculate the bill total with the tip
        calculateTotal(tip, bill);
    }

    public double calculateTip(int percent, double bill){
        tip = Math.ceil(bill*(percent/100.00)*100)/100;
        tipAmount.setText(getString(R.string.tip_amount, tip));
        return tip;
    }

    public void calculateTotal(double tip, double bill){
        total = bill+tip;
        tipTotal.setText(getString(R.string.total_with_tip, total));
    }

    public void calculateSplit(int people){
        split = Math.ceil(total*100.0/people)/100.0;

        // display split amount
        personTotal.setText(getString(R.string.total_per_person, split));
        calculateOverage(split, people);
    }

    public void calculateOverage(double split, int people){
        double splitTotal = split*people;
        over = ((splitTotal*100)-(total*100))/100.00;
        overage.setText(getString(R.string.overage, over));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("my lifecycle", " onSaveInstanceState called");

        outState.putDouble("tipAmount", tip);
        outState.putDouble("totalWithTip", total);
        outState.putDouble("peopleSplit", split);
        outState.putDouble("overageTotal", over);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("my lifecycle", "onRestoreInstanceState called");

        tip = savedInstanceState.getDouble("tipAmount");
        if (tip != 0){
            tipAmount.setText(getString(R.string.tip_amount, tip));
        }

        total = savedInstanceState.getDouble("totalWithTip");
        if (total != 0){
            tipTotal.setText(getString(R.string.total_with_tip, total));
        }

        split = savedInstanceState.getDouble("peopleSplit");
        if (split != 0){
            personTotal.setText(getString(R.string.total_per_person, split));
        }

        over = savedInstanceState.getDouble("overageTotal");
        if (over != 0){
            overage.setText(getString(R.string.overage, over));
        }
    }
}