package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TextView;

import org.w3c.dom.Text;

//ВЫЗЫВАТЬ НАЖАТИЕ КНОПКИ ЦИФРЫ ОТ TextView КОТОРЫЙ МЫ ИСПОЛЬЗУЕМ И ЕГО ЖЕ И МЕНЯТЬ
public class MainActivity extends AppCompatActivity {
    protected TextView inputNumber1, inputNumber2, result, inputSign;
    protected Button digitButtons[] = new Button[10];
    protected Button signButtons[] = new Button[4];
    protected Button equalButton, eraseAllButton;
    protected ImageButton eraseLastButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = (TextView)findViewById(R.id.result);
        inputNumber1 = (TextView)findViewById(R.id.inputNumber1);
        inputNumber2 = (TextView)findViewById(R.id.inputNumber2);
        inputSign = (TextView)findViewById(R.id.inputSign);


        digitButtons[0] = (Button)findViewById(R.id.digit0);
        digitButtons[1] = (Button)findViewById(R.id.digit1);
        digitButtons[2] = (Button)findViewById(R.id.digit2);
        digitButtons[3] = (Button)findViewById(R.id.digit3);
        digitButtons[4] = (Button)findViewById(R.id.digit4);
        digitButtons[5] = (Button)findViewById(R.id.digit5);
        digitButtons[6] = (Button)findViewById(R.id.digit6);
        digitButtons[7] = (Button)findViewById(R.id.digit7);
        digitButtons[8] = (Button)findViewById(R.id.digit8);
        digitButtons[9] = (Button)findViewById(R.id.digit9);

        signButtons[0] = (Button)findViewById(R.id.subtract);
        signButtons[1] = (Button)findViewById(R.id.add);
        signButtons[2] = (Button)findViewById(R.id.divide);
        signButtons[3] = (Button)findViewById(R.id.multiply);

        equalButton = (Button)findViewById(R.id.equal);
        eraseAllButton = (Button)findViewById(R.id.eraseAll);
        eraseLastButton = (ImageButton)findViewById(R.id.eraseLast);

        inputNumber1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eraseLastButton.setTag("inputNumber1");
                eraseAllButton.setTag("inputNumber1");
                for(int i=0;i<10;++i){
                    digitButtons[i].setOnClickListener(digitButtonListener1);
                }
                eraseAllButton.setOnClickListener(eraseButtonListener);
                eraseLastButton.setOnClickListener(eraseButtonListener);
            }
        });
        inputNumber2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eraseLastButton.setTag("inputNumber2");
                eraseAllButton.setTag("inputNumber2");
                for(int i=0;i<10;++i){
                    digitButtons[i].setOnClickListener(digitButtonListener2);
                }
                eraseAllButton.setOnClickListener(eraseButtonListener);
                eraseLastButton.setOnClickListener(eraseButtonListener);
            }
        });

        for(int i=0;i<4;++i) {
            signButtons[i].setOnClickListener(signsButtonListener);
        }

        equalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
    }
    private void calculate(){
        String number1String = inputNumber1.getText().toString();
        String number2String = inputNumber2.getText().toString();
        if(number1String=="" || number2String == ""){
            return;
        }
        int number1 = Integer.parseInt(number1String);
        int number2 = Integer.parseInt(number2String);
        String sign = inputSign.getText().toString();
        switch(sign){
            case "+": result.setText(number1+number2+"");break;
            case "-": result.setText(number1-number2+"");break;
            case "/": result.setText(number1/number2+"");break;
            case "*": result.setText(number1*number2+"");break;
        }
    }
    private View.OnClickListener eraseButtonListener = new View.OnClickListener(){
        @Override
        public void onClick(View v){
            TextView numberButton;
            switch (v.getTag().toString()){
                case "inputNumber1": numberButton = inputNumber1; break;
                case "inputNumber2": numberButton = inputNumber2; break;
                default: return;
            };
            String number = numberButton.getText().toString();
            if(number.length()==0){
                return;
            }
            if(v.getId() == R.id.eraseAll){
                numberButton.setText("");
                result.setText("");
            }
            else if(v.getId() == R.id.eraseLast) {
                number = number.substring(0, number.length() - 1);
                numberButton.setText(number);
                if (numberButton.getText().toString().length() == 0) {
                    result.setText("");
                }
                else calculate();
            }
        };
    };

    private View.OnClickListener signsButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.add: inputSign.setText("+");break;
                case R.id.subtract: inputSign.setText("-");break;
                case R.id.divide: inputSign.setText("/");break;
                case R.id.multiply: inputSign.setText("*");break;
            }
            calculate();
        }
    };
    private View.OnClickListener digitButtonListener1 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String number = inputNumber1.getText().toString();
            if(number.length()<4) {
                switch (v.getId()) {
                    case R.id.digit0:
                        number += "0";
                        inputNumber1.setText(number);
                        break;
                    case R.id.digit1:
                        number += "1";
                        inputNumber1.setText(number);
                        break;
                    case R.id.digit2:
                        number += "2";
                        inputNumber1.setText(number);
                        break;
                    case R.id.digit3:
                        number += "3";
                        inputNumber1.setText(number);
                        break;
                    case R.id.digit4:
                        number += "4";
                        inputNumber1.setText(number);
                        break;
                    case R.id.digit5:
                        number += "5";
                        inputNumber1.setText(number);
                        break;
                    case R.id.digit6:
                        number += "6";
                        inputNumber1.setText(number);
                        break;
                    case R.id.digit7:
                        number += "7";
                        inputNumber1.setText(number);
                        break;
                    case R.id.digit8:
                        number += "8";
                        inputNumber1.setText(number);
                        break;
                    case R.id.digit9:
                        number += "9";
                        inputNumber1.setText(number);
                        break;
                }
                ;
                calculate();
            }
        };
    };
    private View.OnClickListener digitButtonListener2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String number = inputNumber2.getText().toString();
            if(number.length()<4) {
                switch (v.getId()) {
                    case R.id.digit0:
                        number += "0";
                        inputNumber2.setText(number);
                        break;
                    case R.id.digit1:
                        number += "1";
                        inputNumber2.setText(number);
                        break;
                    case R.id.digit2:
                        number += "2";
                        inputNumber2.setText(number);
                        break;
                    case R.id.digit3:
                        number += "3";
                        inputNumber2.setText(number);
                        break;
                    case R.id.digit4:
                        number += "4";
                        inputNumber2.setText(number);
                        break;
                    case R.id.digit5:
                        number += "5";
                        inputNumber2.setText(number);
                        break;
                    case R.id.digit6:
                        number += "6";
                        inputNumber2.setText(number);
                        break;
                    case R.id.digit7:
                        number += "7";
                        inputNumber2.setText(number);
                        break;
                    case R.id.digit8:
                        number += "8";
                        inputNumber2.setText(number);
                        break;
                    case R.id.digit9:
                        number += "9";
                        inputNumber2.setText(number);
                        break;
                }
                ;
                calculate();
            }
        };
    };
}