package com.konifar.materialcat

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.crashlytics.android.Crashlytics
import com.facebook.stetho.Stetho
import com.konifar.materialcat._di.ApplicationComponent
import com.konifar.materialcat._di.module.ApplicationModule
import com.konifar.materialcat._di.DaggerApplicationComponent
import io.fabric.sdk.android.Fabric

class MainApplication : Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder().apply {
            applicationModule(ApplicationModule(this@MainApplication))
        }.build()
    }

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics())
        Stetho.initializeWithDefaults(this);
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}