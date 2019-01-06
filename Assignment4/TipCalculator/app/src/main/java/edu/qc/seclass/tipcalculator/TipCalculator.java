package edu.qc.seclass.tipcalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.*;
import java.math.*;

public class TipCalculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calculator);

        Button btn = findViewById(R.id.buttonCompute);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    EditText et = (EditText) findViewById(R.id.checkAmountValue);
                    String s = et.getText().toString();
                    double checkAmount = Double.parseDouble(s);

                    et = (EditText) findViewById(R.id.partySizeValue);
                    s = et.getText().toString();
                    double size = Double.parseDouble(s);
                    if(size <= 0 || checkAmount < 0){
                        throw new Exception();
                    }
                    double t = checkAmount / size;

                    int fifteenTip = (int)Math.round(t * .15);
                    int twentyTip = (int) Math.round(t * .20);
                    int twentyfiveTip = (int)Math.round(t * .25);
                    int fifteenTotal = (int)Math.round(t * 1.15);
                    int twentyTotal = (int)Math.round(t * 1.20);
                    int twentyfiveTotal = (int)Math.round(t * 1.25);
                    TextView fifteenTipView = (TextView) findViewById(R.id.fifteenPercentTipValue);
                    fifteenTipView.setText(new Integer(fifteenTip).toString());

                    TextView twentyTipView = (TextView) findViewById(R.id.twentyPercentTipValue);
                    twentyTipView.setText(new Integer(twentyTip).toString());

                    TextView twentyfiveTipView = (TextView) findViewById(R.id.twentyfivePercentTipValue);
                    twentyfiveTipView.setText(new Integer(twentyfiveTip).toString());

                    TextView fifteenTotalView = (TextView) findViewById(R.id.fifteenPercentTotalValue);
                    fifteenTotalView.setText(new Integer(fifteenTotal).toString());

                    TextView twentyTotalView = (TextView) findViewById(R.id.twentyPercentTotalValue);
                    twentyTotalView.setText(new Integer(twentyTotal).toString());

                    TextView twentyfiveTotalView = (TextView) findViewById(R.id.twentyfivePercentTotalValue);
                    twentyfiveTotalView.setText(new Integer(twentyfiveTotal).toString());

                }
                catch(Exception e){
                    Toast.makeText(getApplicationContext(), "Empty or incorrect value(s)!", Toast.LENGTH_SHORT)
                            .show();
                }

            }
        });




//
//
    }


}
