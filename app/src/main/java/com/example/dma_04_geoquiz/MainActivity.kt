package com.example.dma_04_geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionTextView: TextView
    var CurrentQuestNum = 1
    var RightAnsNum = 0
    private val quizViewModel: QuizViewModel by
    lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        trueButton = findViewById(R.id.true_button)
         falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)
        trueButton.setOnClickListener {
            checkAnswer(true)
            uncklickButtons()
            if(CurrentQuestNum == 6) { nextButton.setVisibility(View.GONE) }
        }
        falseButton.setOnClickListener {
            checkAnswer(false)
            uncklickButtons()
            if(CurrentQuestNum == 6) { nextButton.setVisibility(View.GONE) }
        }
        nextButton.setOnClickListener {
            CurrentQuestNum =+1

            quizViewModel.moveToNext()
            updateQuestion()
            trueButton.setVisibility(View.VISIBLE)
            falseButton.setVisibility(View.VISIBLE)
            nextButton.setVisibility(View.GONE)
        }
        updateQuestion()
        /* 84 страница книги*/
    }
    private fun updateQuestion() {
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)
    }
    private fun uncklickButtons(){
        trueButton.setVisibility(View.GONE)
        falseButton.setVisibility(View.GONE)
        nextButton.setVisibility(View.VISIBLE)
    }
    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        val messageResId = if (userAnswer == correctAnswer) {
            RightAnsNum =+1
            R.string.correct_toast } else { R.string.incorrect_toast }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
    }

}