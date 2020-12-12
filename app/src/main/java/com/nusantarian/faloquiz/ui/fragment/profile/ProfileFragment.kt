package com.nusantarian.faloquiz.ui.fragment.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nusantarian.faloquiz.R
import com.nusantarian.faloquiz.databinding.FragmentProfileBinding
import com.nusantarian.faloquiz.ui.activity.LandingActivity

class ProfileFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var ft: FragmentTransaction
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        ft = activity!!.supportFragmentManager.beginTransaction()
        auth = FirebaseAuth.getInstance()

        loadData()

        binding.btnEditProfile.setOnClickListener(this)
        binding.btnLogout.setOnClickListener(this)
        binding.icBack.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_edit_profile ->
                ft.replace(R.id.frame_main, EditProfileFragment())
                    .addToBackStack(null)
                    .commit()
            R.id.btn_logout ->
                logoutUser()
            R.id.ic_back ->
                activity!!.onBackPressed()
        }
    }

    private fun logoutUser(){
        auth.signOut()
        startActivity(Intent(context, LandingActivity::class.java))
        activity!!.finish()
    }

    private fun loadData(){
        binding.progress.visibility = View.VISIBLE
        val uid = auth.currentUser?.uid!!
        val data = FirebaseFirestore.getInstance().collection("users").document(uid)
        data.get().addOnCompleteListener {

        }.addOnFailureListener {
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            binding.progress.visibility = View.GONE
        }
    }

}