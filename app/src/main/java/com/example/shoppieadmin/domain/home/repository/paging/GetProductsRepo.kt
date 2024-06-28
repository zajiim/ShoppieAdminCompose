package com.example.shoppieadmin.domain.home.repository.paging

import androidx.paging.PagingData
import com.example.shoppieadmin.domain.home.models.AllProductsResponse
import com.example.shoppieadmin.domain.home.models.Product
import com.example.shoppieadmin.utils.Resource
import kotlinx.coroutines.flow.Flow

interface GetProductsRepo {
    fun getAllProducts(): Flow<PagingData<Product>>
}