package com.anirudh.spotifysearch.di

import android.app.Application
import com.anirudh.spotifysearch.SpotifyApplication
import com.anirudh.spotifysearch.di.module.ActivityModule
import com.anirudh.spotifysearch.di.module.ApiModule
import com.anirudh.spotifysearch.di.module.FragmentModule
import com.anirudh.spotifysearch.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Component(
    modules = [
        ApiModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        FragmentModule::class,
        AndroidSupportInjectionModule::class]
)
@Singleton
interface AppComponent {

    /*
     * We will call this builder interface from our custom Application class.
     * This will set our application object to the AppComponent.
     * So inside the AppComponent the application instance is available.
     * So this application instance can be accessed by our modules
     * such as ApiModule when needed
     *
     * */
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    /*
     * This is our custom Application class
     * */
    fun inject(appController: SpotifyApplication)
}