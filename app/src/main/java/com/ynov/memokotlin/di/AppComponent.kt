package com.ynov.memokotlin.di

import android.app.Application
import com.ynov.memokotlin.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityBuildersModule::class
    ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

    @Component.Builder
    interface Builder {

        // Method #1
        fun build(): AppComponent

        // Method #2
        @BindsInstance
        fun application(application: Application): Builder
    }
}