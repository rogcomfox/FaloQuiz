package com.nusantarian.faloquiz.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nusantarian.faloquiz.R
import com.nusantarian.faloquiz.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? =null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()
        loadData()
        return binding.root
    }

    private fun loadData(){
        binding.progress.visibility = View.VISIBLE
        val uid = auth.currentUser?.uid!!
        val data = FirebaseFirestore.getInstance().collection("users").document(uid)
        data.get().addOnSuccessListener {

        }.addOnFailureListener {

        }

    }
}