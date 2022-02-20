package com.nosorae.thingsflow.di

import com.nosorae.thingsflow.common.Constants.BASE_URL
import com.nosorae.thingsflow.data.remote.IssueApi
import com.nosorae.thingsflow.data.repository.RemoteIssueRepositoryImpl
import com.nosorae.thingsflow.domain.repository.RemoteIssueRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideIssueApi(): IssueApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(buildOkHttpClient())
            .build()
            .create(IssueApi::class.java)
    }

    @Provides
    @Singleton
    fun provideIssueRepository(api: IssueApi): RemoteIssueRepository {
        return RemoteIssueRepositoryImpl(api)
    }

    private fun buildOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor())
            .build()
    }

}