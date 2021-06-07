package id.fadillah.jetpacksubmission.di.core

import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import id.fadillah.jetpacksubmission.data.MovieRepository
import id.fadillah.jetpacksubmission.data.source.local.LocalDataSource
import id.fadillah.jetpacksubmission.data.source.local.room.MovieDatabase
import id.fadillah.jetpacksubmission.data.source.network.ApiService
import id.fadillah.jetpacksubmission.data.source.network.RemoteDataSource
import id.fadillah.jetpacksubmission.domain.repository.IMovieRepository
import id.fadillah.jetpacksubmission.utils.helper.ConstantHelper.MAIN_URL
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieDatabase>().movieDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDatabase::class.java, "Movie.db"
        ).fallbackToDestructiveMigration().build()
    }
}
val networkModule = module {
    single {
        ChuckerInterceptor.Builder(androidContext())
            .collector(ChuckerCollector(androidContext()))
            .maxContentLength(250000L)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(false)
            .build()
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<ChuckerInterceptor>())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(MAIN_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}
val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IMovieRepository> { MovieRepository(get(), get(), androidContext()) }
}