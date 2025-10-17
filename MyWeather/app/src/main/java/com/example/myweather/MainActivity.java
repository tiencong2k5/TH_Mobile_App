package com.example.myweather;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText edtCity;
    Button btnViewWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtCity = findViewById(R.id.edtCity);
        btnViewWeather = findViewById(R.id.btnViewWeather);

        btnViewWeather.setOnClickListener(v -> {
            String city = edtCity.getText().toString().trim();
            if (city.isEmpty()) {
                edtCity.setError("Vui lòng nhập tên thành phố!");
                return;
            }

            Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
            intent.putExtra("city_name", city);
            startActivity(intent);
        });
    }

    // --- Menu ---
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_about) {
            new AlertDialog.Builder(this)
                    .setTitle("Giới thiệu ứng dụng")
                    .setMessage("Ứng dụng MyWeather - Xem thời tiết giả lập.\nTác giả: Tiến Công 🌤️")
                    .setPositiveButton("OK", null)
                    .show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}