package android.podonin.com.perfectweather.api

import android.podonin.com.perfectweather.model.WetherResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface GetWether {
    @GET("v1/forecast?")
    fun getWether(@Query("lat") lat: Double,
                  @Query("lon") lon: Double,
                  @Query("limit") days: Int,
                  @Query("hours") needHours: Boolean): Observable<WetherResponse>
}