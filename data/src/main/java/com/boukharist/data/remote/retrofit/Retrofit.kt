package com.boukharist.data.remote.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object Retrofit {
    var instance: Retrofit = Retrofit.Builder()
        .baseUrl("some_dummy_url") //TODO change here
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
