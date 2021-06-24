package id.fadillah.jetpacksubmission.app

import android.app.Application
import id.fadillah.jetpacksubmission.core.di.databaseModule
import id.fadillah.jetpacksubmission.core.di.networkModule
import id.fadillah.jetpacksubmission.core.di.repositoryModule
import id.fadillah.jetpacksubmission.di.useCaseModule
import id.fadillah.jetpacksubmission.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

open class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}