package android.podonin.com.perfectweather.model

import com.google.gson.annotations.SerializedName

data class NightShort(
    @SerializedName("condition")
    val condition: String,
    @SerializedName("feels_like")
    val feelsLike: Double,
    @SerializedName("humidity")
    val humidity: Double,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("prec_mm")
    val precMm: Double,
    @SerializedName("prec_prob")
    val precProb: Double,
    @SerializedName("pressure_mm")
    val pressureMm: Double,
    @SerializedName("pressure_pa")
    val pressurePa: Double,
    @SerializedName("soil_moisture")
    val soilMoisture: Double,
    @SerializedName("soil_temp")
    val soilTemp: Double,
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("temp_water")
    val tempWater: Double,
    @SerializedName("wind_dir")
    val windDir: String,
    @SerializedName("wind_gust")
    val windGust: Double,
    @SerializedName("wind_speed")
    val windSpeed: Double
)