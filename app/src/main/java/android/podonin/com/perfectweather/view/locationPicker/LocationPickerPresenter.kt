package android.podonin.com.perfectweather.view.locationPicker

import com.google.android.libraries.places.api.model.Place

class LocationPickerPresenter(private val view: LocationPickerView) {
    fun onPlaceSelected(place: Place) {
        view.savePlaceId(place.id)
        view.exit()
    }

    fun onError(statusMessage: String?) {
        view.showMessage(statusMessage ?: "")
        view.exit()
    }
}