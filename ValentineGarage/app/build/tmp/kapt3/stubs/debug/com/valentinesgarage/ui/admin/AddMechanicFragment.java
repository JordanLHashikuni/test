package com.valentinesgarage.ui.admin;

/**
 * Admin-only screen for creating a new mechanic account.
 *
 * FIX: Now collects the admin's own email + password so UserRepository can
 * re-authenticate the admin after Firebase switches the session to the newly
 * created mechanic account (which it does automatically on
 * createUserWithEmailAndPassword). Without re-auth the admin is silently
 * signed out the moment the mechanic account is created.
 */
@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002J$\u0010\u000f\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0012"}, d2 = {"Lcom/valentinesgarage/ui/admin/AddMechanicFragment;", "Landroidx/fragment/app/Fragment;", "()V", "viewModel", "Lcom/valentinesgarage/ui/admin/AddMechanicViewModel;", "getViewModel", "()Lcom/valentinesgarage/ui/admin/AddMechanicViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "buildLayout", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onCreateView", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class AddMechanicFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    
    public AddMechanicFragment() {
        super();
    }
    
    private final com.valentinesgarage.ui.admin.AddMechanicViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    private final android.view.View buildLayout(android.view.LayoutInflater inflater, android.view.ViewGroup container) {
        return null;
    }
}