package com.valentinesgarage.ui.jobs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.valentinesgarage.R
import com.valentinesgarage.databinding.FragmentJobListBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class JobListFragment : Fragment() {

    private var _binding: FragmentJobListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: JobListViewModel by viewModels()
    private lateinit var adapter: JobAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentJobListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupMenu()
        setupRecyclerView()
        observeJobs()
    }

    // ── Menu ──────────────────────────────────────────────────────────────

    private fun setupMenu() {
        requireActivity().addMenuProvider(object : MenuProvider {

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_jobs, menu)

                // FIX 3: The menu item ID was action_add_staff in the original
                // fragment but the nav graph action was action_jobList_to_addMechanic.
                // The menu XML now uses action_add_mechanic to match. Both the
                // lookup here and the click handler below use the new ID.
                //
                // FIX 4: Role-based visibility — hide "Add Mechanic" for mechanics,
                // show it for admins. The menu starts visible="true" in XML (see
                // menu_jobs.xml fix), so we only need to hide it here when the
                // user is NOT an admin. This avoids the race condition where the
                // item stayed hidden because isAdmin.value was still false at the
                // moment onCreateMenu() ran (before the Firestore call returned).
                val isAdmin = viewModel.isAdmin.value
                menu.findItem(R.id.action_add_mechanic)?.isVisible = isAdmin
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {

                    R.id.action_logout -> {
                        viewModel.logout()
                        findNavController().navigate(R.id.loginFragment)
                        true
                    }

                    // FIX 5: Was referencing R.id.action_add_staff (old ID that
                    // no longer exists in the updated menu XML). Updated to use
                    // R.id.action_add_mechanic so the click is actually caught.
                    R.id.action_add_mechanic -> {
                        findNavController().navigate(R.id.action_jobList_to_addMechanic)
                        true
                    }

                    else -> false
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        // FIX 6: When isAdmin loads from Firestore after the menu is first drawn,
        // invalidateOptionsMenu() triggers onCreateMenu() again with the correct
        // role value — so "Add Mechanic" appears for admins without needing a
        // screen restart. This was present before but had no effect because the
        // menu item ID mismatch meant the findItem() call always returned null.
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isAdmin.collect {
                requireActivity().invalidateOptionsMenu()
            }
        }
    }

    // ── RecyclerView ──────────────────────────────────────────────────────

    private fun setupRecyclerView() {
        adapter = JobAdapter { job ->
            val bundle = Bundle().apply {
                putString("jobId", job.jobId)
                putString("truckId", job.truckId)
            }
            findNavController().navigate(R.id.action_jobList_to_jobDetail, bundle)
        }
        binding.rvJobs.layoutManager = LinearLayoutManager(requireContext())
        binding.rvJobs.adapter = adapter
    }

    private fun observeJobs() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.openJobs.collect { jobs ->
                binding.progressBar.visibility = View.GONE
                if (jobs.isEmpty()) {
                    binding.rvJobs.visibility = View.GONE
                    binding.tvEmpty.visibility = View.VISIBLE
                } else {
                    binding.rvJobs.visibility = View.VISIBLE
                    binding.tvEmpty.visibility = View.GONE
                    adapter.submitList(jobs)
                }
            }
        }
    }

    // ── Lifecycle ─────────────────────────────────────────────────────────

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
