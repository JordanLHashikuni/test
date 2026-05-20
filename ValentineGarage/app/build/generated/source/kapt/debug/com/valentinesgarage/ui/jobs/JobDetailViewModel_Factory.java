package com.valentinesgarage.ui.jobs;

import com.valentinesgarage.data.repository.JobRepository;
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
public final class JobDetailViewModel_Factory implements Factory<JobDetailViewModel> {
  private final Provider<JobRepository> jobRepositoryProvider;

  private final Provider<TruckRepository> truckRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  public JobDetailViewModel_Factory(Provider<JobRepository> jobRepositoryProvider,
      Provider<TruckRepository> truckRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    this.jobRepositoryProvider = jobRepositoryProvider;
    this.truckRepositoryProvider = truckRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public JobDetailViewModel get() {
    return newInstance(jobRepositoryProvider.get(), truckRepositoryProvider.get(), userRepositoryProvider.get());
  }

  public static JobDetailViewModel_Factory create(Provider<JobRepository> jobRepositoryProvider,
      Provider<TruckRepository> truckRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    return new JobDetailViewModel_Factory(jobRepositoryProvider, truckRepositoryProvider, userRepositoryProvider);
  }

  public static JobDetailViewModel newInstance(JobRepository jobRepository,
      TruckRepository truckRepository, UserRepository userRepository) {
    return new JobDetailViewModel(jobRepository, truckRepository, userRepository);
  }
}
