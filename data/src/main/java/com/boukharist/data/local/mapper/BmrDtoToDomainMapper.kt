package com.boukharist.data.local.mapper

import com.boukharist.data.local.models.BmrDto
import com.boukharist.data.remote.models.BmrRequest
import com.boukharist.domain.model.HealthInfo

class BmrDtoToDomainMapper : (BmrDto) -> HealthInfo {
    override fun invoke(dto: BmrDto): HealthInfo {
        return HealthInfo(dto.bmr, dto.tdee, dto.caloriesIntake)
    }
}
