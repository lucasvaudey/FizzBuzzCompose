package com.lucasvaudey.fizzbuzz.presentation.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lucasvaudey.fizzbuzz.domain.models.FbParam
import com.lucasvaudey.fizzbuzz.domain.repository.FbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repo: FbRepository
) : ViewModel() {
    private lateinit var fizzBuzzParam: FbParam
    val isLoading = mutableStateOf(true)
    val isLoadingMore = mutableStateOf(false)
    val fizzBuzzList = mutableStateListOf<String>()
    var lastIndex = 1
    private val batchLimit = 100


    init {
        viewModelScope.launch(Dispatchers.IO) {
            fizzBuzzParam = repo.getLastEntry()
            withContext(Dispatchers.Main) {
                isLoading.value = false
                calculateFizzBuzzList()
            }
        }
    }

    fun calculateFizzBuzzList() {
        isLoadingMore.value = true
        for (i in lastIndex..(lastIndex + batchLimit)) {
            when {
                i % fizzBuzzParam.number1 == 0 && i % fizzBuzzParam.number2 == 0 -> fizzBuzzList.add(
                    fizzBuzzParam.text1 + fizzBuzzParam.text2
                )
                i % fizzBuzzParam.number1 == 0 -> fizzBuzzList.add(fizzBuzzParam.text1)
                i % fizzBuzzParam.number2 == 0 -> fizzBuzzList.add(fizzBuzzParam.text2)
                else -> fizzBuzzList.add(i.toString())
            }
        }
        lastIndex += batchLimit
        isLoadingMore.value = false
    }


}