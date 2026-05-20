package com.valentinesgarage;

/**
 * Application class — required by Hilt to generate the dependency graph.
 * Must be declared in AndroidManifest.xml as android:name=".GarageApplication"
 */
@dagger.hilt.android.HiltAndroidApp()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/valentinesgarage/GarageApplication;", "Landroid/app/Application;", "()V", "app_debug"})
public final class GarageApplication extends android.app.Application {
    
    public GarageApplication() {
        super();
    }
}