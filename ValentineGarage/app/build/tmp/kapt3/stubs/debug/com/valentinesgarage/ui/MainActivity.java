package com.valentinesgarage.ui;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0012\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0013H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f\u00a8\u0006\u0016"}, d2 = {"Lcom/valentinesgarage/ui/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/valentinesgarage/databinding/ActivityMainBinding;", "navController", "Landroidx/navigation/NavController;", "userRepository", "Lcom/valentinesgarage/data/repository/UserRepository;", "getUserRepository", "()Lcom/valentinesgarage/data/repository/UserRepository;", "setUserRepository", "(Lcom/valentinesgarage/data/repository/UserRepository;)V", "applyRoleBasedBottomNav", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onSupportNavigateUp", "", "setReportsTabVisible", "visible", "app_debug"})
public final class MainActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.valentinesgarage.databinding.ActivityMainBinding binding;
    private androidx.navigation.NavController navController;
    @javax.inject.Inject()
    public com.valentinesgarage.data.repository.UserRepository userRepository;
    
    public MainActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.valentinesgarage.data.repository.UserRepository getUserRepository() {
        return null;
    }
    
    public final void setUserRepository(@org.jetbrains.annotations.NotNull()
    com.valentinesgarage.data.repository.UserRepository p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    /**
     * Determines whether the Reports tab should be visible based on the
     * signed-in user's role fetched from Firestore.
     *
     * FIX: Previously this defaulted to UserRole.MECHANIC when the Firestore
     * call was slow or failed, which hid the Reports tab for admins until
     * they navigated again.
     *
     * The corrected approach:
     *  1. Show the Reports tab immediately (optimistic default = ADMIN).
     *  2. Hide it only once we have a confirmed MECHANIC role from Firestore.
     *
     * This means admins see the tab instantly with no flicker, and mechanics
     * have it hidden after the first async round-trip — which is acceptable
     * UX because mechanics reach JobListFragment via a login redirect that
     * already involves a short Firestore call.
     */
    private final void applyRoleBasedBottomNav() {
    }
    
    private final void setReportsTabVisible(boolean visible) {
    }
    
    @java.lang.Override()
    public boolean onSupportNavigateUp() {
        return false;
    }
}