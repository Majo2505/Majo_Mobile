package ucb.edu.bo

import android.app.Application
import ucb.edu.bo.di.initKoinAndroid

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoinAndroid(this)
    }
}
