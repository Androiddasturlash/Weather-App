package uz.mahmud.weatherapp.network.repository

import android.annotation.SuppressLint
import android.location.Geocoder
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import uz.mahmud.weatherapp.data.CurrentLocation
import uz.mahmud.weatherapp.data.RemoteLocation
import uz.mahmud.weatherapp.data.RemoteWeatherData
import uz.mahmud.weatherapp.network.api.WeatherAPI

class WeatherDataRepository(private val weatherAPI: WeatherAPI) {

    @SuppressLint("MissingPermission")
    fun getCurrentLocation(
        fusedLocationProviderClient: FusedLocationProviderClient,
        onSuccess: (currentLocation: CurrentLocation) -> Unit,
        onFailure: () -> Unit
    ) {
        fusedLocationProviderClient.getCurrentLocation(
            Priority.PRIORITY_HIGH_ACCURACY,
            CancellationTokenSource().token
        ).addOnSuccessListener { location ->
            if (location == null) {
                onFailure()
                return@addOnSuccessListener
            }
            onSuccess(
                CurrentLocation(
                    latitude = location.latitude,
                    longitude = location.longitude
                )
            )
        }.addOnFailureListener {
            onFailure()
        }
    }

    @Suppress("DEPRECATION")
    fun updateAddressText(
        currentLocation: CurrentLocation,
        geocoder: Geocoder
    ): CurrentLocation {
        val latitude = currentLocation.latitude ?: return currentLocation
        val longitude = currentLocation.longitude ?: return currentLocation
        return geocoder.getFromLocation(latitude, longitude,1)?.let { addresses ->
            val address = addresses[0]
            val addressText = StringBuilder()
            addressText.append(address.locality).append(", ")
            addressText.append(address.adminArea).append(", ")
            addressText.append(address.countryName)
            currentLocation.copy(
                location = addressText.toString()
            )
        } ?: currentLocation
    }

    suspend fun searchLocation(query: String): List<RemoteLocation>? {
        return try {
            val response = weatherAPI.searchLocation(query = query)

            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getWeatherData(latitude: Double, longitude: Double): RemoteWeatherData? {
        val response = weatherAPI.getWeatherData(query = "$latitude,$longitude")
        return if(response.isSuccessful) response.body() else null
    }
}