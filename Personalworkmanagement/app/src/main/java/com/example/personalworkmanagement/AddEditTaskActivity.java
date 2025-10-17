package com.example.personalworkmanagement;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class AddEditTaskActivity extends AppCompatActivity {
    private EditText edtTitle, edtDesc, edtDate;
    private Button btnSave;
    private int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);

        edtTitle = findViewById(R.id.edtTitle);
        edtDesc = findViewById(R.id.edtDesc);
        edtDate = findViewById(R.id.edtDate);
        btnSave = findViewById(R.id.btnSave);

        Intent intent = getIntent();
        if (intent.hasExtra("task")) {
            Task task = (Task) intent.getSerializableExtra("task");
            position = intent.getIntExtra("position", -1);
            edtTitle.setText(task.getTitle());
            edtDesc.setText(task.getDescription());
            edtDate.setText(task.getDueDate());
        }

        btnSave.setOnClickListener(v -> saveTask());
    }

    private void saveTask() {
        String title = edtTitle.getText().toString();
        String desc = edtDesc.getText().toString();
        String date = edtDate.getText().toString();

        Task task = new Task(title, desc, date);

        Intent result = new Intent();
        result.putExtra("task", task);
        result.putExtra("position", position);
        setResult(RESULT_OK, result);
        scheduleNotification(title);
        finish();
    }

    private void scheduleNotification(String title) {
        Intent intent = new Intent(this, ReminderReceiver.class);
        intent.putExtra("taskTitle", title);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        long time = System.currentTimeMillis() + 10000; // Test: 10s sau sẽ báo
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, pendingIntent);
    }
}
