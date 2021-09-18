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

public class MainActivity extends AppCompatActivity {

    EditText billTotal;

    RadioGroup tipGroup;
    RadioButton tipButton;
    TextView tipAmount;
    TextView tipTotal;

    EditText peopleAmt;
    TextView personTotal;
    TextView overage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        billTotal = findViewById(R.id.editTextNumberDecimal);
        tipGroup = findViewById(R.id.radioGroupTip);
        tipAmount = findViewById(R.id.textTipAmountCalc);
        tipTotal = findViewById(R.id.textTipTotalCalc);

        Button tip12 = findViewById(R.id.radioButtonTipOne);
        tip12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int radioId = tipGroup.getCheckedRadioButtonId();
                tipButton = findViewById(radioId);
                double percent = Double.parseDouble(tipButton.getText().toString().substring(0, tipButton.getText().toString().indexOf('%')))/100.00;
                Log.d("debug: ", Double.toString(percent));
                double tip = calculateTip(percent);
                tipAmount.setText(Double.toString(tip));
            }
        });
    }
    public double calculateTip(double percent){
        double bill = Double.parseDouble(billTotal.getText().toString());
        double tip = bill*percent;

        return tip;
    }


    public void calculateTotal(){

    }
}