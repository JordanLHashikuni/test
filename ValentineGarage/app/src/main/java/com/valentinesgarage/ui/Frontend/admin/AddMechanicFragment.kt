package com.valentinesgarage.ui.admin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.valentinesgarage.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 * Admin-only screen for creating a new mechanic account.
 *
 * FIX: Now collects the admin's own email + password so UserRepository can
 * re-authenticate the admin after Firebase switches the session to the newly
 * created mechanic account (which it does automatically on
 * createUserWithEmailAndPassword). Without re-auth the admin is silently
 * signed out the moment the mechanic account is created.
 */
@AndroidEntryPoint
class AddMechanicFragment : Fragment() {

    private val viewModel: AddMechanicViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return buildLayout(inflater, container)
    }

    private fun buildLayout(inflater: LayoutInflater, container: ViewGroup?): View {
        val ctx = requireContext()

        val layout = android.widget.LinearLayout(ctx).apply {
            orientation = android.widget.LinearLayout.VERTICAL
            setPadding(48, 48, 48, 48)
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        }

        fun editText(hint: String, inputType: Int = android.text.InputType.TYPE_CLASS_TEXT) =
            EditText(ctx).apply {
                this.hint = hint
                this.inputType = inputType
                layoutParams = android.widget.LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply { bottomMargin = 24 }
            }

        // ── Mechanic fields ───────────────────────────────────────────────
        val sectionMechanic = TextView(ctx).apply {
            text = "New Mechanic Details"
            textSize = 16f
            setTypeface(null, android.graphics.Typeface.BOLD)
            layoutParams = android.widget.LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply { bottomMargin = 16 }
        }

        val etName = editText("Full name")
        val etEmail = editText("Email", android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS)
        val etPassword = editText(
            "Password",
            android.text.InputType.TYPE_CLASS_TEXT or
                    android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
        )

        // ── Admin re-auth fields ──────────────────────────────────────────
        // Firebase switches the session to the new mechanic the moment their
        // Auth account is created. We need the admin's credentials to sign
        // back in immediately after — otherwise the admin is silently logged out.
        val divider = View(ctx).apply {
            setBackgroundColor(android.graphics.Color.LTGRAY)
            layoutParams = android.widget.LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 2
            ).apply { topMargin = 24; bottomMargin = 24 }
        }

        val sectionAdmin = TextView(ctx).apply {
            text = "Confirm Your Admin Password"
            textSize = 16f
            setTypeface(null, android.graphics.Typeface.BOLD)
            layoutParams = android.widget.LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply { bottomMargin = 8 }
        }

        val sectionAdminHint = TextView(ctx).apply {
            text = "Required to keep your session active after creating the account."
            textSize = 12f
            setTextColor(android.graphics.Color.GRAY)
            layoutParams = android.widget.LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply { bottomMargin = 16 }
        }

        val etAdminEmail = editText(
            "Your admin email",
            android.text.InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        )
        val etAdminPassword = editText(
            "Your admin password",
            android.text.InputType.TYPE_CLASS_TEXT or
                    android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
        )

        // ── Error + progress + submit ─────────────────────────────────────
        val tvError = TextView(ctx).apply {
            setTextColor(android.graphics.Color.RED)
            visibility = View.GONE
            layoutParams = android.widget.LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply { bottomMargin = 16 }
        }

        val progressBar = ProgressBar(ctx).apply {
            visibility = View.GONE
            layoutParams = android.widget.LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).apply { gravity = android.view.Gravity.CENTER_HORIZONTAL }
        }

        val btnSave = Button(ctx).apply {
            text = "Create Mechanic Account"
            setBackgroundColor(ctx.getColor(R.color.red_700))
            setTextColor(android.graphics.Color.WHITE)
            layoutParams = android.widget.LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }

        layout.addView(sectionMechanic)
        layout.addView(etName)
        layout.addView(etEmail)
        layout.addView(etPassword)
        layout.addView(divider)
        layout.addView(sectionAdmin)
        layout.addView(sectionAdminHint)
        layout.addView(etAdminEmail)
        layout.addView(etAdminPassword)
        layout.addView(tvError)
        layout.addView(progressBar)
        layout.addView(btnSave)

        btnSave.setOnClickListener {
            val name          = etName.text.toString().trim()
            val email         = etEmail.text.toString().trim()
            val password      = etPassword.text.toString()
            val adminEmail    = etAdminEmail.text.toString().trim()
            val adminPassword = etAdminPassword.text.toString()

            if (name.isBlank() || email.isBlank() || password.isBlank()) {
                tvError.text = "Mechanic name, email, and password are required"
                tvError.visibility = View.VISIBLE
                return@setOnClickListener
            }

            if (adminEmail.isBlank() || adminPassword.isBlank()) {
                tvError.text = "Enter your admin email and password to confirm"
                tvError.visibility = View.VISIBLE
                return@setOnClickListener
            }

            tvError.visibility = View.GONE
            btnSave.isEnabled = false
            progressBar.visibility = View.VISIBLE

            viewLifecycleOwner.lifecycleScope.launch {
                val result = viewModel.createMechanic(
                    name          = name,
                    email         = email,
                    password      = password,
                    adminEmail    = adminEmail,
                    adminPassword = adminPassword
                )
                progressBar.visibility = View.GONE
                btnSave.isEnabled = true

                result.fold(
                    onSuccess = {
                        Toast.makeText(
                            ctx,
                            "Mechanic account created for $name",
                            Toast.LENGTH_SHORT
                        ).show()
                        findNavController().navigate(R.id.action_addMechanic_to_jobList)
                    },
                    onFailure = {
                        tvError.text = it.message ?: "Failed to create account"
                        tvError.visibility = View.VISIBLE
                    }
                )
            }
        }

        return layout
    }
}
