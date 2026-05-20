package com.valentinesgarage.di;

import com.google.firebase.firestore.FirebaseFirestore;
import com.valentinesgarage.data.repository.JobRepository;
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
public final class RepositoryModule_ProvideJobRepositoryFactory implements Factory<JobRepository> {
  private final Provider<FirebaseFirestore> firestoreProvider;

  public RepositoryModule_ProvideJobRepositoryFactory(
      Provider<FirebaseFirestore> firestoreProvider) {
    this.firestoreProvider = firestoreProvider;
  }

  @Override
  public JobRepository get() {
    return provideJobRepository(firestoreProvider.get());
  }

  public static RepositoryModule_ProvideJobRepositoryFactory create(
      Provider<FirebaseFirestore> firestoreProvider) {
    return new RepositoryModule_ProvideJobRepositoryFactory(firestoreProvider);
  }

  public static JobRepository provideJobRepository(FirebaseFirestore firestore) {
    return Preconditions.checkNotNullFromProvides(RepositoryModule.INSTANCE.provideJobRepository(firestore));
  }
}
