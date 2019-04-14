package android.podonin.com.perfectweather.model

import com.google.gson.annotations.SerializedName

data class WetherResponse(
    @SerializedName("fact")
    val fact: Fact,
    @SerializedName("forecasts")
    val forecasts: List<Forecast>,
    @SerializedName("info")
    val info: Info,
    @SerializedName("now")
    val now: Double,
    @SerializedName("now_dt")
    val nowDt: String
)