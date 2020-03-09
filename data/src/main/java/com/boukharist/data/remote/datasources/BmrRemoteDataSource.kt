package com.boukharist.data.remote.datasources

import com.boukharist.data.remote.models.BmrRequest
import com.boukharist.data.remote.models.BmrResponse
import com.boukharist.data.remote.retrofit.BmrService
import com.boukharist.domain.model.CallResult
import retrofit2.Callback
import retrofit2.HttpException
import java.io.IOException

interface BmrRemoteDataSource {
    fun computeBmr(bmrRequest: BmrRequest): CallResult<BmrResponse, Exception>
}

class BmrRemoteDataSourceImpl(private val bmrService: BmrService) : BmrRemoteDataSource {
    override fun computeBmr(bmrRequest: BmrRequest): CallResult<BmrResponse, Exception> {
        val response = bmrService.computeBmr(bmrRequest.hashCode()).execute()
        return if (response.isSuccessful) {
            val bmrResponse = response.body()!!
            CallResult.success(bmrResponse)
        } else {
            CallResult.failure(HttpException(response))
        }
    }
}

class BmrRemoteDataSourceFakeImpl : BmrRemoteDataSource {
    companion object {
        val ACTIVITIES_ENUM = listOf(1.0, 1.35, 1.7)
    }

    override fun computeBmr(bmrRequest: BmrRequest): CallResult<BmrResponse, Exception> = with(bmrRequest) {
        Thread.sleep(1000)
        val genderGap = if (this.gender == 0) 5 else -161
        val bmr = (10 * weight) + (6.25 * height) - (5 * age) + genderGap
        val activityMeter = ACTIVITIES_ENUM[activityType]
        val tdee = bmr * activityMeter
        val caloriesIntake = when (goal) {
            0 -> tdee - 500
            1 -> tdee
            2 -> tdee + 500
            else -> tdee
        }
        val response = BmrResponse(bmr = bmr, tdee = tdee, caloriesIntake = caloriesIntake)
        CallResult.success(response)
    }
}