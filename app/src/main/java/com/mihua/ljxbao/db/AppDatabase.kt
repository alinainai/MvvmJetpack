package com.mihua.ljxbao.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mihua.ljxbao.bean.Pokemon
import com.mihua.ljxbao.bean.PokemonInfo


@Database(entities = [Pokemon::class, PokemonInfo::class], version = 1, exportSchema = false)
@TypeConverters(value = [TypeResponseConverter::class])
abstract class AppDatabase : RoomDatabase() {

  abstract fun pokemonDao(): PokemonDao
  abstract fun pokemonInfoDao(): PokemonInfoDao
}