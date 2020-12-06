package com.nusantarian.faloquiz.ui.fragment.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.nusantarian.faloquiz.R
import com.nusantarian.faloquiz.databinding.FragmentLoginBinding

class LoginFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var ft: FragmentTransaction

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        ft = activity!!.supportFragmentManager.beginTransaction()
        binding.tvForgotPass.setOnClickListener(this)
        binding.btnLogin.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_login ->
                Toast.makeText(context, "Under Construction", Toast.LENGTH_SHORT).show()
            R.id.tv_forgot_pass ->
                ft.replace(R.id.frame_landing, ForgotFragment())
                    .addToBackStack(null)
                    .commit()
        }
    }
}