package org.abanapps.regal_time.app

import android.app.Application
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize
import org.abanapps.regal_time.store.di.initializeKoin
import org.koin.android.ext.koin.androidContext

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initializeKoin(
            config = {
                androidContext(this@App)
            }
        )
        Firebase.initialize(context = this@App)
    }

}