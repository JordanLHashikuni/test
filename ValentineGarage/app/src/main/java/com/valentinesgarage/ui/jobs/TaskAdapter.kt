package com.valentinesgarage.ui.jobs

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.valentinesgarage.data.model.Task
import com.valentinesgarage.databinding.ItemTaskBinding

class TaskAdapter(
    private val onTaskCompleted: (taskId: String, notes: String) -> Unit
) : ListAdapter<Task, TaskAdapter.TaskViewHolder>(TaskDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(task: Task) {
            binding.tvDescription.text = task.description
            binding.cbDone.isChecked = task.isDone
            binding.cbDone.isEnabled = !task.isDone // can't un-tick

            if (task.isDone) {
                binding.tvCompletedBy.visibility = View.VISIBLE
                binding.tvCompletedBy.text = "✓ Done by ${task.completedByName}"
                binding.tilNotes.visibility = View.GONE
            } else {
                binding.tvCompletedBy.visibility = View.GONE
                // Show notes field when checkbox ticked
                binding.cbDone.setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) {
                        binding.tilNotes.visibility = View.VISIBLE
                    }
                }
            }

            // Confirm button appears in notes field area — tap "Done" on keyboard triggers save
            binding.etNotes.setOnEditorActionListener { _, _, _ ->
                val notes = binding.etNotes.text.toString()
                onTaskCompleted(task.taskId, notes)
                true
            }

            // Also allow completing by tapping the checkbox directly (no notes)
            binding.cbDone.setOnClickListener {
                if (binding.cbDone.isChecked && !task.isDone) {
                    val notes = binding.etNotes.text.toString()
                    onTaskCompleted(task.taskId, notes)
                }
            }
        }
    }

    class TaskDiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem.taskId == newItem.taskId
        override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem == newItem
    }
}
