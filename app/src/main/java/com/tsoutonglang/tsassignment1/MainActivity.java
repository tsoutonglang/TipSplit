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
    TextView personTotal, overage;

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

        //TODO: calculate split amount number of people
    }

    public void checkButton(RadioButton tipButton){
        double bill;
        // check if the user inputted nothing or a value
        // if nothing: bill is set to zero and selection is cleared
        // else bill has a total
        if (billTotal.getText().toString().equals("")) {
            bill = 0.00;
            tipGroup.clearCheck();
        } else {
            bill = Double.parseDouble(String.valueOf(billTotal.getText().toString()));
        }
        Log.d("debug - bill", Double.toString(bill));

        // get the percent tip the user selected
        int percent = Integer.parseInt(tipButton.getText().toString().substring(0, tipButton.getText().toString().indexOf('%')));
        Log.d("debug - percent", Integer.toString(percent));

        // calculate how much tip is
        double tip = calculateTip(percent, bill);
        Log.d("debug - tip amount ", Double.toString(tip));

        // calculate the bill total with the tip
        double total = calculateTotal(tip, bill);
        Log.d("debug - total w/ tip", Double.toString(total));

        // display the tip calculated and the total with tip
        tipAmount.setText("$" + String.format("%1$,.2f", tip));
        tipTotal.setText("$" + String.format("%1$,.2f", total));
    }

    public double calculateTip(double percent, double bill){
        double tip = bill*percent/100.00;

        return tip;
    }

    public double calculateTotal(double tip, double bill){
        double total = bill+tip;
        return total;
    }
}