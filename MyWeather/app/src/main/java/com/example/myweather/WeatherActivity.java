package com.example.myweather;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;
public class WeatherActivity extends AppCompatActivity {
    TextView txtCity, txtTemperature, txtHumidity, txtStatus;
    ImageView imgWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        txtCity = findViewById(R.id.txtCity);
        txtTemperature = findViewById(R.id.txtTemperature);
        txtHumidity = findViewById(R.id.txtHumidity);
        txtStatus = findViewById(R.id.txtStatus);
        imgWeather = findViewById(R.id.imgWeather);

        // Nhận dữ liệu từ Intent
        String city = getIntent().getStringExtra("city_name");
        txtCity.setText("Thời tiết tại " + city);

        // Giả lập dữ liệu ngẫu nhiên
        Random random = new Random();
        int temp = random.nextInt(15) + 20;   // 20–35 độ
        int humidity = random.nextInt(50) + 30; // 30–80%

        String[] statusList = {"Trời nắng", "Trời mưa", "Trời nhiều mây"};
        int idx = random.nextInt(statusList.length);
        String status = statusList[idx];

        txtTemperature.setText("Nhiệt độ: " + temp + "°C");
        txtHumidity.setText("Độ ẩm: " + humidity + "%");
        txtStatus.setText("Trạng thái: " + status);

        // Chọn ảnh phù hợp
        if (status.equals("Trời nắng")) {
            imgWeather.setImageResource(R.drawable.sunny);
        } else if (status.equals("Trời mưa")) {
            imgWeather.setImageResource(R.drawable.rainy);
        } else {
            imgWeather.setImageResource(R.drawable.cloudy);
        }
    }

}
