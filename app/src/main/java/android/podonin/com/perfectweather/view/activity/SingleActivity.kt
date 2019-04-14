package android.podonin.com.perfectweather.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.podonin.com.perfectweather.R
import androidx.navigation.Navigation.findNavController


val LOCATIONS_ID_SET = "LOCATIONS_ID_SET"
val CURRENT_LOCATION_ID = "CURRENT_LOCATION_ID"

class SingleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_single)
    }

    override fun onSupportNavigateUp()
            = findNavController(this, R.id.nav_host_fragment).navigateUp()
}
