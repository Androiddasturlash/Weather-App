package uz.mahmud.weatherapp.dependency_injection

import org.koin.dsl.module
import uz.mahmud.weatherapp.network.repository.WeatherDataRepository

val repositoryModule = module {
    single { WeatherDataRepository(weatherAPI = get()) }
}