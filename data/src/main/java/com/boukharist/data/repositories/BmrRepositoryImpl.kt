package com.boukharist.data.repositories

import com.boukharist.data.remote.datasources.BmrRemoteDataSource
import com.boukharist.data.local.datasources.BmrLocalDataSource
import com.boukharist.data.local.mapper.BmrDtoToDomainMapper
import com.boukharist.data.remote.mapper.BmrRequestMapper
import com.boukharist.data.remote.mapper.BmrResponseMapper
import com.boukharist.data.remote.models.BmrRequest
import com.boukharist.domain.model.*
import com.boukharist.domain.repository.BmrRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class BmrRepositoryImpl(
    private val remoteDataSource: BmrRemoteDataSource,
    private val localDataSource: BmrLocalDataSource,
    private val bmrRequestMapper: BmrRequestMapper,
    private val bmrDtoToDomainMapper: BmrDtoToDomainMapper,
    private val healthInfoMapper: BmrResponseMapper
) : BmrRepository {

    override fun computeBmr(user: User): Flow<CallResult<HealthInfo, BmrException>> = flow {
        try {
            val bmrRequest = user.let(bmrRequestMapper)
            localResponse(bmrRequest) ?: remoteResponse(bmrRequest)
        } catch (throwable: Throwable) {
            val bmrException = BmrComputationException(throwable.localizedMessage ?: "")
            CallResult.failure(bmrException)
        }
    }

    private fun localResponse(bmrRequest: BmrRequest): CallResult<HealthInfo, BmrException>? {
        val localBmr = localDataSource.getBmrById(bmrRequest.hashCode())?.let(bmrDtoToDomainMapper)
        return if (localBmr != null) {
            CallResult.success(localBmr)
        } else {
            null
        }
    }

    private fun remoteResponse(bmrRequest: BmrRequest): CallResult<HealthInfo, BmrException> {
        return when (val callResult = remoteDataSource.computeBmr(bmrRequest)) {
            is Success -> CallResult.success(callResult.value.let(healthInfoMapper))
            is Failure -> CallResult.failure(BmrComputationException(callResult.reason.localizedMessage ?: ""))
        }
    }
}