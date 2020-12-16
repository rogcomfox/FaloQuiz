package com.nusantarian.faloquiz.ui.fragment.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.nusantarian.faloquiz.R
import com.nusantarian.faloquiz.databinding.FragmentQuizBinding
import com.nusantarian.faloquiz.model.Question
import java.util.*

class QuizFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    private val mQuestions: Question = Question()
    private val mQuestionsLength: Int = mQuestions.mQuestions.size
    private var mScore = 0
    private var mAnswer: String? = null
    private lateinit var random: Random
    private lateinit var ft: FragmentTransaction

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        random = Random()
        ft = activity!!.supportFragmentManager.beginTransaction()
        binding.tvScore.text = "Score: $mScore"
        binding.btnAnswer1.setOnClickListener(this)
        binding.btnAnswer2.setOnClickListener(this)
        binding.btnAnswer3.setOnClickListener(this)
        binding.btnAnswer4.setOnClickListener(this)
        binding.icBack.setOnClickListener(this)
        updateQuestion(random.nextInt(mQuestionsLength))
        return binding.root
    }

    private fun updateQuestion(num: Int) {
        binding.tvQuestion.text = mQuestions.getQuestion(num)
        binding.btnAnswer1.text = mQuestions.getChoice1(num)
        binding.btnAnswer2.text = mQuestions.getChoice2(num)
        binding.btnAnswer3.text = mQuestions.getChoice3(num)
        binding.btnAnswer4.text = mQuestions.getChoice4(num)

        mAnswer = mQuestions.getCorrectAnswer(num)
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_answer_1 -> {
                if (binding.btnAnswer1.text == mAnswer) {
                    mScore++
                    binding.tvScore.text = "Score: $mScore"
                    updateQuestion(random.nextInt(mQuestionsLength))
                } else {
                    gameOver()
                }
            }
            R.id.btn_answer_2 -> {
                if (binding.btnAnswer2.text == mAnswer) {
                    mScore++
                    binding.tvScore.text = "Score: $mScore"
                    updateQuestion(random.nextInt(mQuestionsLength))
                } else {
                    gameOver()
                }
            }
            R.id.btn_answer_3 -> {
                if (binding.btnAnswer3.text == mAnswer) {
                    mScore++
                    binding.tvScore.text = "Score: $mScore"
                    updateQuestion(random.nextInt(mQuestionsLength))
                } else {
                    gameOver()
                }
            }
            R.id.btn_answer_4 -> {
                if (binding.btnAnswer4.text == mAnswer) {
                    mScore++
                    binding.tvScore.text = "Score: $mScore"
                    updateQuestion(random.nextInt(mQuestionsLength))
                } else {
                    gameOver()
                }
            }
            R.id.ic_back -> {
                confirmLeave()
            }
        }
    }

    private fun gameOver() {
        val dialog = MaterialAlertDialogBuilder(context!!)
        dialog
            .setMessage("Game Over! Score Anda $mScore point")
            .setCancelable(false)
            .setPositiveButton("New Game") { _, _ ->
                ft.replace(R.id.frame_main, HomeFragment()).commit()
            }
            .setNegativeButton("Exit Game") { _, _ ->
                activity!!.finish()
            }
        dialog.create().show()
    }

    private fun confirmLeave() {
        val dialogBuilder = MaterialAlertDialogBuilder(context!!)
        dialogBuilder
            .setMessage("Anda Yakin Ingin Meninggalkan Quiz Ini?")
            .setPositiveButton("Ya") { _, _ ->
                ft.replace(R.id.frame_main, HomeFragment()).commit()
            }
            .setNegativeButton("Tidak") { _, _ ->

            }
        dialogBuilder.create().show()
    }

}