package com.example.android.loancalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class LoanCalc extends AppCompatActivity {
    private EditText costAmount;
    private EditText downAmount;
    private EditText aprAmount;

    private TextView resultView;
    private TextView barLabel;

    private RadioButton loanButton;
    private RadioButton leaseButton;

    private SeekBar monthSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_calc);

        costAmount = findViewById(R.id.costAmount);
        downAmount = findViewById(R.id.downAmount);
        aprAmount = findViewById(R.id.aprAmount);

        loanButton = findViewById(R.id.loanButton);
        leaseButton = findViewById(R.id.leaseButton);

        resultView = findViewById(R.id.resultView);
        barLabel = findViewById(R.id.barLabel);

        monthSelector = findViewById(R.id.monthSelector);
        monthSelector.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                        seekBar.setProgress(progress);
                        barLabel.setText(String.valueOf("Length of Loan: "+progress+""));
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
    public void buttonPressed(View v){
        double p = monthSelector.getProgress();
        String costInput = costAmount.getText().toString();
        double cI = Double.parseDouble(costInput); //cost of car
        String downInput = downAmount.getText().toString();
        double dI = Double.parseDouble(downInput);     //down payment
        String aprInput = aprAmount.getText().toString();
        double aI = Double.parseDouble(aprInput);  //apr
        double mpResult;
        double mr = ((aI/100)/12);
        double L = cI-dI;

        if(loanButton.isChecked()){
            mpResult = mr*L/(1-(Math.pow(1+mr, -p)));
            resultView.setText("$"+(String.format("%.2f", mpResult)+""));

        }else if (leaseButton.isChecked()){
            mpResult = mr*(L/3)/(1-(Math.pow(1+mr, -36)));
            resultView.setText("$"+(String.format("%.2f", mpResult)+""));

        }
    }
}
