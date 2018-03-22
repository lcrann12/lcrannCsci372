package com.example.android.tipcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class TipCalc extends AppCompatActivity {

    private EditText billAmount;
    private EditText dinerAmount;
    private TextView resultView;
    private SeekBar barOfSeeks;
    private CheckBox splitText;
    private TextView barLabel;
    private TextView amountOwed;
    private RelativeLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calc);
        amountOwed = findViewById(R.id.amountOwed);
        barLabel = findViewById(R.id.barLabel);
        splitText=findViewById(R.id.splitText);
        billAmount =findViewById(R.id.billAmount);
        resultView = findViewById(R.id.resultView);
        resultView.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        String bv = resultView.getText().toString();
                        int progress = Integer.parseInt(bv);
                        barOfSeeks.setProgress(progress);
                        return false;
                    }
                }
        );
        dinerAmount = findViewById(R.id.dinerAmount);
        layout = findViewById(R.id.layout);
        //layout.setBackgroundColor(0xEEFFEE00);
        barOfSeeks = findViewById(R.id.barOfSeeks);
        barOfSeeks.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar barOfSeeks, int progress, boolean b) {
                        barOfSeeks.setProgress(progress);
                        barLabel.setText(String.valueOf(""+progress+"%"));

                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar barOfSeeks) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar barOfSeeks) {

                    }
                }
        );
    }

    public void buttonPressed(View v){
        double l = barOfSeeks.getProgress();
        double totalTip;
        double totalBill;
        String billInput = billAmount.getText().toString();
        double b = Double.parseDouble(billInput);
        String dinerInput = dinerAmount.getText().toString();
        double d = Double.parseDouble(dinerInput);

        if(splitText.isChecked()){
            totalTip = (b*(l/100))/d;
            totalBill = (b/d)+totalTip;

        }else{
            totalTip =(b*(l/100));
            totalBill =b+totalTip;

        }
        resultView.setText(totalTip+"");
        amountOwed.setText(totalBill+"");

    }
    public void extraButtonPressed(View v){
        Intent i = new Intent(this, ColorActivity.class);
        startActivityForResult(i, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        int color = data.getIntExtra("COLOR", 0xFFFF0000);
        layout.setBackgroundColor(color);
    }


}
