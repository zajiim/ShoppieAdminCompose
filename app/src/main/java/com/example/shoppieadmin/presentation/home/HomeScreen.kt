package com.example.shoppieadmin.presentation.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.shoppieadmin.domain.home.models.Product
import com.example.shoppieadmin.presentation.home.components.CustomItem
import com.example.shoppieadmin.presentation.home.components.ShimmerItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    onClick: () -> Unit
) {
    val products = viewModel.products.collectAsLazyPagingItems()
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn {
            items(products.itemCount) { index ->
                val product = products[index]
                product?.let {
                    CustomItem(
                        modifier = Modifier,
                        product = it
                    )
                } ?: ShimmerItem(modifier = Modifier)
            }

            products.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {
                            ShimmerItem(modifier = Modifier)
                        }
                    }
                    loadState.append is LoadState.Loading -> {
                        item {
                            CircularProgressIndicator()
                        }
                    }
                    loadState.refresh is LoadState.Error -> {
                        val e = products.loadState.refresh as LoadState.Error
                        item {
                            Text(text = "Error: ${e.error.message}")
                        }
                    }
                    loadState.append is LoadState.Error -> {
                        val e = products.loadState.append as LoadState.Error
                        item {
                            Text(text = "Error: ${e.error.message}")
                        }
                    }
                }
            }
        }

        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 86.dp)
                .wrapContentSize()
                .background(Color.Transparent),
            onClick = { onClick() }
        ) {
            Text(
                modifier = Modifier.padding(16.dp),
                text = "Add Products"
            )
        }
    }

}


