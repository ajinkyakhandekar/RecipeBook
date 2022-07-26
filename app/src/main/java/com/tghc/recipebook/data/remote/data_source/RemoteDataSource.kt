package com.tghc.recipebook.data.remote.data_source

import com.tghc.recipebook.data.remote.service.FirebaseService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val firebaseService: FirebaseService) {

    suspend fun getRecipeList() = firebaseService.getRecipeListService()

   suspend fun getRecipeCount() = firebaseService.getRecipeCount()

//    suspend fun getSchedule() = firebaseService.getSchedule()
//
//    suspend fun getDriverStandings() = firebaseService.getDriverStandings()
//
//    suspend fun getConstructorStandings() = firebaseService.getConstructorStandings()

}