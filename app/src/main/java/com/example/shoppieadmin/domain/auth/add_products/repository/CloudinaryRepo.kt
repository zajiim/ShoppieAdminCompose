package com.example.shoppieadmin.domain.auth.add_products.repository

import android.net.Uri

interface CloudinaryRepo {
    suspend fun uploadImage(imageUri: Uri): String
}