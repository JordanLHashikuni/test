package com.valentinesgarage.ui.jobs;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\u0006\u0010\u001b\u001a\u00020\u001aR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b8F\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0010R\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u001e\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\t@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/valentinesgarage/ui/jobs/JobListViewModel;", "Landroidx/lifecycle/ViewModel;", "jobRepository", "Lcom/valentinesgarage/data/repository/JobRepository;", "userRepository", "Lcom/valentinesgarage/data/repository/UserRepository;", "(Lcom/valentinesgarage/data/repository/JobRepository;Lcom/valentinesgarage/data/repository/UserRepository;)V", "_isAdmin", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "currentUser", "Lcom/google/firebase/auth/FirebaseUser;", "getCurrentUser", "()Lcom/google/firebase/auth/FirebaseUser;", "isAdmin", "Lkotlinx/coroutines/flow/StateFlow;", "()Lkotlinx/coroutines/flow/StateFlow;", "openJobs", "", "Lcom/valentinesgarage/data/model/Job;", "getOpenJobs", "<set-?>", "roleLoaded", "getRoleLoaded", "()Z", "loadRole", "", "logout", "Companion", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class JobListViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.valentinesgarage.data.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "JobListViewModel";
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.valentinesgarage.data.model.Job>> openJobs = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isAdmin = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isAdmin = null;
    
    /**
     * True once the Firestore role fetch has completed.
     */
    private boolean roleLoaded = false;
    @org.jetbrains.annotations.NotNull()
    public static final com.valentinesgarage.ui.jobs.JobListViewModel.Companion Companion = null;
    
    @javax.inject.Inject()
    public JobListViewModel(@org.jetbrains.annotations.NotNull()
    com.valentinesgarage.data.repository.JobRepository jobRepository, @org.jetbrains.annotations.NotNull()
    com.valentinesgarage.data.repository.UserRepository userRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.valentinesgarage.data.model.Job>> getOpenJobs() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.google.firebase.auth.FirebaseUser getCurrentUser() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isAdmin() {
        return null;
    }
    
    /**
     * True once the Firestore role fetch has completed.
     */
    public final boolean getRoleLoaded() {
        return false;
    }
    
    private final void loadRole() {
    }
    
    /**
     * Signs the current user out of Firebase Auth.
     */
    public final void logout() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/valentinesgarage/ui/jobs/JobListViewModel$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}