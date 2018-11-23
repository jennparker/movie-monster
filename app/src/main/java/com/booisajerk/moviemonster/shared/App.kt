package com.booisajerk.moviemonster.shared

import android.app.Application

open class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
            private set
    }
}