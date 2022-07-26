package com.tghc.recipebook.common


const val TAG = "TAG_RECIPE"

const val dateFormat = "dd/MM/yyyy"
const val timestamp = "yyyy-MM-dd HH:mm:ss"

const val SIZE_ING = 5
const val SIZE_PRO = 3

val UNITS = arrayOf("no.", "piece", "tsp", "tbsp", "mg", "g", "kg", "ml", "L", "cup", "gl")
val SERVE_TYPE = arrayOf("no.", "piece", "people", "plate", "cup", "bowl", "glass", "gram", "kg", "ml", "liter")
val OVERFLOW_TYPE = arrayOf("Edit", "Delete")

const val KEY_ID = "KEY_ID"
const val KEY_DATA = "KEY_DATA"
const val KEY_USER_PREFERENCE = "KEY_USER_PREFERENCE"
const val KEY_SHARED_PREFERENCE = "KEY_SHARED_PREFERENCE"
const val KEY_POSITION = "KEY_POSITION"
const val KEY_PROFILE_URL = "KEY_PROFILE_URL"

const val FIELD_TIMESTAMP = "timeStamp"

const val MSG_FIREBASE_ERROR = "An Error Occurred"
const val MSG_RECIPE_SAVED = "Recipe Saved"
const val MSG_ADD_TITLE = "Please Enter Title"
const val MSG_EXIT = "Exit Without Saving?"
const val MSG_DELETE ="Are you sure you want to delete this Recipe?"

const val flagUpdate = false
const val details = "Details"
const val ingredients = "Ingredients"
const val procedure = "Procedure"
const val cuisine = "Cuisine"
const val servings = "Servings"
const val pTime = "Preparation Time"
const val cTime = "Cooking Time"
const val images = "Images"
const val save = "Save"
const val REQUEST_CODE_IMAGE = 550
const val PICK_IMAGE_REQUEST = 300

const val userId = "userId"
const val posts = "posts"

const val RECIPE_DATABASE = "recipe_database"
const val RECIPE_TABLE = "recipe_table"
const val USER_TABLE = "user_table"

const val COLLECTION_RECIPE = "recipe"
const val COLLECTION_COUNTER = "counter"

enum class Status {
    LOADING, SUCCESS, ERROR, OFFLINE
}

enum class Direction {
    START, TOP, END, BOTTOM
}