package com.tghc.recipebook.constant


const val TAG = "KINGSMILL"

const val dateFormat = "dd/MM/yyyy"
const val timestamp = "yyyy-MM-dd HH:mm:ss"

const val SIZE_ING = 5
const val SIZE_PRO = 3

val UNITS = arrayOf("no.", "piece", "tsp", "tbsp", "mg", "g", "kg", "ml", "L", "cup", "gl")
val SERVE_TYPE = arrayOf("no.", "piece", "people", "plate", "cup", "bowl", "glass", "gram", "kg", "ml", "liter")
const val KEY_ID = "KEY_ID"
const val KEY_DATA = "KEY_DATA"
const val KEY_USER_PREFERENCE = "KEY_USER_PREFERENCE"
const val KEY_SHARED_PREFERENCE = "KEY_SHARED_PREFERENCE"
const val KEY_POSITION = "KEY_POSITION"
const val KEY_PROFILE_URL = "KEY_PROFILE_URL"
val OVERFLOW_TYPE = arrayOf("Edit", "Delete")

const val FIELD_TIMESTAMP = "timeStamp"

const val flagUpdate = false
const val details = "Details"
const val ingredients = "Ingredients"
const val procedure = "Procedure"
const val cusine = "Cusine"
const val servings = "Servings"
const val pTime = "Preparation Time"
const val cTime = "Cooking Time"
const val images = "Images"
const val save = "Save"
const val REQUEST_CODE_IMAGE = 500
const val PICK_IMAGE_REQUEST = 300


const val userId = "userId"
const val posts = "posts"


enum class Status {
    LOADING, SUCCESS, ERROR, OFFLINE
}

enum class Direction {
    START, TOP, END, BOTTOM
}
