package com.example.minicalculator;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;
import android.widget.*;
public class MainActivity extends AppCompatActivity {
    EditText etNumber1, etNumber2;
    Button btnAdd, btnSub, btnMul, btnDiv;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumber1 = findViewById(R.id.etNumber1);
        etNumber2 = findViewById(R.id.etNumber2);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        tvResult = findViewById(R.id.tvResult);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = etNumber1.getText().toString().trim();
                String s2 = etNumber2.getText().toString().trim();

                if (s1.isEmpty() || s2.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập đủ hai số", Toast.LENGTH_SHORT).show();
                    return;
                }

                double num1 = Double.parseDouble(s1);
                double num2 = Double.parseDouble(s2);
                double result = 0;

                int viewId = v.getId();

                if(viewId == R.id.btnAdd) {
                    result = num1 + num2;
                } else if ( viewId == R.id.btnSub) {
                    result = num1 - num2;
                } else if ( viewId == R.id.btnMul) {
                    result = num1 * num2;
                } else if (viewId == R.id.btnDiv) {
                    if ( num2 == 0){
                        Toast.makeText(MainActivity.this, "Không thể chia cho 0", Toast.LENGTH_SHORT).show();
                        return ;
                    }
                    result = num1 / num2;
                }

                tvResult.setText("Kết quả: " + result);
            }
        };

        btnAdd.setOnClickListener(listener);
        btnSub.setOnClickListener(listener);
        btnMul.setOnClickListener(listener);
        btnDiv.setOnClickListener(listener);
    }
}