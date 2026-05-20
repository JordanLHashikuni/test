package com.valentinesgarage.ui.jobs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.valentinesgarage.data.model.Job
import com.valentinesgarage.databinding.ItemJobBinding
import java.text.SimpleDateFormat
import java.util.Locale

class JobAdapter(
    private val onJobClick: (Job) -> Unit
) : ListAdapter<Job, JobAdapter.JobViewHolder>(JobDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewHolder {
        val binding = ItemJobBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return JobViewHolder(binding)
    }

    override fun onBindViewHolder(holder: JobViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class JobViewHolder(private val binding: ItemJobBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(job: Job) {
            binding.tvRegistration.text = job.truckRegistration
            binding.tvStatus.text = job.status.name
            job.createdAt?.let { timestamp ->
                val sdf = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())
                binding.tvCheckinTime.text = "Checked in: ${sdf.format(timestamp.toDate())}"
            }
            binding.root.setOnClickListener { onJobClick(job) }
        }
    }

    class JobDiffCallback : DiffUtil.ItemCallback<Job>() {
        override fun areItemsTheSame(oldItem: Job, newItem: Job) = oldItem.jobId == newItem.jobId
        override fun areContentsTheSame(oldItem: Job, newItem: Job) = oldItem == newItem
    }
}
