package ghasemiarani.iman.android_wake_lock

import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.PowerManager

class AndroidWakeLockBroadcastReceiver : BroadcastReceiver() {

    companion object {
        fun getIntentWakeUp(context: Context) = Intent(
            context, AndroidWakeLockBroadcastReceiver::class.java
        ).apply { action = "${context.packageName}.${Constants.WAKE_UP_ACTION}" }
    }

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action ?: return

        when (action) {
            "${context.packageName}.${Constants.WAKE_UP_ACTION}" -> {
                try {
                    wakeUp(context)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    private fun wakeUp(context: Context) {
        val powerManager = context.getSystemService(Activity.POWER_SERVICE) as PowerManager
        val wakeLock = powerManager.newWakeLock(
            PowerManager.SCREEN_BRIGHT_WAKE_LOCK or PowerManager.FULL_WAKE_LOCK or PowerManager.ACQUIRE_CAUSES_WAKEUP,
            "WatchTrackerPlugin:WakeUp"
        )
        wakeLock.acquire(0L)
        wakeLock.release()
    }
}
