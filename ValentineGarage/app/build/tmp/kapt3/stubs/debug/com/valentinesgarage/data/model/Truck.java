package com.valentinesgarage.data.model;

/**
 * Represents a truck at the point of check-in.
 * Captures vehicle condition and odometer reading to prevent misuse.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0007\b\u0016\u00a2\u0006\u0002\u0010\u0002B{\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0004\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0004\u0012\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u00a2\u0006\u0002\u0010\u0013J\t\u0010$\u001a\u00020\u0004H\u00c6\u0003J\t\u0010%\u001a\u00020\u0004H\u00c6\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\u0012H\u00c6\u0003J\t\u0010\'\u001a\u00020\u0004H\u00c6\u0003J\t\u0010(\u001a\u00020\u0004H\u00c6\u0003J\t\u0010)\u001a\u00020\u0004H\u00c6\u0003J\t\u0010*\u001a\u00020\tH\u00c6\u0003J\t\u0010+\u001a\u00020\u000bH\u00c6\u0003J\t\u0010,\u001a\u00020\u0004H\u00c6\u0003J\u000f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00040\u000eH\u00c6\u0003J\t\u0010.\u001a\u00020\u0004H\u00c6\u0003J\u007f\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0006\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00042\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010\u0010\u001a\u00020\u00042\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u00c6\u0001J\u0013\u00100\u001a\u0002012\b\u00102\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u00103\u001a\u000204H\u00d6\u0001J\u0014\u00105\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u000106J\t\u00107\u001a\u00020\u0004H\u00d6\u0001R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u000f\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0010\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0011\u0010\f\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0006\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0017R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u0005\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0017R\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0017\u00a8\u00068"}, d2 = {"Lcom/valentinesgarage/data/model/Truck;", "", "()V", "truckId", "", "registrationNumber", "make", "model", "kilometerReading", "", "conditionRating", "Lcom/valentinesgarage/data/model/ConditionRating;", "conditionNotes", "photoUrls", "", "checkedInBy", "checkedInByName", "checkInTime", "Lcom/google/firebase/Timestamp;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JLcom/valentinesgarage/data/model/ConditionRating;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;Lcom/google/firebase/Timestamp;)V", "getCheckInTime", "()Lcom/google/firebase/Timestamp;", "getCheckedInBy", "()Ljava/lang/String;", "getCheckedInByName", "getConditionNotes", "getConditionRating", "()Lcom/valentinesgarage/data/model/ConditionRating;", "getKilometerReading", "()J", "getMake", "getModel", "getPhotoUrls", "()Ljava/util/List;", "getRegistrationNumber", "getTruckId", "component1", "component10", "component11", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toMap", "", "toString", "app_debug"})
public final class Truck {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String truckId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String registrationNumber = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String make = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String model = null;
    private final long kilometerReading = 0L;
    @org.jetbrains.annotations.NotNull()
    private final com.valentinesgarage.data.model.ConditionRating conditionRating = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String conditionNotes = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> photoUrls = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String checkedInBy = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String checkedInByName = null;
    @org.jetbrains.annotations.Nullable()
    private final com.google.firebase.Timestamp checkInTime = null;
    
    public Truck(@org.jetbrains.annotations.NotNull()
    java.lang.String truckId, @org.jetbrains.annotations.NotNull()
    java.lang.String registrationNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String make, @org.jetbrains.annotations.NotNull()
    java.lang.String model, long kilometerReading, @org.jetbrains.annotations.NotNull()
    com.valentinesgarage.data.model.ConditionRating conditionRating, @org.jetbrains.annotations.NotNull()
    java.lang.String conditionNotes, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> photoUrls, @org.jetbrains.annotations.NotNull()
    java.lang.String checkedInBy, @org.jetbrains.annotations.NotNull()
    java.lang.String checkedInByName, @org.jetbrains.annotations.Nullable()
    com.google.firebase.Timestamp checkInTime) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTruckId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRegistrationNumber() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMake() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getModel() {
        return null;
    }
    
    public final long getKilometerReading() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.valentinesgarage.data.model.ConditionRating getConditionRating() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getConditionNotes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getPhotoUrls() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCheckedInBy() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCheckedInByName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.google.firebase.Timestamp getCheckInTime() {
        return null;
    }
    
    public Truck() {
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
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.google.firebase.Timestamp component11() {
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
    public final java.lang.String component4() {
        return null;
    }
    
    public final long component5() {
        return 0L;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.valentinesgarage.data.model.ConditionRating component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component8() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.valentinesgarage.data.model.Truck copy(@org.jetbrains.annotations.NotNull()
    java.lang.String truckId, @org.jetbrains.annotations.NotNull()
    java.lang.String registrationNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String make, @org.jetbrains.annotations.NotNull()
    java.lang.String model, long kilometerReading, @org.jetbrains.annotations.NotNull()
    com.valentinesgarage.data.model.ConditionRating conditionRating, @org.jetbrains.annotations.NotNull()
    java.lang.String conditionNotes, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> photoUrls, @org.jetbrains.annotations.NotNull()
    java.lang.String checkedInBy, @org.jetbrains.annotations.NotNull()
    java.lang.String checkedInByName, @org.jetbrains.annotations.Nullable()
    com.google.firebase.Timestamp checkInTime) {
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