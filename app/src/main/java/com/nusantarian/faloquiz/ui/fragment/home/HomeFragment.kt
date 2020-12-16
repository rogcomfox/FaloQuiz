package com.nusantarian.faloquiz.ui.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nusantarian.faloquiz.R
import com.nusantarian.faloquiz.databinding.FragmentHomeBinding
import com.nusantarian.faloquiz.ui.fragment.profile.ProfileFragment

class HomeFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var ft: FragmentTransaction

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()
        binding.icProfile.setOnClickListener(this)
        binding.frameQuiz.setOnClickListener(this)
        ft = activity!!.supportFragmentManager.beginTransaction()
        loadData()
        return binding.root
    }

    private fun loadData() {
        binding.progress.visibility = View.VISIBLE
        val uid = auth.currentUser?.uid!!
        val data = FirebaseFirestore.getInstance().collection("users").document(uid)
        data.get().addOnSuccessListener {
            val name = it.getString("name")
            binding.tvWelcome.text = "Welcome, $name"
            binding.progress.visibility = View.GONE
        }.addOnFailureListener {
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            binding.progress.visibility = View.GONE
        }

    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.ic_profile ->
                ft.replace(R.id.frame_main, ProfileFragment())
                    .addToBackStack(null)
                    .commit()
            R.id.frame_quiz ->
                ft.replace(R.id.frame_main, QuizFragment())
                    .addToBackStack(null)
                    .commit()
        }
    }
}