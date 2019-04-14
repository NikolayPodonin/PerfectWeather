package android.podonin.com.perfectweather.adapter

import android.podonin.com.perfectweather.R
import android.podonin.com.perfectweather.model.Fact
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.collection.ArrayMap
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.libraries.places.api.model.Place
import kotlinx.android.synthetic.main.widget_location_element.view.*

class LocationsAdapter : RecyclerView.Adapter<LocationsAdapter.PlaceViewHolder>() {

    private val placesWithWeather = ArrayMap<Place, Fact>()
    var currentId: String = ""

    fun setPlaces(places: List<Place>) {
        placesWithWeather.clear()
        places.forEach { placesWithWeather[it] = null }
        notifyDataSetChanged()
    }

    fun changePlace(place: Place, fact: Fact?) {
        placesWithWeather[place] = fact
        notifyItemChanged(placesWithWeather.indexOfKey(place))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.widget_location_element, parent, false)
        return PlaceViewHolder(view)
    }

    override fun getItemCount(): Int {
        return placesWithWeather.size
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val place = placesWithWeather.keyAt(position)
        val fact = placesWithWeather[place]
        holder.bind(place, fact)
    }

    inner class PlaceViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(place: Place, fact: Fact?) {
            view.locationName.text = place.name
            view.locationParentName.text = place.address
            view.currentImage.visibility = if (place.id == currentId) View.VISIBLE else View.GONE
            fact?.also {
                view.degrees.text = fact.temp.toString()
                view.weatherInfo.text = fact.run { "${windDir.toUpperCase()} | $windSpeed km/h" }
                view.weatherLightness.text = fact.run { if (condition == "clear") "Sunny" else "Cloudy" }
                Glide.with(view.context)
                    .load("https://yastatic.net/weather/i/icons/blueye/color/svg/${fact.icon}.svg")
                    .into(view.weatherImage)
            }
        }
    }
}