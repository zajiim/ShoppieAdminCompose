package com.example.shoppieadmin.presentation.auth.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieadmin.core.navigation.Graph
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


    var startDestination by mutableStateOf(Graph.AUTH)
        private set

    init {
        viewModelScope.launch {
            tokenUseCase.readTokenUseCase().onEach { token ->
                val isTokenValid = token?.let { shoppieApi.isTokenValid(it) }
                startDestination = if (isTokenValid?.status == true) {
                    Graph.MAIN
                } else {
                    Graph.AUTH
                }
                delay(300)
                splashCondition = false

            }.launchIn(viewModelScope)
        }
    }

}