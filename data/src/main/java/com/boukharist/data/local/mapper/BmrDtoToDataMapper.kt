package com.boukharist.data.local.mapper

import com.boukharist.data.local.models.BmrDto
import com.boukharist.data.remote.models.BmrRequest
import com.boukharist.domain.model.HealthInfo

class BmrDtoToDataMapper : (HealthInfo, BmrRequest) -> BmrDto {

    override fun invoke(info: HealthInfo, request: BmrRequest): BmrDto {
        return BmrDto(
            bmrRequestHash = request.hashCode(),
            bmr = info.bmr,
            tdee = info.tdee,
            caloriesIntake = info.caloriesIntake
        )
    }

}