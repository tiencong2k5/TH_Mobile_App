package com.example.personalworkmanagement;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TaskStorage {
    private static final String PREFS_NAME = "tasks_prefs";
    private static final String KEY_TASKS = "task_list";
    private SharedPreferences prefs;
    private Gson gson = new Gson();

    public TaskStorage(Context context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveTasks(List<Task> tasks) {
        String json = gson.toJson(tasks);
        prefs.edit().putString(KEY_TASKS, json).apply();
    }

    public List<Task> loadTasks() {
        String json = prefs.getString(KEY_TASKS, null);
        if (json == null) return new ArrayList<>();
        Type type = new TypeToken<List<Task>>(){}.getType();
        return gson.fromJson(json, type);
    }
}
