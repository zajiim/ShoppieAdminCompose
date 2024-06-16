package com.example.shoppieadmin.presentation.auth.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieadmin.core.navigation.Routes
import com.example.shoppieadmin.domain.auth.login.models.MainActivityEvent
import com.example.shoppieadmin.domain.auth.login.use_cases.ReadTokenUseCase
import com.example.shoppieadmin.domain.auth.login.use_cases.SaveTokenUseCase
import com.example.shoppieadmin.domain.auth.login.use_cases.TokenUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.DEFAULT_CONCURRENCY
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val tokenUseCase: TokenUseCases
): ViewModel() {

//    private val _splashCondition = mutableStateOf(true)
//    val splashCondition = _splashCondition
    var splashCondition by mutableStateOf(true)
    private set

    var startDestination by mutableStateOf(Routes.AuthNavigation.route)
        private set

    init {
        viewModelScope.launch {
            tokenUseCase.readTokenUseCase().onEach { shouldStartFromHomeScreen ->

                Log.e(TAG, "inside viewmodel token ======= $shouldStartFromHomeScreen ", )
                if (!shouldStartFromHomeScreen.isNullOrEmpty()) {
                    startDestination = Routes.HomeNavigation.route
                } else {
                    startDestination = Routes.AuthNavigation.route
                }
                delay(300)
                splashCondition = false

            }.launchIn(viewModelScope)
        }
    }

//    fun onEvent(event: MainActivityEvent) {
//        when(event) {
//            MainActivityEvent.ReadTokenOnEntry ->  {
//                readTokenOnStart()
//            }
//        }
//    }
//
//    private fun readTokenOnStart() {
//        viewModelScope.launch {
//            tokenUseCase()
//        }
//    }
}