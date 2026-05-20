package com.valentinesgarage.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.valentinesgarage.R
import com.valentinesgarage.data.model.UserRole
import com.valentinesgarage.data.repository.UserRepository
import com.valentinesgarage.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    @Inject lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Required: makes our MaterialToolbar the support action bar so that
        // MenuProvider events from fragments actually fire.  This only works
        // when the theme does NOT provide its own WindowDecorActionBar
        // (i.e. the theme must extend a NoActionBar variant — see themes.xml).
        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        val topLevelDestinations = setOf(
            R.id.loginFragment,
            R.id.jobListFragment,
            R.id.checkInFragment,
            R.id.reportsFragment
        )

        val appBarConfig = AppBarConfiguration(topLevelDestinations)
        binding.toolbar.setupWithNavController(navController, appBarConfig)
        binding.bottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment -> {
                    binding.toolbar.visibility = View.GONE
                    binding.bottomNavigation.visibility = View.GONE
                }
                else -> {
                    binding.toolbar.visibility = View.VISIBLE
                    binding.bottomNavigation.visibility = View.VISIBLE
                    applyRoleBasedBottomNav()
                }
            }
        }
    }

    /**
     * Determines whether the Reports tab should be visible based on the
     * signed-in user's role fetched from Firestore.
     *
     * FIX: Previously this defaulted to UserRole.MECHANIC when the Firestore
     * call was slow or failed, which hid the Reports tab for admins until
     * they navigated again.
     *
     * The corrected approach:
     *   1. Show the Reports tab immediately (optimistic default = ADMIN).
     *   2. Hide it only once we have a confirmed MECHANIC role from Firestore.
     *
     * This means admins see the tab instantly with no flicker, and mechanics
     * have it hidden after the first async round-trip — which is acceptable
     * UX because mechanics reach JobListFragment via a login redirect that
     * already involves a short Firestore call.
     */
    private fun applyRoleBasedBottomNav() {
        val firebaseUser = userRepository.currentFirebaseUser
        if (firebaseUser == null) {
            setReportsTabVisible(false)
            return
        }

        // Step 1: Show the tab immediately so admins don't see it missing
        // while the Firestore request is in-flight.
        setReportsTabVisible(true)

        // Step 2: Confirm the role asynchronously, then hide if MECHANIC.
        lifecycleScope.launch {
            val role = userRepository.getUser(firebaseUser.uid)
                .getOrNull()?.role
                ?: UserRole.ADMIN   // FIX: safe default — better to show than hide

            setReportsTabVisible(role == UserRole.ADMIN)
        }
    }

    private fun setReportsTabVisible(visible: Boolean) {
        binding.bottomNavigation.menu
            .findItem(R.id.reportsFragment)
            ?.isVisible = visible
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
