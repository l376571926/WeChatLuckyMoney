package xyz.monkeytong.hongbao.utils;

import android.app.KeyguardManager;
import android.content.Context;
import android.os.PowerManager;

/**
 * Created by Zhongyi on 1/29/16.
 */
public class PowerUtil {
    private PowerManager.WakeLock wakeLock;
    private KeyguardManager.KeyguardLock keyguardLock;

    public PowerUtil(Context context) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "HongbaoWakelock");
        KeyguardManager km = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        keyguardLock = km.newKeyguardLock("HongbaoKeyguardLock");
    }

    public void handleWakeLock(boolean isWake) {
        if (isWake) {
            wakeLock.acquire(1800000);
            keyguardLock.disableKeyguard();
        } else {
            if (wakeLock.isHeld()) {
                wakeLock.release();
                keyguardLock.reenableKeyguard();
            }
        }
    }
}
