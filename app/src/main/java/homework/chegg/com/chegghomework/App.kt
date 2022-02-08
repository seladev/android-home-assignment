package homework.chegg.com.chegghomework

import android.app.Application
import android.content.Context

/**
 * Created by seladev
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        application = this
    }

    companion object {
        var application: Application? = null
            private set
        val context: Context
            get() = application!!.applicationContext
    }
}