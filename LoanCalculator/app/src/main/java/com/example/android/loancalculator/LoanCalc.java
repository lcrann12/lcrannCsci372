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

        resultView = findViewById(R.id.resultView);
        resultView.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        String v = resultView.getText().toString();
                        int progress = Integer.parseInt(v);
                        monthSelector.setProgress(progress);
                        return false;
                    }
                }
        );
        barLabel = findViewById(R.id.barLabel);
        monthSelector = findViewById(R.id.monthSelector);
        monthSelector.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                        monthSelector.setProgress(progress);
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
        int p = monthSelector.getProgress();
        double mpResult;
        String costInput = costAmount.getText().toString();
        int cI = Integer.parseInt(costInput); //cost of car
        String downInput = downAmount.getText().toString();
        int dI = Integer.parseInt(downInput);     //down payment
        String aprInput = aprAmount.getText().toString();
        double aI = Double.parseDouble(aprInput);  //apr
        double mr = (aI/12);
        if(loanButton.isChecked()){
            mpResult = ((aI/100)/12)*((cI-dI)/(1-(1+Math.pow(mr, -p))));
        }else{
            mpResult = ((aI/100)/12)*(((cI/3)-dI)/(1-(1+Math.pow(mr, -36))));
        }
        resultView.setText("$"+mpResult+"");
    }
}
