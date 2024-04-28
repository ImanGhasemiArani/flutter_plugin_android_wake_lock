package ghasemiarani.iman.android_wake_lock

import android.content.Context
import androidx.annotation.NonNull

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result

/** AndroidWakeLockPlugin */
class AndroidWakeLockPlugin : FlutterPlugin, MethodCallHandler {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private lateinit var channel: MethodChannel
    private lateinit var context: Context

    override fun onAttachedToEngine(flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
        channel = MethodChannel(flutterPluginBinding.binaryMessenger, Constants.CHANNEL_NAME)
        context = flutterPluginBinding.applicationContext
        channel.setMethodCallHandler(this)
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        channel.setMethodCallHandler(null)
    }

    override fun onMethodCall(call: MethodCall, result: Result) = when (call.method) {
        Constants.WAKE_UP_ACTION -> result.success(wakeUp())
        else -> result.notImplemented()
    }

    private fun wakeUp(): Boolean {
        return try {
            context.sendBroadcast(AndroidWakeLockBroadcastReceiver.getIntentWakeUp(context))
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
