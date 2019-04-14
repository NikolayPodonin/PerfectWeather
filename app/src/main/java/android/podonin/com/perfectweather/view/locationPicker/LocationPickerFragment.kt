package android.podonin.com.perfectweather.view.locationPicker

import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.model.TypeFilter
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class LocationPickerFragment: AutocompleteSupportFragment(), LocationPickerView {

    val presenter: LocationPickerPresenter by inject { parametersOf(this) }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setPlaceFields(listOf(Place.Field.NAME))
        setTypeFilter(TypeFilter.CITIES)
        //setLocationBias() - to specify prefer region with RectangularBounds.newInstance( lat, lon )
        setOnPlaceSelectedListener(object: PlaceSelectionListener{
            override fun onPlaceSelected(place: Place) {
                presenter.onPlaceSelected(place)
            }

            override fun onError(status: Status) {
                presenter.onError(status.statusMessage)
            }
        })
    }

    override fun exit() = activity?.onBackPressed()!!
    override fun showMessage(message: String) = Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
}