package android.podonin.com.perfectweather.view.locationController

import android.podonin.com.perfectweather.model.Fact
import android.podonin.com.perfectweather.model.WetherResponse
import com.google.android.libraries.places.api.model.Place

interface LocationControllerView {
    fun addWeatherAndPlace(place: Place, weatherFact: Fact?)
    fun requestPlacesForIds(placeIds: Set<String>)
    fun showMessage(message: String?)
    fun navigateToLocationPicker()
}