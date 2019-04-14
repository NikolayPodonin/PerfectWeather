package android.podonin.com.perfectweather.model

import com.google.gson.annotations.SerializedName

data class NightShort(
    @SerializedName("condition")
    val condition: String,
    @SerializedName("feels_like")
    val feelsLike: Int,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("prec_mm")
    val precMm: Int,
    @SerializedName("prec_prob")
    val precProb: Int,
    @SerializedName("pressure_mm")
    val pressureMm: Int,
    @SerializedName("pressure_pa")
    val pressurePa: Int,
    @SerializedName("soil_moisture")
    val soilMoisture: Double,
    @SerializedName("soil_temp")
    val soilTemp: Int,
    @SerializedName("temp")
    val temp: Int,
    @SerializedName("temp_water")
    val tempWater: Int,
    @SerializedName("wind_dir")
    val windDir: String,
    @SerializedName("wind_gust")
    val windGust: Double,
    @SerializedName("wind_speed")
    val windSpeed: Int
)