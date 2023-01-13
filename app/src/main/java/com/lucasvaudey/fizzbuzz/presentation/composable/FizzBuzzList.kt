package com.lucasvaudey.fizzbuzz.presentation.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.lucasvaudey.fizzbuzz.presentation.viewmodels.ListViewModel

@Composable
fun FizzBuzzList(
    viewModel: ListViewModel = hiltViewModel()
) {
    val scrollState = rememberLazyListState()
    val layoutInfo = remember { derivedStateOf { scrollState.layoutInfo } }
    Column(Modifier.fillMaxSize()) {
        //Load more when the user is at the end of the list
        if ((layoutInfo.value.visibleItemsInfo.lastOrNull()?.index
                ?: 0) > layoutInfo.value.totalItemsCount - 10 && !viewModel.isLoading.value
        ) {
            viewModel.calculateFizzBuzzList()
        }
        Text(text = "FizzBuzzList")
        if (viewModel.isLoading.value) {
            CircularProgressIndicator()
        } else {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth(),
                state = scrollState,
                content = {
                    items(viewModel.fizzBuzzList.size) {
                        Text(text = viewModel.fizzBuzzList[it])
                    }
                    if (viewModel.isLoadingMore.value) {
                        item {
                            CircularProgressIndicator()
                        }
                    }
                })
        }
    }

}