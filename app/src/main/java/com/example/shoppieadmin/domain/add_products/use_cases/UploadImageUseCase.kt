package com.example.shoppieadmin.domain.add_products.use_cases

import android.net.Uri
import com.example.shoppieadmin.domain.add_products.repository.CloudinaryRepo
import javax.inject.Inject

class UploadImageUseCase @Inject constructor(
    private val cloudinaryRepo: CloudinaryRepo
) {
    suspend operator fun invoke(imageUri: Uri): String {
        return cloudinaryRepo.uploadImage(imageUri)
    }
}