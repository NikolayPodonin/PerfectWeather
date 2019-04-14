package android.podonin.com.perfectweather.view.locationController

import android.podonin.com.perfectweather.repository.WetherRepository
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FetchPlaceResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class LocationControllerPresenter(private val view: LocationControllerView,
                                  private val repository: WetherRepository) {
    private val disposables = mutableListOf<Disposable>()

    fun onStarted(placeIds: Set<String>) {
        view.requestPlacesForIds(placeIds)
        if (placeIds.isEmpty()) view.navigateToLocationPicker()
    }

    fun onDestroy() {
        disposables.forEach { it.dispose() }
    }

    fun onPlaceResponse(place: Place) {
        view.addWeatherAndPlace(place, null)
        place.latLng?.also {
            disposables += repository.getWether(it.latitude, it.longitude, 1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { weatherResponse -> view.addWeatherAndPlace(place, weatherResponse.fact)},
                    { error -> view.showMessage(error.message)}
                )
        }
    }
}