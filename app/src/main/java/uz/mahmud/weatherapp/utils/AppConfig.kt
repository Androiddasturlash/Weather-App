package uz.mahmud.weatherapp.utils

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import uz.mahmud.weatherapp.dependency_injection.networkModule
import uz.mahmud.weatherapp.dependency_injection.repositoryModule
import uz.mahmud.weatherapp.dependency_injection.serializerModule
import uz.mahmud.weatherapp.dependency_injection.storageModule
import uz.mahmud.weatherapp.dependency_injection.viewModeModule

class AppConfig : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppConfig)
            modules(
                listOf(
                    repositoryModule,
                    viewModeModule,
                    serializerModule,
                    storageModule,
                    networkModule
                )
            )
        }
    }
}