package com.nusantarian.faloquiz.ui.fragment.auth

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.nusantarian.faloquiz.R
import com.nusantarian.faloquiz.databinding.FragmentForgotBinding


class ForgotFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentForgotBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentForgotBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()
        binding.btnResetPass.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_reset_pass) {
            binding.progress.visibility = View.VISIBLE
            val email = binding.tilEmail.editText?.text.toString()

            if (!isValid(email)){
                binding.tilEmail.visibility = View.GONE
            } else {
                resetPass(email)
            }
        }
    }

    private fun resetPass(email: String) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(context, "Please Check Your Email", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Failed to Reset Password", Toast.LENGTH_SHORT).show()
            }
            binding.progress.visibility = View.GONE
        }.addOnFailureListener {
            binding.progress.visibility = View.GONE
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
        }
    }

    private fun isValid(email: String): Boolean {
        val empty = activity!!.resources.getString(R.string.text_empty)
        val valid = activity!!.resources.getString(R.string.text_email_not_match)

        return if (email.isEmpty()) {
            binding.tilEmail.error = empty
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = valid
            false
        } else {
            binding.tilEmail.error = null
            binding.tilEmail.isErrorEnabled
            true
        }
    }
}