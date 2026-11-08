package com.k3.games.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_profiles")
data class GameProfile(
    @PrimaryKey val packageName: String,
    val displayName: String,
    val fpsCap: Int = 60,
    val performanceMode: String = "Balanced",
    val resolutionScale: Float = 1.0f,
    val doNotDisturb: Boolean = false,
    val autoRejectCalls: Boolean = false,
    val keepScreenAwake: Boolean = true,
    val showFpsOverlay: Boolean = true,
    val smartTuneEnabled: Boolean = false,
)
