package com.nusantarian.faloquiz.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import com.nusantarian.faloquiz.R
import com.nusantarian.faloquiz.databinding.ActivityMainBinding
import com.nusantarian.faloquiz.ui.fragment.home.HomeFragment

class MainActivity : AppCompatActivity(), FragmentManager.OnBackStackChangedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.addOnBackStackChangedListener(this)
        initHomeFragment()
    }

    private fun initHomeFragment(){
        supportFragmentManager.beginTransaction()
            .add(R.id.frame_main, HomeFragment())
            .commit()
    }

    override fun onBackStackChanged() {

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