package com.valentinesgarage.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.valentinesgarage.R
import com.valentinesgarage.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel.currentUser != null) {
            findNavController().navigate(R.id.action_login_to_jobList)
            return
        }

        binding.btnLogin.setOnClickListener {
            val email    = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.login(email, password)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.loginState.collect { state ->
                when (state) {
                    is AuthViewModel.LoginState.Idle -> {
                        binding.progressBar.visibility = View.GONE
                        binding.tvError.visibility     = View.GONE
                    }
                    is AuthViewModel.LoginState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.tvError.visibility     = View.GONE
                        binding.btnLogin.isEnabled     = false
                    }
                    // FIX: Success is now an object, not a data class carrying
                    // a User. The User/role is fetched by MainActivity after
                    // navigation — never during the login call itself.
                    is AuthViewModel.LoginState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        findNavController().navigate(R.id.action_login_to_jobList)
                    }
                    is AuthViewModel.LoginState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.btnLogin.isEnabled     = true
                        binding.tvError.visibility     = View.VISIBLE
                        binding.tvError.text           = state.message
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
