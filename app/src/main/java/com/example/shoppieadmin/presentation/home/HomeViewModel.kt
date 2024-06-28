package com.example.shoppieadmin.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.shoppieadmin.data.repository.GetProductsRepoImpl
import com.example.shoppieadmin.domain.home.repository.paging.GetProductsRepo
import com.example.shoppieadmin.domain.home.use_cases.GetAllProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getAllProductsUseCase: GetAllProductsUseCase
): ViewModel() {

    val products = getAllProductsUseCase().cachedIn(viewModelScope)


}