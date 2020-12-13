package com.nusantarian.faloquiz.ui.fragment.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import coil.load
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.nusantarian.faloquiz.R
import com.nusantarian.faloquiz.databinding.FragmentEditProfileBinding

class EditProfileFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()
        binding.btnUpdateProfile.setOnClickListener(this)
        binding.imgProfile.setOnClickListener(this)
        loadProfile()
        return binding.root
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_update_profile ->
                //Todo: add logic for update profile
                Toast.makeText(context, "Under Construction", Toast.LENGTH_SHORT).show()
            R.id.img_profile ->
                initDialogImage()
        }
    }
    //Todo: Add Logic for Every item
    private fun initDialogImage(){
        val list = arrayOf("Take a Photo", "Pick From Gallery", "Delete Profile Picture")
        MaterialAlertDialogBuilder(context!!)
            .setTitle("Action")
            .setItems(list){ dialog, which ->

            }
            .show()
    }

    private fun loadProfile(){
        binding.progress.visibility = View.VISIBLE
        val uid = auth.currentUser?.uid!!
        val data = FirebaseFirestore.getInstance().collection("users").document(uid)
        data.get().addOnSuccessListener {
            val picture = it.getString("picture")
            val email = it.getString("email")
            val name = it.getString("name")
            binding.tilName.editText?.setText(name)
            binding.imgProfile.load(picture)
            binding.tilEmail.editText?.setText(email)
            binding.progress.visibility = View.GONE
        }.addOnFailureListener {
            Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            binding.progress.visibility = View.GONE
        }
    }
}