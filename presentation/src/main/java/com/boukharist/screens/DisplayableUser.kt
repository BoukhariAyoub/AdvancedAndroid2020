package com.boukharist.mvarchi

data class DisplayableUser(val fullName: String, val age: Int,val ageColor : Color)
data class FormFields(val firstName: String, val lastName: String, val birthDate: String)

enum class Color{
    RED , BLUE
}