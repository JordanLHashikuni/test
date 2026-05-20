package com.valentinesgarage.ui.jobs;

import com.valentinesgarage.data.repository.JobRepository;
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
public final class JobListViewModel_Factory implements Factory<JobListViewModel> {
  private final Provider<JobRepository> jobRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  public JobListViewModel_Factory(Provider<JobRepository> jobRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    this.jobRepositoryProvider = jobRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public JobListViewModel get() {
    return newInstance(jobRepositoryProvider.get(), userRepositoryProvider.get());
  }

  public static JobListViewModel_Factory create(Provider<JobRepository> jobRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    return new JobListViewModel_Factory(jobRepositoryProvider, userRepositoryProvider);
  }

  public static JobListViewModel newInstance(JobRepository jobRepository,
      UserRepository userRepository) {
    return new JobListViewModel(jobRepository, userRepository);
  }
}
