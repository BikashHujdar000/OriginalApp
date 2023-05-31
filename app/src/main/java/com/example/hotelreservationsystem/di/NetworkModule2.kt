package com.example.hotelreservationsystem.di

import com.example.hotelreservationsystem.api.okayTestApio
import com.example.hotelreservationsystem.utils.constants.BASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module

class NetworkModule2 {

    @Singleton
    @Provides
    fun providesRetrofit2 () :Retrofit{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE)
            .build()
    }

    @Singleton
    @Provides
    fun providesokaytestApi(retrofit: Retrofit):okayTestApio{
        return retrofit.create(okayTestApio::class.java)

    }


}