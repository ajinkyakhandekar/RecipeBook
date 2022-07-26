package com.tghc.recipebook.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tghc.recipebook.common.USER_TABLE

@Entity(tableName = USER_TABLE)
data class UserEntity(
    @PrimaryKey
    var position: String = "",
    var points: String = "",
    var constructorName: String = "",
    var driverName: String = "",
)