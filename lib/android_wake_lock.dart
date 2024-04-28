import 'android_wake_lock_platform_interface.dart';

class AndroidWakeLock {
  AndroidWakeLock._();

  static Future<bool> wakeUp() async {
    return AndroidWakeLockPlatform.instance.wakeUp();
  }
}
