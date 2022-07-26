package com.tghc.recipebook.data.remote.dto

import java.util.*

data class UserDto(
    var username: String = "",
    var name: String = "",
    var phone: String = "",
    var gender: String = "",
    var dob: Date? = null,
    var active: Boolean = false,
    var profile_url: String = "",
    var bio: String = "",
    var mealType: MealType = MealType.DEFAULT,
)

enum class MealType {
    VEGAN, EGG, VEG, NON_VEG, DEFAULT
}