import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'android_wake_lock_platform_interface.dart';

/// An implementation of [AndroidWakeLockPlatform] that uses method channels.
class MethodChannelAndroidWakeLock extends AndroidWakeLockPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('android_wake_lock');

  @override
  Future<bool> wakeUp() async {
    return await methodChannel.invokeMethod<bool>('wakeUp') ?? false;
  }
}
