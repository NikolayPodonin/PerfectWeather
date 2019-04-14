package android.podonin.com.perfectweather.view.locationPicker

interface LocationPickerView {
    fun exit()
    fun showMessage(message: String)
    fun savePlaceId(id: String?)
}