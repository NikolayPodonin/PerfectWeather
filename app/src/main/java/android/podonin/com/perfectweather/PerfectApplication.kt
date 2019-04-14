package android.podonin.com.perfectweather

import android.app.Application
import android.podonin.com.perfectweather.di.weatherModule
import com.google.android.libraries.places.api.Places
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PerfectApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Places.initialize(applicationContext, "AIzaSyDdqJeBSteRv68jqbw8HN1aS1qcfxAvhq4")

        startKoin {
            androidLogger()

            androidContext(this@PerfectApplication)

            androidFileProperties()

            modules(weatherModule)
        }
    }
}