package com.example.liveintuitions

import android.app.Application
import com.google.firebase.FirebaseApp

class LiveIntuitions : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}