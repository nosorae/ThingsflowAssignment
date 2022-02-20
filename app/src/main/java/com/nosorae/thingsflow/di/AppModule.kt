package com.nosorae.thingsflow.di

import android.app.Application
import androidx.room.Room
import com.nosorae.thingsflow.common.Constants.BASE_URL
import com.nosorae.thingsflow.common.Constants.DATABASE_NAME
import com.nosorae.thingsflow.data.local.IssueDatabase
import com.nosorae.thingsflow.data.remote.IssueApi
import com.nosorae.thingsflow.data.repository.LocalIssueRepositoryImpl
import com.nosorae.thingsflow.data.repository.RemoteIssueRepositoryImpl
import com.nosorae.thingsflow.domain.repository.LocalIssueRepository
import com.nosorae.thingsflow.domain.repository.RemoteIssueRepository
import com.nosorae.thingsflow.domain.use_case.DeleteIssuesUseCase
import com.nosorae.thingsflow.domain.use_case.InsertIssuesUseCase
import com.nosorae.thingsflow.domain.use_case.LoadIssuesUseCase
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
    private fun buildOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor())
            .build()
    }

    @Provides
    @Singleton
    fun provideIssueRepository(api: IssueApi): RemoteIssueRepository {
        return RemoteIssueRepositoryImpl(api)
    }


    //--------------------------------------------------------------------


    // TODO Provide 방식 통일 및 모듈 나누기
    @Provides
    @Singleton
    fun provideIssueDatabase(app: Application): IssueDatabase {
        return Room.databaseBuilder(
            app,
            IssueDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db: IssueDatabase): LocalIssueRepository {
        return LocalIssueRepositoryImpl(db.issueDao)
    }

    @Provides
    @Singleton
    fun provideLoadIssuesUseCase(repository: LocalIssueRepository): LoadIssuesUseCase {
        return LoadIssuesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideInsertIssues(repository: LocalIssueRepository): InsertIssuesUseCase {
        return InsertIssuesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteIssues(repository: LocalIssueRepository): DeleteIssuesUseCase {
        return DeleteIssuesUseCase(repository)
    }



}