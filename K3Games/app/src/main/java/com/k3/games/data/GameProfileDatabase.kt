package com.k3.games.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context

@Dao
interface GameProfileDao {
    @Query("SELECT * FROM game_profiles ORDER BY displayName ASC")
    suspend fun getAll(): List<GameProfile>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(profile: GameProfile)

    @Query("DELETE FROM game_profiles WHERE packageName = :packageName")
    suspend fun delete(packageName: String)
}

@Database(entities = [GameProfile::class], version = 1, exportSchema = false)
abstract class GameProfileDatabase : RoomDatabase() {
    abstract fun gameProfileDao(): GameProfileDao

    companion object {
        @Volatile
        private var INSTANCE: GameProfileDatabase? = null

        fun getInstance(context: Context): GameProfileDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GameProfileDatabase::class.java,
                    "k3_games_profiles.db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}
