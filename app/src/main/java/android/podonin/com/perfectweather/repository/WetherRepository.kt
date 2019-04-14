package android.podonin.com.perfectweather.repository

import android.podonin.com.perfectweather.api.GetWether
import android.podonin.com.perfectweather.model.WetherResponse
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WetherRepository {
    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val newRequest = original
                .newBuilder()
                .header("X-Yandex-API-Key", "6ce0b540-b136-48f6-950a-bd31d72417ae")
                .method(original.method(), original.body())
                .build()
            chain.proceed(newRequest)
        }
        .build()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.weather.yandex.ru/")
        .client(client)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getWether(lat: Double, lon: Double, days: Int, needHours: Boolean = false)
            : Observable<WetherResponse> {
        val request = retrofit.create(GetWether::class.java)
        return request.getWether(lat, lon, days, needHours)
    }
}