package com.example.shoppieadmin.presentation.auth.main

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieadmin.core.navigation.Routes
import com.example.shoppieadmin.data.remote.ShoppieApi
import com.example.shoppieadmin.domain.auth.main.use_cases.TokenUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val tokenUseCase: TokenUseCases,
    private val shoppieApi: ShoppieApi
) : ViewModel() {

    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Routes.AuthNavigation.route)
        private set

    init {
        viewModelScope.launch {
            tokenUseCase.readTokenUseCase().onEach { shouldStartFromHomeScreen ->

                Log.e(TAG, "inside viewmodel token ======= $shouldStartFromHomeScreen ")

                val istokenValid = shouldStartFromHomeScreen?.let { shoppieApi.isTokenValid(it) }
                if (istokenValid?.data?.status == true) {
                    Log.e(TAG, "valid token found >>>>>>> ${istokenValid.data} ")
                    startDestination = Routes.HomeNavigation.route
                } else {
                    Log.e(TAG, "token in invalid >>>>>>> ${istokenValid?.data} ")
                    startDestination = Routes.AuthNavigation.route
                }

                delay(300)
                splashCondition = false

            }.launchIn(viewModelScope)
        }
    }

}