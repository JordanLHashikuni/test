package com.valentinesgarage.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module that provides Firebase service instances.
 *
 * By providing these as @Singleton, we ensure only one instance of each
 * Firebase service exists across the entire app — which is both correct
 * (Firebase SDKs are designed this way) and efficient.
 *
 * Any class that needs Firebase just declares it as a constructor parameter
 * and Hilt injects it automatically — no manual instantiation needed.
 */
@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    /**
     * Provides the FirebaseAuth instance for login/logout operations.
     * Injected into [UserRepository].
     */
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    /**
     * Provides the FirebaseFirestore instance for all database operations.
     * Injected into [UserRepository], [TruckRepository], and [JobRepository].
     */
    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    /**
     * Provides the FirebaseStorage instance for vehicle photo uploads.
     * Injected into [TruckRepository].
     */
    @Provides
    @Singleton
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()
}
