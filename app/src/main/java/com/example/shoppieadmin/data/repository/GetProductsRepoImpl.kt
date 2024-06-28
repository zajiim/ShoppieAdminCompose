package com.example.shoppieadmin.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.shoppieadmin.data.remote.api.ShoppieApi
import com.example.shoppieadmin.data.remote.paging.GetProductsPagingSource
import com.example.shoppieadmin.domain.home.models.Product
import com.example.shoppieadmin.domain.home.repository.paging.GetProductsRepo
import com.example.shoppieadmin.domain.main.datamanager.LocalUserManager
import com.example.shoppieadmin.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsRepoImpl @Inject constructor(
    private val shoppieApi: ShoppieApi,
    private val localUserManager: LocalUserManager
): GetProductsRepo {
    override fun getAllProducts(): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { GetProductsPagingSource(shoppieApi, localUserManager) }
        ).flow
    }


}