package dev.vinits.mtgcmp

import android.app.Application

class MTGApp : Application() {
    override fun onCreate() {
        super.onCreate()
        AppMultiplatform.initialize()
    }
}