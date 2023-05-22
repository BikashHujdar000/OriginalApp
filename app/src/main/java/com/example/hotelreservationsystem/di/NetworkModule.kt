package com.example.hotelreservationsystem.di

import com.example.hotelreservationsystem.api.OwnerApi
import com.example.hotelreservationsystem.utils.constants.BASEURL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASEURL)
            .build()
    }
    @Provides
    @Singleton
    fun ProvidesOwnerApi(retrofit: Retrofit):OwnerApi
    {
        return  retrofit.create(OwnerApi::class.java)
    }



}