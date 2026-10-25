package uz.mahmud.weatherapp.network.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.mahmud.weatherapp.data.RemoteLocation
import uz.mahmud.weatherapp.data.RemoteWeatherData

interface WeatherAPI {

    companion object {
        const val  BASE_URL = "https://api.weatherapi.com/v1/"
        const val API_KEY = "a2033e23499e49ecba5145425263107"
    }

    @GET("search.json")
    suspend fun searchLocation(
        @Query("key") key: String = API_KEY,
        @Query("q") query: String
    ): Response<List<RemoteLocation>>

    @GET("forecast.json")
    suspend fun getWeatherData(
        @Query("key") key: String = API_KEY,
        @Query("q") query: String
    ): Response<RemoteWeatherData>
}