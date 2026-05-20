package com.valentinesgarage.ui.checkin;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001:\u0001\u001eB\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\nJ6\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\nR\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/valentinesgarage/ui/checkin/CheckInViewModel;", "Landroidx/lifecycle/ViewModel;", "truckRepository", "Lcom/valentinesgarage/data/repository/TruckRepository;", "userRepository", "Lcom/valentinesgarage/data/repository/UserRepository;", "(Lcom/valentinesgarage/data/repository/TruckRepository;Lcom/valentinesgarage/data/repository/UserRepository;)V", "_photoUris", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "", "_state", "Lcom/valentinesgarage/ui/checkin/CheckInViewModel$CheckInState;", "photoUris", "Lkotlinx/coroutines/flow/StateFlow;", "getPhotoUris", "()Lkotlinx/coroutines/flow/StateFlow;", "state", "getState", "addPhoto", "", "uri", "checkInTruck", "registration", "make", "model", "kilometers", "condition", "Lcom/valentinesgarage/data/model/ConditionRating;", "conditionNotes", "CheckInState", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class CheckInViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.valentinesgarage.data.repository.TruckRepository truckRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.valentinesgarage.data.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.valentinesgarage.ui.checkin.CheckInViewModel.CheckInState> _state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.valentinesgarage.ui.checkin.CheckInViewModel.CheckInState> state = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<java.lang.String>> _photoUris = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.String>> photoUris = null;
    
    @javax.inject.Inject()
    public CheckInViewModel(@org.jetbrains.annotations.NotNull()
    com.valentinesgarage.data.repository.TruckRepository truckRepository, @org.jetbrains.annotations.NotNull()
    com.valentinesgarage.data.repository.UserRepository userRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.valentinesgarage.ui.checkin.CheckInViewModel.CheckInState> getState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.String>> getPhotoUris() {
        return null;
    }
    
    public final void addPhoto(@org.jetbrains.annotations.NotNull()
    java.lang.String uri) {
    }
    
    public final void checkInTruck(@org.jetbrains.annotations.NotNull()
    java.lang.String registration, @org.jetbrains.annotations.NotNull()
    java.lang.String make, @org.jetbrains.annotations.NotNull()
    java.lang.String model, @org.jetbrains.annotations.NotNull()
    java.lang.String kilometers, @org.jetbrains.annotations.NotNull()
    com.valentinesgarage.data.model.ConditionRating condition, @org.jetbrains.annotations.NotNull()
    java.lang.String conditionNotes) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0004\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0004\u0007\b\t\n\u00a8\u0006\u000b"}, d2 = {"Lcom/valentinesgarage/ui/checkin/CheckInViewModel$CheckInState;", "", "()V", "Error", "Idle", "Loading", "Success", "Lcom/valentinesgarage/ui/checkin/CheckInViewModel$CheckInState$Error;", "Lcom/valentinesgarage/ui/checkin/CheckInViewModel$CheckInState$Idle;", "Lcom/valentinesgarage/ui/checkin/CheckInViewModel$CheckInState$Loading;", "Lcom/valentinesgarage/ui/checkin/CheckInViewModel$CheckInState$Success;", "app_debug"})
    public static abstract class CheckInState {
        
        private CheckInState() {
            super();
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003H\u00c6\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00d6\u0003J\t\u0010\r\u001a\u00020\u000eH\u00d6\u0001J\t\u0010\u000f\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\u0010"}, d2 = {"Lcom/valentinesgarage/ui/checkin/CheckInViewModel$CheckInState$Error;", "Lcom/valentinesgarage/ui/checkin/CheckInViewModel$CheckInState;", "message", "", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
        public static final class Error extends com.valentinesgarage.ui.checkin.CheckInViewModel.CheckInState {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String message = null;
            
            public Error(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getMessage() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.valentinesgarage.ui.checkin.CheckInViewModel.CheckInState.Error copy(@org.jetbrains.annotations.NotNull()
            java.lang.String message) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/valentinesgarage/ui/checkin/CheckInViewModel$CheckInState$Idle;", "Lcom/valentinesgarage/ui/checkin/CheckInViewModel$CheckInState;", "()V", "app_debug"})
        public static final class Idle extends com.valentinesgarage.ui.checkin.CheckInViewModel.CheckInState {
            @org.jetbrains.annotations.NotNull()
            public static final com.valentinesgarage.ui.checkin.CheckInViewModel.CheckInState.Idle INSTANCE = null;
            
            private Idle() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/valentinesgarage/ui/checkin/CheckInViewModel$CheckInState$Loading;", "Lcom/valentinesgarage/ui/checkin/CheckInViewModel$CheckInState;", "()V", "app_debug"})
        public static final class Loading extends com.valentinesgarage.ui.checkin.CheckInViewModel.CheckInState {
            @org.jetbrains.annotations.NotNull()
            public static final com.valentinesgarage.ui.checkin.CheckInViewModel.CheckInState.Loading INSTANCE = null;
            
            private Loading() {
            }
        }
        
        @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\n\u001a\u00020\u0003H\u00c6\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u00d6\u0003J\t\u0010\u0010\u001a\u00020\u0011H\u00d6\u0001J\t\u0010\u0012\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007\u00a8\u0006\u0013"}, d2 = {"Lcom/valentinesgarage/ui/checkin/CheckInViewModel$CheckInState$Success;", "Lcom/valentinesgarage/ui/checkin/CheckInViewModel$CheckInState;", "truckId", "", "jobId", "(Ljava/lang/String;Ljava/lang/String;)V", "getJobId", "()Ljava/lang/String;", "getTruckId", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_debug"})
        public static final class Success extends com.valentinesgarage.ui.checkin.CheckInViewModel.CheckInState {
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String truckId = null;
            @org.jetbrains.annotations.NotNull()
            private final java.lang.String jobId = null;
            
            public Success(@org.jetbrains.annotations.NotNull()
            java.lang.String truckId, @org.jetbrains.annotations.NotNull()
            java.lang.String jobId) {
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getTruckId() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String getJobId() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component1() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final java.lang.String component2() {
                return null;
            }
            
            @org.jetbrains.annotations.NotNull()
            public final com.valentinesgarage.ui.checkin.CheckInViewModel.CheckInState.Success copy(@org.jetbrains.annotations.NotNull()
            java.lang.String truckId, @org.jetbrains.annotations.NotNull()
            java.lang.String jobId) {
                return null;
            }
            
            @java.lang.Override()
            public boolean equals(@org.jetbrains.annotations.Nullable()
            java.lang.Object other) {
                return false;
            }
            
            @java.lang.Override()
            public int hashCode() {
                return 0;
            }
            
            @java.lang.Override()
            @org.jetbrains.annotations.NotNull()
            public java.lang.String toString() {
                return null;
            }
        }
    }
}