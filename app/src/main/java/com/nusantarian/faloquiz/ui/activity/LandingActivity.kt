package com.nusantarian.faloquiz.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nusantarian.faloquiz.databinding.ActivityLandingBinding

class LandingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLandingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLandingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}