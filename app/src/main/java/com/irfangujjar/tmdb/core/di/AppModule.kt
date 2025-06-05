package com.irfangujjar.tmdb.core.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.irfangujjar.tmdb.BuildConfig
import com.irfangujjar.tmdb.core.data_store.DataStoreUtil
import com.irfangujjar.tmdb.core.db.AppDatabase
import com.irfangujjar.tmdb.core.urls.URLS
import com.irfangujjar.tmdb.features.main.celebs.data.data_sources.remote.api.CelebApi
import com.irfangujjar.tmdb.features.main.movies.data.data_sources.remote.api.MovieApi
import com.irfangujjar.tmdb.features.main.search.data.data_sources.remote.api.SearchApi
import com.irfangujjar.tmdb.features.main.tv_shows.data.data_sources.remote.api.TvShowApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        val client = OkHttpClient().newBuilder()
            .addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                    val request: Request = chain.request()
                        .newBuilder()
                        .header("accept", "application/json")
                        .header("Authorization", "Bearer ${BuildConfig.BEARER_TOKEN}")
                        .build()
                    return chain.proceed(request)
                }
            })
        return Retrofit.Builder()
            .baseUrl(URLS.BASE_URL)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesMovieApi(retrofit: Retrofit): MovieApi = retrofit.create(MovieApi::class.java)

    @Provides
    @Singleton
    fun providesTvShowApi(retrofit: Retrofit): TvShowApi = retrofit.create(TvShowApi::class.java)

    @Provides
    @Singleton
    fun providesCelebApi(retrofit: Retrofit): CelebApi = retrofit.create(CelebApi::class.java)

    @Provides
    @Singleton
    fun providesSearchApi(retrofit: Retrofit): SearchApi = retrofit.create(SearchApi::class.java)

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<androidx.datastore.preferences.core.Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(SharedPreferencesMigration(context, DataStoreUtil.DATA_STORE_NAME)),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = { context.preferencesDataStoreFile(DataStoreUtil.DATA_STORE_NAME) }
        )
    }

    @Provides
    @Singleton
    fun providesDataBase(
        @ApplicationContext context: Context,
    ): AppDatabase =
        Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            name = "tmdb_db"
        ).build()
}