package com.lucasvaudey.fizzbuzz.presentation.composable

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.lucasvaudey.fizzbuzz.presentation.viewmodels.FormViewModel
import kotlinx.coroutines.launch

@Composable
fun Form(
    onNavigate: () -> Unit,
    viewModel: FormViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }
    var number1 by remember { mutableStateOf(TextFieldValue("")) }
    var text1 by remember { mutableStateOf(TextFieldValue("")) }
    var text2 by remember { mutableStateOf(TextFieldValue("")) }
    var number2 by remember { mutableStateOf(TextFieldValue("")) }
    val context = LocalContext.current
    fun checkEntry(): Boolean {
        if (number1.text == number2.text) {
            Toast.makeText(
                context,
                "Le premier et le deuxième nombre doivent être différent",
                Toast.LENGTH_SHORT
            )
                .show()
            return false
        }
        if (text1.text.isEmpty() || text2.text.isEmpty() || number1.text.isEmpty() || number2.text.isEmpty()) {
            Toast.makeText(context, "Un des quatre champs n'est pas rempli", Toast.LENGTH_SHORT)
                .show()
            return false
        }
        if (number1.text.toIntOrNull() == null || number2.text.toIntOrNull() == null) {
            Toast.makeText(context, "Un des deux nombres est mal formé", Toast.LENGTH_SHORT)
                .show()
            return false

        }
        return true

    }
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Fizz Buzz", fontSize = 30.sp)
        Row {
            FormEntry(
                label = "Entrer un premier nombre",
                onValueChange = { number1 = it },
                modifier = Modifier.weight(1F),
                value = number1,
                isNumber = true,
            )
            Box(modifier = Modifier.width(5.dp))
            FormEntry(
                label = "Entrer un second nombre",
                onValueChange = { number2 = it },
                modifier = Modifier.weight(1F),
                value = number2,
                isNumber = true

            )
        }
        Row {
            FormEntry(
                label = "Entrer un mot associé au premier nombre",
                onValueChange = { text1 = it },
                value = text1,
                modifier = Modifier.weight(1F)
            )
            Box(modifier = Modifier.width(5.dp))
            FormEntry(
                label = "Entrer un mot associé au second nombre",
                onValueChange = { text2 = it },
                value = text2,
                modifier = Modifier.weight(1F)
            )
        }
        Button(onClick = {
            if (checkEntry()) {
                scope.launch {
                    isLoading = true
                    viewModel.saveEntry(
                        number1 = number1.text.toInt(),
                        number2 = number2.text.toInt(),
                        text1 = text1.text,
                        text2 = text2.text
                    )
                    isLoading = false
                    onNavigate()
                }
            }
        }) {
            if (isLoading)
                CircularProgressIndicator()
            else
                Text(text = "FizzBuzz !!")

        }
    }
}

@Composable
fun FormEntry(
    modifier: Modifier,
    label: String,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    isNumber: Boolean = false,
) {
    Column(modifier = modifier) {
        Text(text = label)
        TextField(
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions(
                keyboardType = if (isNumber) KeyboardType.Number else KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
        )
    }
}