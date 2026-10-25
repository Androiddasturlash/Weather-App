package uz.mahmud.weatherapp.dependency_injection

import org.koin.dsl.module
import uz.mahmud.weatherapp.storage.SharedPreferenceManager

val storageModule = module {
    single { SharedPreferenceManager(context = get(), gson = get()) }
}