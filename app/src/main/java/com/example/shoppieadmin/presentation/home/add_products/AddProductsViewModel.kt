package com.example.shoppieadmin.presentation.home.add_products

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppieadmin.domain.auth.add_products.use_cases.UploadImageUseCase
import com.example.shoppieadmin.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddProductsViewModel @Inject constructor(
    private val uploadImageUseCase: UploadImageUseCase
): ViewModel() {

    var productsState by mutableStateOf<AddProductsState>(AddProductsState())
        private set

    private val _images = MutableStateFlow<List<Uri>>(emptyList())
    val images: StateFlow<List<Uri>> = _images.asStateFlow()

    private val _uploadResults = MutableStateFlow<Resource<List<String>>>(Resource.Loading(isLoading = false))
    val uploadResults = _uploadResults.asStateFlow()



    fun onNameChange(newValue: String) {
        productsState = productsState.copy(
            nameInput = newValue
        )
    }

    fun onDescriptionChange(newValue: String) {
        productsState = productsState.copy(
            descriptionInput = newValue
        )
    }

    fun onPriceChange(newValue: Int) {
        productsState = productsState.copy(
            price = newValue
        )
    }

    fun onQuantityChange(newValue: Int) {
        productsState = productsState.copy(
            quantity = newValue
        )
    }

    fun onCategoryChange(newValue: String) {
        productsState = productsState.copy(
            productCategory = newValue
        )
    }




    //Add Images to cloudinary
    fun addImages(imageUris: List<Uri>) {
        _images.value = imageUris
        viewModelScope.launch {
            _uploadResults.value = Resource.Loading(isLoading = true)
            val urls = mutableListOf<String>()
            try {
                for (image in imageUris) {
                    val url = uploadImageUseCase(image)
                    urls.add(url)
                }
                _uploadResults.value = Resource.Success(urls)
            } catch (e: Exception) {
                _uploadResults.value = Resource.Error("Upload failed due to ${e.message}")
            } finally {
                _uploadResults.value = Resource.Loading(isLoading = false)
            }
        }
    }
}