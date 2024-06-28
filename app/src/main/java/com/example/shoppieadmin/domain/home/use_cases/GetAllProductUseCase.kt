package com.example.shoppieadmin.domain.home.use_cases

import androidx.paging.PagingData
import com.example.shoppieadmin.domain.home.models.Product
import com.example.shoppieadmin.domain.home.repository.paging.GetProductsRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(
    private val getProductsRepo: GetProductsRepo
) {
    operator fun invoke(): Flow<PagingData<Product>> {
        return getProductsRepo.getAllProducts()
    }
}