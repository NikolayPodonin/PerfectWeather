package android.podonin.com.perfectweather.view.locationPicker

import android.content.Context
import android.os.Bundle
import android.podonin.com.perfectweather.R
import android.podonin.com.perfectweather.view.activity.CURRENT_LOCATION_ID
import android.podonin.com.perfectweather.view.activity.LOCATIONS_ID_SET
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
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

        setPlaceFields(listOf(Place.Field.NAME, Place.Field.ID))
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

    override fun savePlaceId(id: String?) {
        activity?.also {
            val preferences = it.getPreferences(Context.MODE_PRIVATE)
            val set = preferences.getStringSet(LOCATIONS_ID_SET, setOf())?.toMutableSet()
            set?.add(id)
            preferences
                .edit()
                .putString(CURRENT_LOCATION_ID, id)
                .putStringSet(LOCATIONS_ID_SET, set)
                .apply()
        }
    }

    override fun exit() {
        activity?.also {
            Navigation
                .findNavController(it, R.id.nav_host_fragment)
                .navigateUp()
        }
    }

    override fun showMessage(message: String) = Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
}