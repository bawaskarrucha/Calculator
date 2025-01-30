package com.example.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;
    private Button btnClear, btnBackspace, btnPercentage, btnDivide, btnMultiply, btnSubtract, btnAdd, btnEquals, btnDot;
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;

    private String currentInput = "";
    private String operator = "";
    private double firstNumber = 0;
    private boolean isNewInput = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initializeViews();
        setButtonListeners();
    }

    private void initializeViews() {
        tvResult = findViewById(R.id.tvResult);

        // Number Buttons
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        // Operator Buttons
        btnAdd = findViewById(R.id.btnAdd);
        btnSubtract = findViewById(R.id.btnSubtract);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);
        btnPercentage = findViewById(R.id.btnPercentage);

        // Function Buttons
        btnEquals = findViewById(R.id.btnEquals);
        btnClear = findViewById(R.id.btnClear);
        btnBackspace = findViewById(R.id.btnBackspace);
        btnDot = findViewById(R.id.btnDot);
    }

    private void setButtonListeners() {
        // Number Button Clicks
        View.OnClickListener numberClickListener = view -> {
            if (isNewInput) {
                currentInput = "";
                isNewInput = false;
            }
            currentInput += ((Button) view).getText().toString();
            tvResult.setText(currentInput);
        };

        btn0.setOnClickListener(numberClickListener);
        btn1.setOnClickListener(numberClickListener);
        btn2.setOnClickListener(numberClickListener);
        btn3.setOnClickListener(numberClickListener);
        btn4.setOnClickListener(numberClickListener);
        btn5.setOnClickListener(numberClickListener);
        btn6.setOnClickListener(numberClickListener);
        btn7.setOnClickListener(numberClickListener);
        btn8.setOnClickListener(numberClickListener);
        btn9.setOnClickListener(numberClickListener);

        // Operator Button Clicks
        View.OnClickListener operatorClickListener = view -> {
            if (!currentInput.isEmpty()) {
                firstNumber = Double.parseDouble(currentInput);
                operator = ((Button) view).getText().toString();
                isNewInput = true;
            }
        };

        btnAdd.setOnClickListener(operatorClickListener);
        btnSubtract.setOnClickListener(operatorClickListener);
        btnMultiply.setOnClickListener(operatorClickListener);
        btnDivide.setOnClickListener(operatorClickListener);
        btnPercentage.setOnClickListener(operatorClickListener);

        // Equals Button
        btnEquals.setOnClickListener(view -> {
            if (!currentInput.isEmpty() && !operator.isEmpty()) {
                double secondNumber = Double.parseDouble(currentInput);
                double result = 0;

                switch (operator) {
                    case "+": result = firstNumber + secondNumber; break;
                    case "-": result = firstNumber - secondNumber; break;
                    case "×": result = firstNumber * secondNumber; break;
                    case "÷": result = (secondNumber != 0) ? firstNumber / secondNumber : 0; break;
                    case "%": result = firstNumber % secondNumber; break;
                }

                tvResult.setText(String.valueOf(result));
                currentInput = String.valueOf(result);
                operator = "";
                isNewInput = true;
            }
        });

        // Clear Button
        btnClear.setOnClickListener(view -> {
            currentInput = "";
            operator = "";
            firstNumber = 0;
            tvResult.setText("0");
            isNewInput = true;
        });

        // Backspace Button
        btnBackspace.setOnClickListener(view -> {
            if (!currentInput.isEmpty()) {
                currentInput = currentInput.substring(0, currentInput.length() - 1);
                tvResult.setText(currentInput.isEmpty() ? "0" : currentInput);
            }
        });

        // Dot Button
        btnDot.setOnClickListener(view -> {
            if (!currentInput.contains(".")) {
                currentInput += ".";
                tvResult.setText(currentInput);
            }
        });
    }
}
