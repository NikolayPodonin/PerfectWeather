package android.podonin.com.perfectweather.model

import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("date")
    val date: String,
    @SerializedName("date_ts")
    val dateTs: Double,
    @SerializedName("hours")
    val hours: List<Any>,
    @SerializedName("moon_code")
    val moonCode: Double,
    @SerializedName("moon_text")
    val moonText: String,
    @SerializedName("parts")
    val parts: Parts,
    @SerializedName("rise_begin")
    val riseBegin: String,
    @SerializedName("set_end")
    val setEnd: String,
    @SerializedName("sunrise")
    val sunrise: String,
    @SerializedName("sunset")
    val sunset: String,
    @SerializedName("week")
    val week: Double
)