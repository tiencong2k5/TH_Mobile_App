package com.example.personalworkmanagement;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private List<Task> taskList = new ArrayList<>();
    private TaskStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        storage = new TaskStorage(this);
        taskList = storage.loadTasks();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new TaskAdapter(taskList, pos -> editTask(pos));
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = findViewById(R.id.fabAdd);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(this, AddEditTaskActivity.class);
            startActivityForResult(intent, 1);
        });
    }

    private void editTask(int pos) {
        Intent intent = new Intent(this, AddEditTaskActivity.class);
        intent.putExtra("task", taskList.get(pos));
        intent.putExtra("position", pos);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int req, int res, Intent data) {
        super.onActivityResult(req, res, data);
        if (res == RESULT_OK && data != null) {
            Task task = (Task) data.getSerializableExtra("task");
            if (req == 1) taskList.add(task);
            else if (req == 2) {
                int pos = data.getIntExtra("position", -1);
                if (pos >= 0) taskList.set(pos, task);
            }
            storage.saveTasks(taskList);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Lưu công việc thành công!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId(); // Get the ID once

        if (itemId == R.id.menu_add) {
            startActivityForResult(new Intent(this, AddEditTaskActivity.class), 1);
            return true;
        } else if (itemId == R.id.menu_clear) {
            taskList.clear();
            storage.saveTasks(taskList);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Đã xóa tất cả công việc", Toast.LENGTH_SHORT).show();
            return true;
        } else if (itemId == R.id.menu_about) {
            startActivity(new Intent(this, AboutActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}