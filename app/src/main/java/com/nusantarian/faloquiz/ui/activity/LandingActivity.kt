package com.nusantarian.faloquiz.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.nusantarian.faloquiz.R
import com.nusantarian.faloquiz.databinding.ActivityLandingBinding
import com.nusantarian.faloquiz.ui.fragment.auth.LandingFragment

class LandingActivity : AppCompatActivity(), FragmentManager.OnBackStackChangedListener {

    private lateinit var binding: ActivityLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.addOnBackStackChangedListener(this)
        initFragment()
    }

    private fun initFragment(){
        supportFragmentManager.beginTransaction()
            .add(R.id.frame_landing, LandingFragment())
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

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return true
    }
}