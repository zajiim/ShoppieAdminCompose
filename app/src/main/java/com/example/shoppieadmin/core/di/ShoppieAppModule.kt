package com.example.shoppieadmin.core.di

import com.example.shoppieadmin.domain.auth.login.use_cases.ValidationEmailUseCase
import com.example.shoppieadmin.domain.auth.login.use_cases.ValidationPasswordUseCase
import com.example.shoppieadmin.domain.auth.login.use_cases.ValidationUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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


}