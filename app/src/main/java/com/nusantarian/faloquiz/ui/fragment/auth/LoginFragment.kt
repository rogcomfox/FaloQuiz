package com.nusantarian.faloquiz.ui.fragment.auth

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.auth.FirebaseAuth
import com.nusantarian.faloquiz.R
import com.nusantarian.faloquiz.databinding.FragmentLoginBinding
import com.nusantarian.faloquiz.ui.activity.MainActivity

class LoginFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var ft: FragmentTransaction
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        ft = activity!!.supportFragmentManager.beginTransaction()
        auth = FirebaseAuth.getInstance()

        binding.tvForgotPass.setOnClickListener(this)
        binding.btnLogin.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_login -> {
                binding.progress.visibility = View.VISIBLE
                val email = binding.tilEmail.editText?.text.toString()
                val pass = binding.tilPass.editText?.text.toString()

                if (!isValid(email, pass)) {
                    binding.progress.visibility = View.GONE
                } else {
                    loginUser(email, pass)
                }
            }
            R.id.tv_forgot_pass ->
                ft.replace(R.id.frame_landing, ForgotFragment())
                    .addToBackStack(null)
                    .commit()
        }
    }

    private fun loginUser(email: String, pass: String) {
        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(context, "Welcome to FaloQuiz", Toast.LENGTH_SHORT).show()
                startActivity(Intent(context, MainActivity::class.java))
                activity!!.finish()
            } else {
                Toast.makeText(context, "Failed To Login", Toast.LENGTH_SHORT).show()
            }
            binding.progress.visibility = View.GONE
        }.addOnFailureListener {
            binding.progress.visibility = View.GONE
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun isValid(email: String, pass: String): Boolean {
        val empty = activity!!.resources.getString(R.string.text_empty)
        val valid = activity!!.resources.getString(R.string.text_email_not_match)
        return if (email.isEmpty()) {
            binding.tilEmail.error = empty
            false
        } else if (pass.isEmpty()) {
            binding.tilPass.error = empty
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = valid
            false
        } else {
            binding.tilEmail.error = null
            binding.tilPass.error = null

            binding.tilPass.isErrorEnabled
            binding.tilEmail.isErrorEnabled
            true
        }
    }
}