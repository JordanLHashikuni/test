package com.valentinesgarage

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class — required by Hilt to generate the dependency graph.
 * Must be declared in AndroidManifest.xml as android:name=".GarageApplication"
 */
@HiltAndroidApp
class GarageApplication : Application()
