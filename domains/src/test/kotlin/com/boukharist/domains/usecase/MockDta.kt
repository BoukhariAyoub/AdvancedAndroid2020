package com.boukharist.domains.usecase

import com.boukharist.domains.entities.User
import java.util.*

object MockDta {


    const val FIRST_NAME = "first"
    const val LAST_NAME = "last"

    //Monday, March 12, 1990 11:03:49 AM
    val BIRTH_DATE = Date(637239829000L)


    val USER_MOCK = User(firstName = "first", lastName = "last", birthDate = BIRTH_DATE)
    val USER_MOCK_2 = User(firstName = "second", lastName = "second", birthDate = BIRTH_DATE)
}