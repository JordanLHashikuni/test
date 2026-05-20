package com.valentinesgarage.ui.jobs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.valentinesgarage.databinding.FragmentJobDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class JobDetailFragment : Fragment() {

    private var _binding: FragmentJobDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: JobDetailViewModel by viewModels()
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentJobDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val jobId = arguments?.getString("jobId") ?: return
        val truckId = arguments?.getString("truckId") ?: return
        viewModel.init(jobId, truckId)

        taskAdapter = TaskAdapter { taskId, notes ->
            viewModel.completeTask(taskId, notes)
        }

        binding.rvTasks.layoutManager = LinearLayoutManager(requireContext())
        binding.rvTasks.adapter = taskAdapter

        binding.btnAddTask.setOnClickListener {
            val description = binding.etNewTask.text.toString()
            viewModel.addTask(description)
            binding.etNewTask.text?.clear()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.truck.collect { truck ->
                truck?.let {
                    binding.tvTruckTitle.text = "${it.registrationNumber} — ${it.make} ${it.model}"
                    binding.tvTruckCondition.text = "Condition: ${it.conditionRating.name} — ${it.conditionNotes}"
                    binding.tvTruckKm.text = "Odometer at check-in: ${it.kilometerReading} km"
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.tasks.collect { tasks ->
                binding.progressBar.visibility = View.GONE
                taskAdapter.submitList(tasks)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
