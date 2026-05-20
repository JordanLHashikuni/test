package com.valentinesgarage.data.model;

/**
 * Overall vehicle condition captured at check-in.
 * Displayed in Valentine's reports to track vehicle state over time.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lcom/valentinesgarage/data/model/ConditionRating;", "", "(Ljava/lang/String;I)V", "EXCELLENT", "GOOD", "FAIR", "POOR", "CRITICAL", "app_debug"})
public enum ConditionRating {
    /*public static final*/ EXCELLENT /* = new EXCELLENT() */,
    /*public static final*/ GOOD /* = new GOOD() */,
    /*public static final*/ FAIR /* = new FAIR() */,
    /*public static final*/ POOR /* = new POOR() */,
    /*public static final*/ CRITICAL /* = new CRITICAL() */;
    
    ConditionRating() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.valentinesgarage.data.model.ConditionRating> getEntries() {
        return null;
    }
}