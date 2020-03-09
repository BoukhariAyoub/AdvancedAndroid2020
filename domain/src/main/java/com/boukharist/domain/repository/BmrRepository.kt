package com.boukharist.domain.repository

import com.boukharist.domain.model.*

interface BmrRepository {
    fun computeBmr(user: User): CallResult<HealthInfo, BmrException>
}

