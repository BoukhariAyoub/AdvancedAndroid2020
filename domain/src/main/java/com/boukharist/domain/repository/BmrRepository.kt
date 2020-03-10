package com.boukharist.domain.repository

import com.boukharist.domain.model.*
import kotlinx.coroutines.flow.Flow

interface BmrRepository {
    fun computeBmr(user: User): Flow<CallResult<HealthInfo, BmrException>>
}

