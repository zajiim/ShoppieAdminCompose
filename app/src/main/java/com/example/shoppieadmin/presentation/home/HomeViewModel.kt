package com.example.shoppieadmin.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.shoppieadmin.data.repository.GetProductsRepoImpl
import com.example.shoppieadmin.domain.home.models.Product
import com.example.shoppieadmin.domain.home.repository.paging.GetProductsRepo
import com.example.shoppieadmin.domain.home.use_cases.GetAllProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllProductsUseCase: GetAllProductsUseCase
): ViewModel() {

//    val products = getAllProductsUseCase().cachedIn(viewModelScope)

    private val _products = MutableStateFlow<PagingData<Product>>(PagingData.empty())
    val products = _products

    init {

    }

    fun refreshProducts() {
        viewModelScope.launch {
            getAllProductsUseCase().cachedIn(viewModelScope).collectLatest { prodx ->
                _products.value = prodx
            }
        }
    }

}