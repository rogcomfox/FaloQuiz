package com.nusantarian.faloquiz.ui.fragment.auth

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nusantarian.faloquiz.R
import com.nusantarian.faloquiz.databinding.FragmentRegisterBinding
import com.nusantarian.faloquiz.model.User
import com.nusantarian.faloquiz.ui.activity.MainActivity

class RegisterFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()
        binding.btnRegister.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_register) {
            binding.progress.visibility = View.VISIBLE
            val email = binding.tilEmail.editText?.text.toString()
            val name = binding.tilName.editText?.text.toString()
            val pass = binding.tilPass.editText?.text.toString()
            val confirm = binding.tilPassConfirm.editText?.text.toString()

            if (!isValid(email, name, pass, confirm)) {
                binding.progress.visibility = View.GONE
            } else {
                registerUser(email, name, pass)
            }
        }
    }

    private fun registerUser(email: String, name: String, pass: String) {
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
            if (it.isSuccessful) {
                val uid = auth.currentUser?.uid!!
                pushUserData(uid, email, name)
            } else {
                Toast.makeText(context, "Failed To Register", Toast.LENGTH_SHORT).show()
            }
            binding.progress.visibility = View.GONE
        }.addOnFailureListener {
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            binding.progress.visibility = View.GONE
        }
    }

    private fun pushUserData(uid: String, email: String, name: String) {
        val data = FirebaseFirestore.getInstance().collection("users").document(uid)
        val userData = User(email, name, PROFILE_PICTURE)
        data.set(userData).addOnCompleteListener {
            if (it.isSuccessful) {
                startActivity(Intent(context, MainActivity::class.java))
                activity!!.finish()
                Toast.makeText(context, "Welcome to FaloQuiz", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Failed to Save Data", Toast.LENGTH_SHORT).show()
            }
            binding.progress.visibility = View.GONE
        }.addOnFailureListener {
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            binding.progress.visibility = View.GONE
        }
    }

    private fun isValid(email: String, name: String, pass: String, confirm: String): Boolean {
        val empty = activity!!.resources.getString(R.string.text_empty)
        val valid = activity!!.resources.getString(R.string.text_email_not_match)
        val match = activity!!.resources.getString(R.string.text_pass_not_match)
        return if (email.isEmpty()) {
            binding.tilEmail.error = empty
            false
        } else if (name.isEmpty()) {
            binding.tilName.error = empty
            false
        } else if (pass.isEmpty()) {
            binding.tilPass.error = empty
            false
        } else if (pass != confirm) {
            binding.tilPass.error = match
            binding.tilPassConfirm.error = match
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = valid
            false
        } else {
            binding.tilEmail.error = null
            binding.tilPass.error = null
            binding.tilName.error = null

            binding.tilPass.isErrorEnabled
            binding.tilEmail.isErrorEnabled
            binding.tilName.isErrorEnabled
            true
        }
    }
    companion object{
        const val PROFILE_PICTURE = "https://firebasestorage.googleapis.com/v0/b/faloquiz.appspot.com/o/default_profile%2Fman.png?alt=media&token=3315b22d-b249-494e-9fca-0b756918d7ff"
    }
}