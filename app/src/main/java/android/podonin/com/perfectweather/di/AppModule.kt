package android.podonin.com.perfectweather.di

import android.podonin.com.perfectweather.adapter.LocationsAdapter
import android.podonin.com.perfectweather.repository.WetherRepository
import android.podonin.com.perfectweather.view.locationController.LocationControllerPresenter
import android.podonin.com.perfectweather.view.locationPicker.LocationPickerPresenter
import org.koin.dsl.module

val weatherModule = module {
    factory { params -> LocationPickerPresenter(params[0]) }
    factory { params -> LocationControllerPresenter(params[0], get()) }
    factory { LocationsAdapter() }
    single { WetherRepository() }
}
