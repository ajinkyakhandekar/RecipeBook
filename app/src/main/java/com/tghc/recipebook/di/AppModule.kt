package com.tghc.recipebook.di

import android.content.Context
import androidx.room.Room
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.tghc.recipebook.common.RECIPE_DATABASE
import com.tghc.recipebook.data.local.data_source.LocalDataSource
import com.tghc.recipebook.data.local.database.RecipeDao
import com.tghc.recipebook.data.local.database.RecipeDatabase
import com.tghc.recipebook.data.remote.data_source.RemoteDataSource
import com.tghc.recipebook.data.remote.service.FirebaseService
import com.tghc.recipebook.data.repo.RecipeRepositoryImpl
import com.tghc.recipebook.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // ----------------- Remote Data Source -------------------- //

    @Provides
    @Singleton
    fun provideFirebaseFirestore() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseStorage() = FirebaseStorage.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseService(
        firebaseFirestore: FirebaseFirestore,
        firebaseStorage: FirebaseStorage
    ) = FirebaseService(firebaseFirestore, firebaseStorage)

    @Provides
    @Singleton
    fun provideRemoteDataSource(firebaseService: FirebaseService) =
        RemoteDataSource(firebaseService)


    // ----------------- Local Data Source -------------------- //

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        RecipeDatabase::class.java,
        RECIPE_DATABASE
    ).build()


    @Singleton
    @Provides
    fun provideDao(db: RecipeDatabase): RecipeDao = db.recipeDao()

    @Provides
    @Singleton
    fun provideLocalDataSource(recipeDao: RecipeDao) = LocalDataSource(recipeDao)


    // ----------------- Repository -------------------- //

    @Provides
    @Singleton
    fun provideRecipeRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource
    ) = RecipeRepositoryImpl(localDataSource, remoteDataSource)


    // ----------------- Use Cases -------------------- //

    // --------- Recipe Count -------- //
    @Provides
    @Singleton
    fun provideGetRecipeCountLocalUseCase(recipeRepositoryImpl: RecipeRepositoryImpl) =
        GetRecipeCountLocalUseCase(recipeRepositoryImpl)

    @Provides
    @Singleton
    fun provideGetRecipeCountRemoteUseCase(recipeRepositoryImpl: RecipeRepositoryImpl) =
        GetRecipeCountRemoteUseCase(recipeRepositoryImpl)

    @Provides
    @Singleton
    fun provideCountUseCase(
        getRecipeCountLocalUseCase: GetRecipeCountLocalUseCase,
        getRecipeCountRemoteUseCase: GetRecipeCountRemoteUseCase
    ) = IsUpdateRequiredUseCase(getRecipeCountLocalUseCase, getRecipeCountRemoteUseCase)


    // --------- Recipe List -------- //

    @Provides
    @Singleton
    fun provideRecipeListLocalUseCase(recipeRepositoryImpl: RecipeRepositoryImpl) =
        GetRecipeListLocalUseCase(recipeRepositoryImpl)

    @Provides
    @Singleton
    fun provideRecipeListRemoteUseCase(recipeRepositoryImpl: RecipeRepositoryImpl) =
        GetRecipeListRemoteUseCase(recipeRepositoryImpl)

    @Provides
    @Singleton
    fun provideRecipeListUseCase(
        isUpdateRequiredUseCase: IsUpdateRequiredUseCase,
        getRecipeCountLocalUseCase: GetRecipeListLocalUseCase,
        getRecipeListRemoteUseCase: GetRecipeListRemoteUseCase
    ) =
        GetRecipeListUseCase(
            isUpdateRequiredUseCase,
            getRecipeCountLocalUseCase,
            getRecipeListRemoteUseCase
        )

    // --------- Recipe Item -------- //

    @Provides
    @Singleton
    fun provideRecipeUseCase(recipeRepositoryImpl: RecipeRepositoryImpl) =
        GetRecipeUseCase(recipeRepositoryImpl)
}