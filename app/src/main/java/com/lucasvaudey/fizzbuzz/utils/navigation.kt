package com.lucasvaudey.fizzbuzz.utils

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lucasvaudey.fizzbuzz.presentation.composable.FizzBuzzList
import com.lucasvaudey.fizzbuzz.presentation.composable.Form

@Composable
fun NavigationHost(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Form",
    ) {
        composable("Form") {
            Form(onNavigate = {
                navController.navigate("FizzBuzzList")
            })
        }
        composable("FizzBuzzList") {
            FizzBuzzList()
        }
    }
}
