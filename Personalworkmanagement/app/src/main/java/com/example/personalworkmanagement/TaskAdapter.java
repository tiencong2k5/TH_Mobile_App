package com.example.personalworkmanagement;

import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import android.widget.*;
import android.view.View;
import android.view.LayoutInflater;
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<Task> taskList;
    private OnTaskClickListener  listener;

    public interface OnTaskClickListener {
        void onTaskClick(int position);
    }

    public  TaskAdapter(List<Task> taskList, OnTaskClickListener  listener){
        this.taskList = taskList;
        this.listener = listener;

    }
    public static class TaskViewHolder extends RecyclerView.ViewHolder{
        TextView txtTitle, txtDate;
        public TaskViewHolder(View itemView, OnTaskClickListener listener) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDate = itemView.findViewById(R.id.txtDate);

            itemView.setOnClickListener(v -> {
                if (listener != null)
                    listener.onTaskClick(getAdapterPosition());
            });
        }
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_task, parent, false);
        return new TaskViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.txtTitle.setText(task.getTitle());
        holder.txtDate.setText(task.getDueDate());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}


