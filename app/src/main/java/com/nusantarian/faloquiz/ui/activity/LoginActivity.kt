package com.nusantarian.faloquiz.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.nusantarian.faloquiz.R
import com.nusantarian.faloquiz.databinding.ActivityLoginBinding
import com.nusantarian.faloquiz.ui.fragment.auth.LoginFragment

class LoginActivity : AppCompatActivity(), FragmentManager.OnBackStackChangedListener {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.addOnBackStackChangedListener(this)
        initAuthFragment()
    }

    private fun initAuthFragment(){
        supportFragmentManager.beginTransaction()
            .add(R.id.frame_auth, LoginFragment())
            .commit()
    }

    override fun onBackStackChanged() {
        TODO("Not yet implemented")
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0)
            supportFragmentManager.popBackStack()
        else
            super.onBackPressed()
    }
}