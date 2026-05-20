package com.valentinesgarage.di;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.valentinesgarage.data.repository.TruckRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation"
})
public final class RepositoryModule_ProvideTruckRepositoryFactory implements Factory<TruckRepository> {
  private final Provider<FirebaseFirestore> firestoreProvider;

  private final Provider<FirebaseStorage> storageProvider;

  public RepositoryModule_ProvideTruckRepositoryFactory(
      Provider<FirebaseFirestore> firestoreProvider, Provider<FirebaseStorage> storageProvider) {
    this.firestoreProvider = firestoreProvider;
    this.storageProvider = storageProvider;
  }

  @Override
  public TruckRepository get() {
    return provideTruckRepository(firestoreProvider.get(), storageProvider.get());
  }

  public static RepositoryModule_ProvideTruckRepositoryFactory create(
      Provider<FirebaseFirestore> firestoreProvider, Provider<FirebaseStorage> storageProvider) {
    return new RepositoryModule_ProvideTruckRepositoryFactory(firestoreProvider, storageProvider);
  }

  public static TruckRepository provideTruckRepository(FirebaseFirestore firestore,
      FirebaseStorage storage) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideTruckRepository(firestore, storage));
  }
}
