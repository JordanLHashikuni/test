package com.valentinesgarage.di

import com.valentinesgarage.data.repository.JobRepository
import com.valentinesgarage.data.repository.TruckRepository
import com.valentinesgarage.data.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module that provides repository instances.
 *
 * Repositories are @Singleton so a single instance is shared across
 * all ViewModels — this ensures consistent data and avoids duplicate
 * Firestore listeners being registered.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(
        auth: FirebaseAuth,
        firestore: FirebaseFirestore
    ): UserRepository = UserRepository(auth, firestore)

    @Provides
    @Singleton
    fun provideTruckRepository(
        firestore: FirebaseFirestore,
        storage: FirebaseStorage
    ): TruckRepository = TruckRepository(firestore, storage)

    @Provides
    @Singleton
    fun provideJobRepository(
        firestore: FirebaseFirestore
    ): JobRepository = JobRepository(firestore)
}
