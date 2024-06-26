package com.example.shoppieadmin.core.di

import android.app.Application
import com.example.shoppieadmin.data.add_products.repository.CloudinaryRepoImpl
import com.example.shoppieadmin.data.datamanager.LocalUserManagerImpl
import com.example.shoppieadmin.data.remote.api.ShoppieApi
import com.example.shoppieadmin.data.repository.GetProductsRepoImpl
import com.example.shoppieadmin.data.repository.ShoppieRepoImpl
import com.example.shoppieadmin.domain.add_products.repository.CloudinaryRepo
import com.example.shoppieadmin.domain.add_products.use_cases.UploadImageUseCase
import com.example.shoppieadmin.domain.main.datamanager.LocalUserManager
import com.example.shoppieadmin.domain.auth.login.repository.ShoppieRepo
import com.example.shoppieadmin.domain.main.use_cases.ReadTokenUseCase
import com.example.shoppieadmin.domain.main.use_cases.SaveTokenUseCase
import com.example.shoppieadmin.domain.main.use_cases.TokenUseCases
import com.example.shoppieadmin.domain.auth.login.use_cases.ValidationEmailUseCase
import com.example.shoppieadmin.domain.auth.login.use_cases.ValidationPasswordUseCase
import com.example.shoppieadmin.domain.auth.login.use_cases.ValidationUseCases
import com.example.shoppieadmin.domain.home.repository.paging.GetProductsRepo
import com.example.shoppieadmin.domain.home.use_cases.GetAllProductsUseCase
import com.example.shoppieadmin.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ShoppieAppModule {

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun provideShoppieApi(): ShoppieApi {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(ShoppieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepo(api: ShoppieApi): ShoppieRepo {
        return ShoppieRepoImpl(api)
    }


    @Provides
    @Singleton
    fun provideValidationEmailUseCase(): ValidationEmailUseCase {
        return ValidationEmailUseCase()
    }

    @Provides
    @Singleton
    fun provideValidationPasswordUseCase(): ValidationPasswordUseCase {
        return ValidationPasswordUseCase()
    }

    @Provides
    @Singleton
    fun provideValidationUseCases(
        validationEmailUseCase: ValidationEmailUseCase,
        validationPasswordUseCase: ValidationPasswordUseCase
    ): ValidationUseCases {
        return ValidationUseCases(validationEmailUseCase, validationPasswordUseCase)
    }


    @Provides
    @Singleton
    fun provideLocalDataManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideTokenUseCases(
        localUserManager: LocalUserManager
    ): TokenUseCases = TokenUseCases(
        SaveTokenUseCase(localUserManager),
        ReadTokenUseCase(localUserManager)
    )

    @Provides
    @Singleton
    fun providesCloudinaryRepository(): CloudinaryRepo {
        return CloudinaryRepoImpl()
    }

    @Provides
    @Singleton
    fun provideUploadImageUseCase(cloudinaryRepo: CloudinaryRepo): UploadImageUseCase {
        return UploadImageUseCase(cloudinaryRepo)
    }

    @Provides
    @Singleton
    fun provideGetProductsRepo(
        shoppieApi: ShoppieApi,
        localUserManager: LocalUserManager
    ): GetProductsRepo = GetProductsRepoImpl(shoppieApi, localUserManager)

    @Provides
    @Singleton
    fun provideGetAllProductsUseCase(
        getProductsRepo: GetProductsRepo
    ): GetAllProductsUseCase = GetAllProductsUseCase(getProductsRepo)

}