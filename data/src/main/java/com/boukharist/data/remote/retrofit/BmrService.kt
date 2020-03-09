package com.boukharist.data.remote.retrofit

import com.boukharist.data.remote.models.BmrResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface BmrService {
    @GET("some_dummy_repo")
    fun computeBmr(@Path("brmRequest") bmrRequest: Int): Call<BmrResponse>
}