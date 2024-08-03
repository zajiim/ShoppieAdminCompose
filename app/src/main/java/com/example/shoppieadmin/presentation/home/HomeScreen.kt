package com.example.shoppieadmin.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.shoppieadmin.presentation.home.components.CustomItem
import com.example.shoppieadmin.presentation.home.components.ShimmerItem
import com.example.shoppieadmin.ui.theme.PrimaryColor

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
    bottomPadding: PaddingValues,
    onClick: () -> Unit
) {

    LaunchedEffect(Unit) {
        viewModel.refreshProducts()
    }


    val products = viewModel.products.collectAsLazyPagingItems()
    val configuration = LocalConfiguration.current

    val screenHeight = configuration.screenHeightDp.dp
    Box(modifier = modifier.fillMaxSize().padding(bottom = bottomPadding.calculateBottomPadding() + 8.dp)) {
        LazyColumn {
            when {
                products.loadState.refresh is LoadState.Loading && products.itemCount == 0 -> {
                    items(5) {
                        ShimmerItem(modifier = Modifier)
                    }
                }

                products.loadState.refresh is LoadState.NotLoading && products.itemCount == 0 -> {
                    item {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(top = screenHeight - (screenHeight / 1.5f)),
                                model = "https://media.geeksforgeeks.org/wp-content/uploads/20230802153215/Error-404.png",
                                contentDescription = null,
                                contentScale = ContentScale.FillBounds
                            )
                        }
                    }
                }

                else -> {
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
                            loadState.append is LoadState.Loading -> {
                                item {
                                    ShimmerItem(modifier = Modifier)
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
            }
        }

        FloatingActionButton(modifier = Modifier
            .align(Alignment.BottomCenter)
            .wrapContentSize(),
            containerColor = PrimaryColor,
            onClick = { onClick() }) {
            Text(
                modifier = Modifier.padding(16.dp), text = "Add Products"
            )
        }
    }

}


