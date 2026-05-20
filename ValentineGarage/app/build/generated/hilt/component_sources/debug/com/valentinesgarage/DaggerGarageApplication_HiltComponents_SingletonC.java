package com.valentinesgarage;

import android.app.Activity;
import android.app.Service;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.valentinesgarage.data.repository.JobRepository;
import com.valentinesgarage.data.repository.TruckRepository;
import com.valentinesgarage.data.repository.UserRepository;
import com.valentinesgarage.di.FirebaseModule_ProvideFirebaseAuthFactory;
import com.valentinesgarage.di.FirebaseModule_ProvideFirebaseFirestoreFactory;
import com.valentinesgarage.di.FirebaseModule_ProvideFirebaseStorageFactory;
import com.valentinesgarage.di.RepositoryModule_ProvideJobRepositoryFactory;
import com.valentinesgarage.di.RepositoryModule_ProvideTruckRepositoryFactory;
import com.valentinesgarage.di.RepositoryModule_ProvideUserRepositoryFactory;
import com.valentinesgarage.ui.MainActivity;
import com.valentinesgarage.ui.MainActivity_MembersInjector;
import com.valentinesgarage.ui.admin.AddMechanicFragment;
import com.valentinesgarage.ui.admin.AddMechanicViewModel;
import com.valentinesgarage.ui.admin.AddMechanicViewModel_HiltModules;
import com.valentinesgarage.ui.auth.AuthViewModel;
import com.valentinesgarage.ui.auth.AuthViewModel_HiltModules;
import com.valentinesgarage.ui.auth.LoginFragment;
import com.valentinesgarage.ui.checkin.CheckInFragment;
import com.valentinesgarage.ui.checkin.CheckInViewModel;
import com.valentinesgarage.ui.checkin.CheckInViewModel_HiltModules;
import com.valentinesgarage.ui.jobs.JobDetailFragment;
import com.valentinesgarage.ui.jobs.JobDetailViewModel;
import com.valentinesgarage.ui.jobs.JobDetailViewModel_HiltModules;
import com.valentinesgarage.ui.jobs.JobListFragment;
import com.valentinesgarage.ui.jobs.JobListViewModel;
import com.valentinesgarage.ui.jobs.JobListViewModel_HiltModules;
import com.valentinesgarage.ui.reports.ReportsFragment;
import com.valentinesgarage.ui.reports.ReportsViewModel;
import com.valentinesgarage.ui.reports.ReportsViewModel_HiltModules;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.IdentifierNameString;
import dagger.internal.KeepFieldType;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

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
public final class DaggerGarageApplication_HiltComponents_SingletonC {
  private DaggerGarageApplication_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static GarageApplication_HiltComponents.SingletonC create() {
    return new Builder().build();
  }

  public static final class Builder {
    private Builder() {
    }

    /**
     * @deprecated This module is declared, but an instance is not used in the component. This method is a no-op. For more, see https://dagger.dev/unused-modules.
     */
    @Deprecated
    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public GarageApplication_HiltComponents.SingletonC build() {
      return new SingletonCImpl();
    }
  }

  private static final class ActivityRetainedCBuilder implements GarageApplication_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public GarageApplication_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements GarageApplication_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public GarageApplication_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements GarageApplication_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public GarageApplication_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements GarageApplication_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public GarageApplication_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements GarageApplication_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public GarageApplication_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements GarageApplication_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public GarageApplication_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements GarageApplication_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public GarageApplication_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends GarageApplication_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends GarageApplication_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public void injectAddMechanicFragment(AddMechanicFragment addMechanicFragment) {
    }

    @Override
    public void injectLoginFragment(LoginFragment loginFragment) {
    }

    @Override
    public void injectCheckInFragment(CheckInFragment checkInFragment) {
    }

    @Override
    public void injectJobDetailFragment(JobDetailFragment jobDetailFragment) {
    }

    @Override
    public void injectJobListFragment(JobListFragment jobListFragment) {
    }

    @Override
    public void injectReportsFragment(ReportsFragment reportsFragment) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends GarageApplication_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends GarageApplication_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
      injectMainActivity2(mainActivity);
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(ImmutableMap.<String, Boolean>builderWithExpectedSize(6).put(LazyClassKeyProvider.com_valentinesgarage_ui_admin_AddMechanicViewModel, AddMechanicViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_valentinesgarage_ui_auth_AuthViewModel, AuthViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_valentinesgarage_ui_checkin_CheckInViewModel, CheckInViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_valentinesgarage_ui_jobs_JobDetailViewModel, JobDetailViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_valentinesgarage_ui_jobs_JobListViewModel, JobListViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_valentinesgarage_ui_reports_ReportsViewModel, ReportsViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @CanIgnoreReturnValue
    private MainActivity injectMainActivity2(MainActivity instance) {
      MainActivity_MembersInjector.injectUserRepository(instance, singletonCImpl.provideUserRepositoryProvider.get());
      return instance;
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_valentinesgarage_ui_checkin_CheckInViewModel = "com.valentinesgarage.ui.checkin.CheckInViewModel";

      static String com_valentinesgarage_ui_admin_AddMechanicViewModel = "com.valentinesgarage.ui.admin.AddMechanicViewModel";

      static String com_valentinesgarage_ui_reports_ReportsViewModel = "com.valentinesgarage.ui.reports.ReportsViewModel";

      static String com_valentinesgarage_ui_auth_AuthViewModel = "com.valentinesgarage.ui.auth.AuthViewModel";

      static String com_valentinesgarage_ui_jobs_JobListViewModel = "com.valentinesgarage.ui.jobs.JobListViewModel";

      static String com_valentinesgarage_ui_jobs_JobDetailViewModel = "com.valentinesgarage.ui.jobs.JobDetailViewModel";

      @KeepFieldType
      CheckInViewModel com_valentinesgarage_ui_checkin_CheckInViewModel2;

      @KeepFieldType
      AddMechanicViewModel com_valentinesgarage_ui_admin_AddMechanicViewModel2;

      @KeepFieldType
      ReportsViewModel com_valentinesgarage_ui_reports_ReportsViewModel2;

      @KeepFieldType
      AuthViewModel com_valentinesgarage_ui_auth_AuthViewModel2;

      @KeepFieldType
      JobListViewModel com_valentinesgarage_ui_jobs_JobListViewModel2;

      @KeepFieldType
      JobDetailViewModel com_valentinesgarage_ui_jobs_JobDetailViewModel2;
    }
  }

  private static final class ViewModelCImpl extends GarageApplication_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<AddMechanicViewModel> addMechanicViewModelProvider;

    private Provider<AuthViewModel> authViewModelProvider;

    private Provider<CheckInViewModel> checkInViewModelProvider;

    private Provider<JobDetailViewModel> jobDetailViewModelProvider;

    private Provider<JobListViewModel> jobListViewModelProvider;

    private Provider<ReportsViewModel> reportsViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.addMechanicViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.authViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.checkInViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.jobDetailViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.jobListViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.reportsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(ImmutableMap.<String, javax.inject.Provider<ViewModel>>builderWithExpectedSize(6).put(LazyClassKeyProvider.com_valentinesgarage_ui_admin_AddMechanicViewModel, ((Provider) addMechanicViewModelProvider)).put(LazyClassKeyProvider.com_valentinesgarage_ui_auth_AuthViewModel, ((Provider) authViewModelProvider)).put(LazyClassKeyProvider.com_valentinesgarage_ui_checkin_CheckInViewModel, ((Provider) checkInViewModelProvider)).put(LazyClassKeyProvider.com_valentinesgarage_ui_jobs_JobDetailViewModel, ((Provider) jobDetailViewModelProvider)).put(LazyClassKeyProvider.com_valentinesgarage_ui_jobs_JobListViewModel, ((Provider) jobListViewModelProvider)).put(LazyClassKeyProvider.com_valentinesgarage_ui_reports_ReportsViewModel, ((Provider) reportsViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return ImmutableMap.<Class<?>, Object>of();
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_valentinesgarage_ui_jobs_JobDetailViewModel = "com.valentinesgarage.ui.jobs.JobDetailViewModel";

      static String com_valentinesgarage_ui_auth_AuthViewModel = "com.valentinesgarage.ui.auth.AuthViewModel";

      static String com_valentinesgarage_ui_jobs_JobListViewModel = "com.valentinesgarage.ui.jobs.JobListViewModel";

      static String com_valentinesgarage_ui_reports_ReportsViewModel = "com.valentinesgarage.ui.reports.ReportsViewModel";

      static String com_valentinesgarage_ui_admin_AddMechanicViewModel = "com.valentinesgarage.ui.admin.AddMechanicViewModel";

      static String com_valentinesgarage_ui_checkin_CheckInViewModel = "com.valentinesgarage.ui.checkin.CheckInViewModel";

      @KeepFieldType
      JobDetailViewModel com_valentinesgarage_ui_jobs_JobDetailViewModel2;

      @KeepFieldType
      AuthViewModel com_valentinesgarage_ui_auth_AuthViewModel2;

      @KeepFieldType
      JobListViewModel com_valentinesgarage_ui_jobs_JobListViewModel2;

      @KeepFieldType
      ReportsViewModel com_valentinesgarage_ui_reports_ReportsViewModel2;

      @KeepFieldType
      AddMechanicViewModel com_valentinesgarage_ui_admin_AddMechanicViewModel2;

      @KeepFieldType
      CheckInViewModel com_valentinesgarage_ui_checkin_CheckInViewModel2;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.valentinesgarage.ui.admin.AddMechanicViewModel 
          return (T) new AddMechanicViewModel(singletonCImpl.provideUserRepositoryProvider.get());

          case 1: // com.valentinesgarage.ui.auth.AuthViewModel 
          return (T) new AuthViewModel(singletonCImpl.provideUserRepositoryProvider.get());

          case 2: // com.valentinesgarage.ui.checkin.CheckInViewModel 
          return (T) new CheckInViewModel(singletonCImpl.provideTruckRepositoryProvider.get(), singletonCImpl.provideUserRepositoryProvider.get());

          case 3: // com.valentinesgarage.ui.jobs.JobDetailViewModel 
          return (T) new JobDetailViewModel(singletonCImpl.provideJobRepositoryProvider.get(), singletonCImpl.provideTruckRepositoryProvider.get(), singletonCImpl.provideUserRepositoryProvider.get());

          case 4: // com.valentinesgarage.ui.jobs.JobListViewModel 
          return (T) new JobListViewModel(singletonCImpl.provideJobRepositoryProvider.get(), singletonCImpl.provideUserRepositoryProvider.get());

          case 5: // com.valentinesgarage.ui.reports.ReportsViewModel 
          return (T) new ReportsViewModel(singletonCImpl.provideJobRepositoryProvider.get(), singletonCImpl.provideUserRepositoryProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends GarageApplication_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends GarageApplication_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends GarageApplication_HiltComponents.SingletonC {
    private final SingletonCImpl singletonCImpl = this;

    private Provider<FirebaseAuth> provideFirebaseAuthProvider;

    private Provider<FirebaseFirestore> provideFirebaseFirestoreProvider;

    private Provider<UserRepository> provideUserRepositoryProvider;

    private Provider<FirebaseStorage> provideFirebaseStorageProvider;

    private Provider<TruckRepository> provideTruckRepositoryProvider;

    private Provider<JobRepository> provideJobRepositoryProvider;

    private SingletonCImpl() {

      initialize();

    }

    @SuppressWarnings("unchecked")
    private void initialize() {
      this.provideFirebaseAuthProvider = DoubleCheck.provider(new SwitchingProvider<FirebaseAuth>(singletonCImpl, 1));
      this.provideFirebaseFirestoreProvider = DoubleCheck.provider(new SwitchingProvider<FirebaseFirestore>(singletonCImpl, 2));
      this.provideUserRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<UserRepository>(singletonCImpl, 0));
      this.provideFirebaseStorageProvider = DoubleCheck.provider(new SwitchingProvider<FirebaseStorage>(singletonCImpl, 4));
      this.provideTruckRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<TruckRepository>(singletonCImpl, 3));
      this.provideJobRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<JobRepository>(singletonCImpl, 5));
    }

    @Override
    public void injectGarageApplication(GarageApplication garageApplication) {
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return ImmutableSet.<Boolean>of();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.valentinesgarage.data.repository.UserRepository 
          return (T) RepositoryModule_ProvideUserRepositoryFactory.provideUserRepository(singletonCImpl.provideFirebaseAuthProvider.get(), singletonCImpl.provideFirebaseFirestoreProvider.get());

          case 1: // com.google.firebase.auth.FirebaseAuth 
          return (T) FirebaseModule_ProvideFirebaseAuthFactory.provideFirebaseAuth();

          case 2: // com.google.firebase.firestore.FirebaseFirestore 
          return (T) FirebaseModule_ProvideFirebaseFirestoreFactory.provideFirebaseFirestore();

          case 3: // com.valentinesgarage.data.repository.TruckRepository 
          return (T) RepositoryModule_ProvideTruckRepositoryFactory.provideTruckRepository(singletonCImpl.provideFirebaseFirestoreProvider.get(), singletonCImpl.provideFirebaseStorageProvider.get());

          case 4: // com.google.firebase.storage.FirebaseStorage 
          return (T) FirebaseModule_ProvideFirebaseStorageFactory.provideFirebaseStorage();

          case 5: // com.valentinesgarage.data.repository.JobRepository 
          return (T) RepositoryModule_ProvideJobRepositoryFactory.provideJobRepository(singletonCImpl.provideFirebaseFirestoreProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
