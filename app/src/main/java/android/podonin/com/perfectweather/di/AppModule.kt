package android.podonin.com.perfectweather.di

import android.podonin.com.perfectweather.view.locationController.LocationControllerPresenter
import android.podonin.com.perfectweather.view.locationPicker.LocationPickerPresenter
import org.koin.dsl.module

val weatherModule = module {
    factory { params -> LocationPickerPresenter(params[0]) }
    factory { params -> LocationControllerPresenter(params[0]) }
}
