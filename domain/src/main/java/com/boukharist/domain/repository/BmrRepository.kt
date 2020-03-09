package com.boukharist.domain.repository

import com.boukharist.domain.model.*
import java.lang.Exception

interface BmrRepository {
    fun computeBmr(user: User): CallResult<Bmr, BmrException>
}

