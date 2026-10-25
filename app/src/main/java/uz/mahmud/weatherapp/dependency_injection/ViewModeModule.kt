package uz.mahmud.weatherapp.dependency_injection

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import uz.mahmud.weatherapp.fragments.home.HomeViewModel
import uz.mahmud.weatherapp.fragments.location.LocationViewModel

val viewModeModule = module {
    viewModel { HomeViewModel(weatherDataRepository = get()) }
    viewModel { LocationViewModel(weatherDataRepository = get()) }
}