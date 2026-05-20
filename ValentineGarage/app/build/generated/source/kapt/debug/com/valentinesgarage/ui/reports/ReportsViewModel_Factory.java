package com.valentinesgarage.ui.reports;

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
public final class ReportsViewModel_Factory implements Factory<ReportsViewModel> {
  private final Provider<JobRepository> jobRepositoryProvider;

  private final Provider<UserRepository> userRepositoryProvider;

  public ReportsViewModel_Factory(Provider<JobRepository> jobRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    this.jobRepositoryProvider = jobRepositoryProvider;
    this.userRepositoryProvider = userRepositoryProvider;
  }

  @Override
  public ReportsViewModel get() {
    return newInstance(jobRepositoryProvider.get(), userRepositoryProvider.get());
  }

  public static ReportsViewModel_Factory create(Provider<JobRepository> jobRepositoryProvider,
      Provider<UserRepository> userRepositoryProvider) {
    return new ReportsViewModel_Factory(jobRepositoryProvider, userRepositoryProvider);
  }

  public static ReportsViewModel newInstance(JobRepository jobRepository,
      UserRepository userRepository) {
    return new ReportsViewModel(jobRepository, userRepository);
  }
}
