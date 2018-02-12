package com.example.lenny.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class tipCalculator extends AppCompatActivity {
    private TextView tip;
    private TextView bill;
    private TextView barLabel;
    private EditText billAmount;
    private EditText dinerAmount;
    private SeekBar barOfSeeks;
    private CheckBox splitBill;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);
        tip = findViewById(R.id.tip);
        bill = findViewById(R.id.bill);
        barLabel = findViewById(R.id.barLabel);
        billAmount = findViewById(R.id.billAmount);
        dinerAmount = findViewById(R.id.dinerAmount);
        splitBill = findViewById(R.id.splitBill);
        barOfSeeks = findViewById(R.id.barOfSeeks);
        barOfSeeks.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                        barOfSeeks.setProgress(progress);
                        barLabel.setText(""+progress+"%");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );
    }

    public void buttonPress(View v) {
        double tipLabel;
        double totalBill;
        double resultBill = Double.parseDouble(billAmount.getText().toString());
        double resultDiner = Double.parseDouble(dinerAmount.getText().toString());
        double resultTip = Double.parseDouble(barLabel.getText().toString());
        if (splitBill.isChecked()) {
            tipLabel = (resultBill / resultDiner) * (resultTip / 100);
            totalBill = tipLabel+(resultBill/resultDiner);
        }else{
            tipLabel = resultBill*(resultTip/100);
            totalBill = resultBill+tipLabel;
        }
        tip.setText(tipLabel+"");
        bill.setText(totalBill+"");
    }
}
