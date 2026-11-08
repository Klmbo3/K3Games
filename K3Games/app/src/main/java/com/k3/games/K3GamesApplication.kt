package com.k3.games

import android.app.Application

class K3GamesApplication : Application() {
    val database: com.k3.games.data.GameProfileDatabase by lazy {
        com.k3.games.data.GameProfileDatabase.getInstance(this)
    }
}
