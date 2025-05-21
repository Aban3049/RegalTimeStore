package org.abanapps.regal_time.app

import android.app.Application
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        Firebase.initialize(context = this@App)
    }

}