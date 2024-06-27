package com.example.shoppieadmin.data.add_products.repository

import android.net.Uri
import android.util.Log
import com.cloudinary.android.MediaManager
import com.cloudinary.android.callback.ErrorInfo
import com.cloudinary.android.callback.UploadCallback
import com.example.shoppieadmin.domain.auth.add_products.repository.CloudinaryRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resumeWithException

class CloudinaryRepoImpl @Inject constructor(): CloudinaryRepo {
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun uploadImage(imageUri: Uri): String {
        return suspendCancellableCoroutine { continuation ->
            Log.e("tag_add_product_viewmodel", "upload fn " )
            MediaManager.get().upload(imageUri).unsigned("lqvxamnz")
                .option("folder", "productImages")
                .callback(object: UploadCallback {
                    override fun onStart(requestId: String?) {
                        Log.e("tag_add_product_viewmodel", "inside onstart " )
                    }

                    override fun onProgress(requestId: String?, bytes: Long, totalBytes: Long) {
                        Log.e("tag_add_product_viewmodel", "inside onprogress " )
                    }

                    override fun onSuccess(
                        requestId: String?,
                        resultData: MutableMap<Any?, Any?>?
                    ) {
                        Log.e("tag_add_product_viewmodel", "inside onsuccess " )
                        val url = resultData?.get("url").toString()
                        continuation.resume(url, null)
                    }

                    override fun onError(requestId: String?, error: ErrorInfo?) {
                        continuation.resumeWithException(Exception(error?.description))
                        Log.e("tag_add_product_viewmodel", "inside onerror " )
                    }

                    override fun onReschedule(requestId: String?, error: ErrorInfo?) {
                        Log.e("tag_add_product_viewmodel", "inside onrechedule " )
                    }

                }).dispatch()
        }
    }
}