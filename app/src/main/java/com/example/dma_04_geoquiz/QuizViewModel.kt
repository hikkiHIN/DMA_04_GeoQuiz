package com.example.dma_04_geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

const val TAG = "QuizViewModel"
class QuizViewModel : ViewModel() {
    init {
        Log.d(TAG, "ViewModel instance created")
    }
    override fun onCleared() {
        super.onCleared()
        Log.d(TAG, "ViewModel instance about to be destroyed")
    }
}