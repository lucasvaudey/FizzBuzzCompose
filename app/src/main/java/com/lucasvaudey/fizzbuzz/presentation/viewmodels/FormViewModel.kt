package com.lucasvaudey.fizzbuzz.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.lucasvaudey.fizzbuzz.domain.repository.FbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(
    private val repo: FbRepository
) : ViewModel() {
    fun saveEntry(number1: Int, number2: Int, text1: String, text2: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repo.saveEntry(number1, number2, text1, text2)
        }
    }

}