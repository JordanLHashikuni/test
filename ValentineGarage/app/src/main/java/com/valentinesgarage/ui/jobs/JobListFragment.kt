package com.valentinesgarage.ui.jobs

import android.os.Bundle
import android.util.Log
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

    companion object {
        private const val TAG = "JobListFragment"
    }

    private var _binding: FragmentJobListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: JobListViewModel by viewModels()
    private lateinit var adapter: JobAdapter

    // ── Persistent admin flag ─────────────────────────────────────────────
    //
    // This is the root fix for the "Add Mechanic not appearing" bug.
    //
    // The problem: onCreateMenu() is called once, immediately when the
    // MenuProvider is registered. At that moment viewModel.isAdmin.value is
    // still false because the Firestore getUser() coroutine hasn't returned
    // yet. Even though invalidateOptionsMenu() is called later (when the flow
    // emits true), it triggers onCreateMenu() again — but if the fragment
    // re-reads viewModel.isAdmin.value at that precise instant it may still
    // see false due to coroutine scheduling.
    //
    // The fix: maintain a fragment-level isAdminUser field that is updated
    // from the StateFlow collector BEFORE we call invalidateOptionsMenu().
    // onPrepareMenu() (not onCreateMenu) then reads this stable field to set
    // visibility. onPrepareMenu() is called on every invalidate, making it
    // the correct hook for dynamic item visibility.
    //
    private var isAdminUser: Boolean = false

    // ── Lifecycle ─────────────────────────────────────────────────────────

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
        observeAdminRole()
    }

    // ── Admin role observer ───────────────────────────────────────────────
    //
    // Collect the StateFlow in a SEPARATE function so the sequence is clear:
    //   1. Update the stable fragment field (isAdminUser).
    //   2. Log the state for debugging.
    //   3. Only then call invalidateOptionsMenu() so onPrepareMenu() sees the
    //      correct value when it runs.
    //
    private fun observeAdminRole() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isAdmin.collect { admin ->
                Log.d(TAG, "observeAdminRole: isAdmin=$admin  roleLoaded=${viewModel.roleLoaded}")
                isAdminUser = admin           // ← update stable field FIRST
                requireActivity().invalidateOptionsMenu()   // ← THEN redraw
            }
        }
    }

    // ── Menu ──────────────────────────────────────────────────────────────

    private fun setupMenu() {
        requireActivity().addMenuProvider(object : MenuProvider {

            /**
             * onCreateMenu() inflates the menu XML. We do NOT set item
             * visibility here because this callback fires only once (at
             * registration time) and isAdmin may not be known yet.
             *
             * Visibility is controlled entirely in onPrepareMenu(), which
             * is called on every invalidateOptionsMenu() — including the one
             * triggered by observeAdminRole() once Firestore returns.
             */
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                Log.d(TAG, "onCreateMenu: inflating menu — isAdminUser=$isAdminUser")
                menuInflater.inflate(R.menu.menu_jobs, menu)
                // Do not touch visibility here; let onPrepareMenu handle it.
            }

            /**
             * onPrepareMenu() is the correct place for dynamic item
             * visibility. It is called:
             *   • right after onCreateMenu()
             *   • every time invalidateOptionsMenu() / invalidateMenu() is
             *     called on the host activity
             *
             * Reading the stable isAdminUser field (updated by the Flow
             * collector before invalidation) guarantees we always see the
             * current value, with no race against the Firestore coroutine.
             */
            override fun onPrepareMenu(menu: Menu) {
                Log.d(TAG,
                    "onPrepareMenu: isAdminUser=$isAdminUser  " +
                    "roleLoaded=${viewModel.roleLoaded}  " +
                    "isAdmin.value=${viewModel.isAdmin.value}"
                )
                menu.findItem(R.id.action_add_mechanic)?.isVisible = isAdminUser
                menu.findItem(R.id.action_logout)?.isVisible = true
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {

                    R.id.action_logout -> {
                        Log.d(TAG, "onMenuItemSelected: Logout")
                        viewModel.logout()
                        findNavController().navigate(R.id.loginFragment)
                        true
                    }

                    R.id.action_add_mechanic -> {
                        Log.d(TAG, "onMenuItemSelected: Add Mechanic — isAdminUser=$isAdminUser")
                        findNavController().navigate(R.id.action_jobList_to_addMechanic)
                        true
                    }

                    else -> false
                }
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
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
