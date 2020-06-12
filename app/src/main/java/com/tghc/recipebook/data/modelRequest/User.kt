package com.tghc.recipebook.data.modelRequest

import java.util.*

data class User (
        var username:String="",
        var name:String="",
        var phone:String="",
        var gender:String="",
        var dob:Date?=null,
        var active:Boolean=false,
        var profile_url:String="",
        var bio:String="",
        var typeVeg:Boolean=false,
        var typeNv:Boolean=false,
        var typeEgg:Boolean=false,
        var typeVegan:Boolean=false
//        var type:MutableList<Boolean> = ArrayList()
)