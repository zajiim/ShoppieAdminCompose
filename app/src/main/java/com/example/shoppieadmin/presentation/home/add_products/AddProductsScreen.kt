package com.example.shoppieadmin.presentation.home.add_products

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.shoppieadmin.presentation.auth.login.components.CustomButton
import com.example.shoppieadmin.presentation.home.add_products.components.ProductsDetailsContainer
import com.example.shoppieadmin.ui.theme.Orange
import com.example.shoppieadmin.ui.theme.PrimaryColor
import com.example.shoppieadmin.ui.theme.PrimaryDark
import com.example.shoppieadmin.utils.Resource
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun AddProductsScreen(
    viewModel: AddProductsViewModel = hiltViewModel(),
    navController: NavHostController
    ) {

    val images by viewModel.images.collectAsState()
    val uploadResult by viewModel.uploadResults.collectAsState()

    val pickImages = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents()
    ) { uris ->
        viewModel.addImages(uris)
    }

    val pagerState = rememberPagerState(initialPage = 0, pageCount = { images.size })
    val imageUrlList = remember { mutableStateListOf<String>() }
    if (uploadResult is Resource.Success) {
        imageUrlList.clear()
        imageUrlList.addAll((uploadResult as Resource.Success<List<String>>).data ?: emptyList())
    }

    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()


    Scaffold(
        topBar = {
        TopAppBar(title = {
            Text(text = "Top App Bar")
        }, navigationIcon = {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(Icons.Filled.ArrowBack, "backIcon")
            }
        }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = PrimaryColor
        ))
    },
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {

            if (viewModel.productsState.navigateToBack) {
                coroutineScope.launch {
                    snackBarHostState.showSnackbar(
                        "Product added"
                    )

                    navController.navigateUp()
                }
            }
            item {
                val stroke = Stroke(
                    width = 2f, pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                )
                Box(
                    modifier = Modifier
                        .height(240.dp)
                        .fillMaxWidth()
                        .padding(vertical = 6.dp, horizontal = 24.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .drawBehind {
                            drawRoundRect(
                                color = Color.Gray,
                                style = stroke,
                                cornerRadius = CornerRadius(24.dp.toPx())
                            )
                        }
                        .clickable { pickImages.launch("image/*") }) {
                    if (images.isNotEmpty()) {
                        HorizontalPager(
                            state = pagerState, modifier = Modifier.fillMaxSize()
                        ) { page: Int ->
                            Image(
                                painter = rememberAsyncImagePainter(
                                    model = images[page]
                                ),
                                contentDescription = null,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )

                        }
                    } else {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = null,
                                modifier = Modifier
                            )
                            Text(text = "Add Products")
                        }
                    }

                    if ((uploadResult as? Resource.Loading)?.isLoading == true) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(PrimaryColor.copy(alpha = 0.1f))
                                .align(Alignment.Center)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(
                                    Alignment.Center
                                )
                            )
                        }
                    }
                }
            }

            item {
                ProductsDetailsContainer(
                    nameValue = { viewModel.productsState.nameInput },
                    onNameChanged = viewModel::onNameChange,
                    descriptionValue = { viewModel.productsState.descriptionInput },
                    onDescriptionChanged = viewModel::onDescriptionChange,
                    priceValue = { viewModel.productsState.price },
                    onPriceChanged = viewModel::onPriceChange,
                    countValue = { viewModel.productsState.quantity },
                    onCountChanged = viewModel::onQuantityChange,
                    categoryValue = { viewModel.productsState.productCategory },
                    onCategoryChanged = viewModel::onCategoryChange,
                    cursorColor = PrimaryDark,
                )
            }
            item {
                CustomButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 12.dp),
                    text = "Upload",
                    backgroundColor = Orange,
                    contentColor = Color.White,
                    onButtonClicked =  viewModel::onUploadButtonClick,
                    isLoading = viewModel.productsState.isLoading
                )
            }

            item {
                Spacer(modifier = Modifier.height(180.dp))
            }
        }

    }
}

