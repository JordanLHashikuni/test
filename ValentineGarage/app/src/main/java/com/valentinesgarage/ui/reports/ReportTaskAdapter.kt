package com.valentinesgarage.ui.reports

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.valentinesgarage.data.model.Task
import com.valentinesgarage.databinding.ItemReportTaskBinding
import java.text.SimpleDateFormat
import java.util.Locale

class ReportTaskAdapter : ListAdapter<Task, ReportTaskAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemReportTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class ViewHolder(private val binding: ItemReportTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.tvTaskDesc.text = task.description
            binding.tvTaskNotes.text = if (task.notes.isNotBlank()) "Notes: ${task.notes}" else ""
            task.completedAt?.let {
                val sdf = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
                binding.tvTaskTime.text = "Completed: ${sdf.format(it.toDate())}"
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem.taskId == newItem.taskId
        override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem
    }
}
