package android.podonin.com.perfectweather.view.locationController

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.podonin.com.perfectweather.R
import android.podonin.com.perfectweather.adapter.LocationsAdapter
import android.podonin.com.perfectweather.model.Fact
import android.podonin.com.perfectweather.view.activity.CURRENT_LOCATION_ID
import android.podonin.com.perfectweather.view.activity.LOCATIONS_ID_SET
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.gms.tasks.Task
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FetchPlaceResponse
import kotlinx.android.synthetic.main.fr_location_controller.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class LocationControllerFragment: Fragment(), LocationControllerView {

    private val presenter: LocationControllerPresenter by inject { parametersOf(this) }
    private val adapter: LocationsAdapter by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fr_location_controller, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        locationsRecycler.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        activity?.also { activ ->
            val preferences = activ.getPreferences(MODE_PRIVATE)
            val ids = preferences.getStringSet(LOCATIONS_ID_SET, setOf()) ?: setOf()
            val currentId = preferences.getString(CURRENT_LOCATION_ID, "") ?: ""
            presenter.onStarted(ids)
            adapter.currentId = currentId
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun requestPlacesForIds(placeIds: Set<String>) {
        activity?.also { activ ->
            placeIds.forEach {
                if (!it.isNullOrEmpty()) {
                    val task = Places.createClient(activ.applicationContext)
                        .fetchPlace(
                            FetchPlaceRequest.newInstance(
                                it,
                                listOf(Place.Field.NAME, Place.Field.LAT_LNG, Place.Field.ID, Place.Field.ADDRESS)
                            )
                        )
                    task.addOnSuccessListener { response: FetchPlaceResponse -> presenter.onPlaceResponse(response.place) }
                }
            }
        }
    }

    override fun navigateToLocationPicker() {
        activity?.also {
            Navigation
                .findNavController(it, R.id.nav_host_fragment)
                .navigate(R.id.action_locationControllerFragment_to_locationPickerFragment)
        }
    }

    override fun addWeatherAndPlace(place: Place, weatherFact: Fact?) {
        adapter.changePlace(place, weatherFact)
    }

    override fun showMessage(message: String?) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}