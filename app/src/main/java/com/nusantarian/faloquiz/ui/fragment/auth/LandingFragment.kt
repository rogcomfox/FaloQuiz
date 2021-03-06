package com.nusantarian.faloquiz.ui.fragment.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.nusantarian.faloquiz.R
import com.nusantarian.faloquiz.databinding.FragmentLandingBinding

class LandingFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentLandingBinding? = null
    private val binding get() = _binding!!
    private lateinit var ft: FragmentTransaction

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLandingBinding.inflate(inflater, container, false)
        ft = activity!!.supportFragmentManager.beginTransaction()
        binding.btnLogin.setOnClickListener(this)
        binding.btnRegister.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_login ->
                ft.replace(R.id.frame_landing, LoginFragment())
                    .addToBackStack(null)
                    .commit()
            R.id.btn_register ->
                ft.replace(R.id.frame_landing, RegisterFragment())
                    .addToBackStack(null)
                    .commit()
        }
    }
}