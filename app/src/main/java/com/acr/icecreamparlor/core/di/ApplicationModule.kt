package com.acr.icecreamparlor.core.di

import android.content.Context
import com.acr.icecreamparlor.AndroidApplication
import com.acr.icecreamparlor.data.repository.GetIceCreamsIceCreamsDataRepoImpl
import com.acr.icecreamparlor.data.service.GetIceCreamsDataService
import com.acr.icecreamparlor.domain.datainformation.GetIceCreamsDataUseCase
import com.acr.icecreamparlor.domain.repository.GetIceCreamsDataRepo
import com.acr.icecreamparlor.presentation.viewmodel.WelcomeViewModel
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: AndroidApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    fun provideRetrofit() = Retrofit.Builder()
        .baseUrl("https://gl-endpoint.herokuapp.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // Service
    @Provides
    @Singleton
    fun provideGetDataService(retrofit: Retrofit) = GetIceCreamsDataService(retrofit)

    // Repository
    @Provides
    @Singleton
    fun provideGetDataRepo(getIceCreamsDataService: GetIceCreamsDataService): GetIceCreamsDataRepo = GetIceCreamsIceCreamsDataRepoImpl(getIceCreamsDataService)

    // UseCase
    @Provides
    @Singleton
    fun provideGetDataInformationUseCase(getIceCreamsDataRepo: GetIceCreamsDataRepo) = GetIceCreamsDataUseCase(getIceCreamsDataRepo)

    // ViewModel
    @Provides
    fun provideHomeViewModel(getIceCreamsDataUseCase: GetIceCreamsDataUseCase) = WelcomeViewModel(getIceCreamsDataUseCase)
}
