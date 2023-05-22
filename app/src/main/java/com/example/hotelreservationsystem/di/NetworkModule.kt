package com.example.hotelreservationsystem.di

import com.example.hotelreservationsystem.api.AuthInterceptors
import com.example.hotelreservationsystem.api.HotelsApi
import com.example.hotelreservationsystem.api.OwnerApi
import com.example.hotelreservationsystem.utils.constants.BASEURL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {


    // on approaching for same function o adding clents 1
    //1.returning retrofit builder instead of retrofit and remove builde ()
    //

    @Singleton
    @Provides
    fun providesRetrofitBuilder(): Retrofit.Builder{
        return  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASEURL)
        //.build()
    }

    @Singleton
    @Provides
    fun provideOkHTTPClient(authInterceptors: AuthInterceptors) : OkHttpClient{
        return OkHttpClient.Builder().addInterceptor(authInterceptors).build()
    }


    @Singleton
    @Provides
    fun providesOwnerAPI(retrofitBuilder: Retrofit.Builder):OwnerApi{
        return  retrofitBuilder.build().create(OwnerApi::class.java)
    }

    @Singleton
    @Provides
    fun providesHotelApi(retrofitBuilder: Retrofit.Builder,okHttpClient: OkHttpClient):HotelsApi{
        return  retrofitBuilder
            .client(okHttpClient)
            .build().create(HotelsApi::class.java)
    }
}