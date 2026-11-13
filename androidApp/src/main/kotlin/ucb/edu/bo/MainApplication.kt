package ucb.edu.bo

import android.app.Application
import ucb.edu.bo.core.notifications.LocalNotifier
import ucb.edu.bo.di.initKoinAndroid
import ucb.edu.bo.push.NotificationHelper
import ucb.edu.bo.scheduler.SyncScheduler

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoinAndroid(this)

        // Notificaciones locales: el codigo comun las pide y Android las muestra
        LocalNotifier.handler = { titulo, texto ->
            NotificationHelper.mostrar(this, titulo, texto)
        }

        // Sincronizacion en segundo plano (WorkManager)
        val scheduler = SyncScheduler(this)
        scheduler.schedulePeriodicalSync()
        scheduler.syncNow()
    }
}