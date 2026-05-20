package com.valentinesgarage.ui.checkin;

import com.valentinesgarage.data.repository.TruckRepository;
import com.valentinesgarage.data.repository.UserRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
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
public final class CheckInViewModel_Factory implements Factory<CheckInViewModel> {
  private final Provider<TruckRepository> truckRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  public CheckInViewModel_Factory(Provider<TruckRepository> truckRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    this.truckRepositoryProvider = truckRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public CheckInViewModel get() {
    return newInstance(truckRepositoryProvider.get(), userRepositoryProvider.get());
  }

  public static CheckInViewModel_Factory create(Provider<TruckRepository> truckRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    return new CheckInViewModel_Factory(truckRepositoryProvider, userRepositoryProvider);
  }

  public static CheckInViewModel newInstance(TruckRepository truckRepository,
      UserRepository userRepository) {
    return new CheckInViewModel(truckRepository, userRepository);
  }
}
