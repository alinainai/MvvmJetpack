package com.mihua.ljxbao.di

import com.gas.moshi.MyKotlinJsonAdapterFactory
import com.gas.moshi.MyStandardJsonAdapters
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.IO
    }
}
