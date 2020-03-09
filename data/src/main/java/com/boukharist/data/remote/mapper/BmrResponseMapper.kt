package com.boukharist.data.remote.mapper

import com.boukharist.data.remote.models.BmrResponse
import com.boukharist.domain.model.HealthInfo

class BmrResponseMapper : (BmrResponse) -> HealthInfo {

    override fun invoke(response: BmrResponse): HealthInfo {
        return HealthInfo(bmr = response.bmr, tdee = response.tdee, caloriesIntake = response.caloriesIntake)
    }
}


