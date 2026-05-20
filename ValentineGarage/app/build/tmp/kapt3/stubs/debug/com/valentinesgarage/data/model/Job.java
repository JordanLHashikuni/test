package com.valentinesgarage.data.model;

/**
 * Represents a repair/service job for a checked-in truck.
 * A Job is created at check-in and contains a list of Tasks.
 * Multiple mechanics can be assigned to the same job.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0007\b\u0016\u00a2\u0006\u0002\u0010\u0002B_\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0004\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r\u00a2\u0006\u0002\u0010\u000fJ\t\u0010\u001c\u001a\u00020\u0004H\u00c6\u0003J\t\u0010\u001d\u001a\u00020\u0004H\u00c6\u0003J\t\u0010\u001e\u001a\u00020\u0004H\u00c6\u0003J\t\u0010\u001f\u001a\u00020\bH\u00c6\u0003J\u000f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00040\nH\u00c6\u0003J\t\u0010!\u001a\u00020\u0004H\u00c6\u0003J\u000b\u0010\"\u001a\u0004\u0018\u00010\rH\u00c6\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\rH\u00c6\u0003Jc\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\b2\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\n2\b\b\u0002\u0010\u000b\u001a\u00020\u00042\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\rH\u00c6\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010\'\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010(\u001a\u00020)H\u00d6\u0001J\u0014\u0010*\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00010+J\t\u0010,\u001a\u00020\u0004H\u00d6\u0001R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\f\u001a\u0004\u0018\u00010\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u0011\u0010\u000b\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0016R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0016R\u0011\u0010\u0006\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016\u00a8\u0006-"}, d2 = {"Lcom/valentinesgarage/data/model/Job;", "", "()V", "jobId", "", "truckId", "truckRegistration", "status", "Lcom/valentinesgarage/data/model/JobStatus;", "assignedMechanics", "", "createdBy", "createdAt", "Lcom/google/firebase/Timestamp;", "completedAt", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/valentinesgarage/data/model/JobStatus;Ljava/util/List;Ljava/lang/String;Lcom/google/firebase/Timestamp;Lcom/google/firebase/Timestamp;)V", "getAssignedMechanics", "()Ljava/util/List;", "getCompletedAt", "()Lcom/google/firebase/Timestamp;", "getCreatedAt", "getCreatedBy", "()Ljava/lang/String;", "getJobId", "getStatus", "()Lcom/valentinesgarage/data/model/JobStatus;", "getTruckId", "getTruckRegistration", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toMap", "", "toString", "app_debug"})
public final class Job {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String jobId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String truckId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String truckRegistration = null;
    @org.jetbrains.annotations.NotNull()
    private final com.valentinesgarage.data.model.JobStatus status = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> assignedMechanics = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String createdBy = null;
    @org.jetbrains.annotations.Nullable()
    private final com.google.firebase.Timestamp createdAt = null;
    @org.jetbrains.annotations.Nullable()
    private final com.google.firebase.Timestamp completedAt = null;
    
    public Job(@org.jetbrains.annotations.NotNull()
    java.lang.String jobId, @org.jetbrains.annotations.NotNull()
    java.lang.String truckId, @org.jetbrains.annotations.NotNull()
    java.lang.String truckRegistration, @org.jetbrains.annotations.NotNull()
    com.valentinesgarage.data.model.JobStatus status, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> assignedMechanics, @org.jetbrains.annotations.NotNull()
    java.lang.String createdBy, @org.jetbrains.annotations.Nullable()
    com.google.firebase.Timestamp createdAt, @org.jetbrains.annotations.Nullable()
    com.google.firebase.Timestamp completedAt) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getJobId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTruckId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTruckRegistration() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.valentinesgarage.data.model.JobStatus getStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getAssignedMechanics() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCreatedBy() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.google.firebase.Timestamp getCreatedAt() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.google.firebase.Timestamp getCompletedAt() {
        return null;
    }
    
    public Job() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Map<java.lang.String, java.lang.Object> toMap() {
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
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.valentinesgarage.data.model.JobStatus component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.google.firebase.Timestamp component7() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.google.firebase.Timestamp component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.valentinesgarage.data.model.Job copy(@org.jetbrains.annotations.NotNull()
    java.lang.String jobId, @org.jetbrains.annotations.NotNull()
    java.lang.String truckId, @org.jetbrains.annotations.NotNull()
    java.lang.String truckRegistration, @org.jetbrains.annotations.NotNull()
    com.valentinesgarage.data.model.JobStatus status, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> assignedMechanics, @org.jetbrains.annotations.NotNull()
    java.lang.String createdBy, @org.jetbrains.annotations.Nullable()
    com.google.firebase.Timestamp createdAt, @org.jetbrains.annotations.Nullable()
    com.google.firebase.Timestamp completedAt) {
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