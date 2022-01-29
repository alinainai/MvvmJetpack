package com.gas.di

import android.app.Application
import androidx.room.Room
import com.mihua.db.AppDatabase
import com.mihua.db.PokemonDao
import com.mihua.db.PokemonInfoDao
import com.mihua.db.TypeResponseConverter

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

  @Provides
  @Singleton
  fun provideAppDatabase(
    application: Application,
    typeResponseConverter: TypeResponseConverter
  ): AppDatabase {
    return Room
      .databaseBuilder(application, AppDatabase::class.java, "MihuaDex.db")
      .fallbackToDestructiveMigration()
      .addTypeConverter(typeResponseConverter)
      .build()
  }

  @Provides
  @Singleton
  fun providePokemonDao(appDatabase: AppDatabase): PokemonDao {
    return appDatabase.pokemonDao()
  }

  @Provides
  @Singleton
  fun providePokemonInfoDao(appDatabase: AppDatabase): PokemonInfoDao {
    return appDatabase.pokemonInfoDao()
  }

  @Provides
  @Singleton
  fun provideTypeResponseConverter(moshi: Moshi): TypeResponseConverter {
    return TypeResponseConverter(moshi)
  }
}
