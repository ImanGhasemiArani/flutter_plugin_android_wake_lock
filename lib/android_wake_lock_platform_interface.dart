import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'android_wake_lock_method_channel.dart';

abstract class AndroidWakeLockPlatform extends PlatformInterface {
  /// Constructs a AndroidWakeLockPlatform.
  AndroidWakeLockPlatform() : super(token: _token);

  static final Object _token = Object();

  static AndroidWakeLockPlatform _instance = MethodChannelAndroidWakeLock();

  /// The default instance of [AndroidWakeLockPlatform] to use.
  ///
  /// Defaults to [MethodChannelAndroidWakeLock].
  static AndroidWakeLockPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [AndroidWakeLockPlatform] when
  /// they register themselves.
  static set instance(AndroidWakeLockPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<bool> wakeUp() {
    throw UnimplementedError('wakeUp() has not been implemented.');
  }
}
