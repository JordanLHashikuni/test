package com.valentinesgarage.ui.jobs;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000bJ\u0016\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\u000bJ\u0016\u0010 \u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\u000b2\u0006\u0010\"\u001a\u00020\u000bJ\u0010\u0010#\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u000bH\u0002J\u0010\u0010$\u001a\u00020\u001b2\u0006\u0010!\u001a\u00020\u000bH\u0002R\u0016\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0019\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006%"}, d2 = {"Lcom/valentinesgarage/ui/jobs/JobDetailViewModel;", "Landroidx/lifecycle/ViewModel;", "jobRepository", "Lcom/valentinesgarage/data/repository/JobRepository;", "truckRepository", "Lcom/valentinesgarage/data/repository/TruckRepository;", "userRepository", "Lcom/valentinesgarage/data/repository/UserRepository;", "(Lcom/valentinesgarage/data/repository/JobRepository;Lcom/valentinesgarage/data/repository/TruckRepository;Lcom/valentinesgarage/data/repository/UserRepository;)V", "_error", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_tasks", "", "Lcom/valentinesgarage/data/model/Task;", "_truck", "Lcom/valentinesgarage/data/model/Truck;", "currentJobId", "error", "Lkotlinx/coroutines/flow/StateFlow;", "getError", "()Lkotlinx/coroutines/flow/StateFlow;", "tasks", "getTasks", "truck", "getTruck", "addTask", "", "description", "completeTask", "taskId", "notes", "init", "jobId", "truckId", "loadTruck", "observeTasks", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class JobDetailViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.valentinesgarage.data.repository.JobRepository jobRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.valentinesgarage.data.repository.TruckRepository truckRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.valentinesgarage.data.repository.UserRepository userRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.valentinesgarage.data.model.Truck> _truck = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.valentinesgarage.data.model.Truck> truck = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.valentinesgarage.data.model.Task>> _tasks = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.valentinesgarage.data.model.Task>> tasks = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _error = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> error = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String currentJobId = "";
    
    @javax.inject.Inject()
    public JobDetailViewModel(@org.jetbrains.annotations.NotNull()
    com.valentinesgarage.data.repository.JobRepository jobRepository, @org.jetbrains.annotations.NotNull()
    com.valentinesgarage.data.repository.TruckRepository truckRepository, @org.jetbrains.annotations.NotNull()
    com.valentinesgarage.data.repository.UserRepository userRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.valentinesgarage.data.model.Truck> getTruck() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.valentinesgarage.data.model.Task>> getTasks() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getError() {
        return null;
    }
    
    public final void init(@org.jetbrains.annotations.NotNull()
    java.lang.String jobId, @org.jetbrains.annotations.NotNull()
    java.lang.String truckId) {
    }
    
    private final void loadTruck(java.lang.String truckId) {
    }
    
    private final void observeTasks(java.lang.String jobId) {
    }
    
    public final void addTask(@org.jetbrains.annotations.NotNull()
    java.lang.String description) {
    }
    
    public final void completeTask(@org.jetbrains.annotations.NotNull()
    java.lang.String taskId, @org.jetbrains.annotations.NotNull()
    java.lang.String notes) {
    }
}