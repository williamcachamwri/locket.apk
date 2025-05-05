package cl.json.social;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.os.Build;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactContext;

public class TargetChosenReceiver extends BroadcastReceiver {
    private static final String EXTRA_RECEIVER_TOKEN = "receiver_token";
    private static final Object LOCK = new Object();
    private static Promise callback;
    private static TargetChosenReceiver sLastRegisteredReceiver;
    private static String sTargetChosenReceiveAction;

    public static boolean isSupported() {
        return true;
    }

    public static void registerCallbacks(Promise promise) {
        callback = promise;
    }

    public static IntentSender getSharingSenderIntent(ReactContext reactContext) {
        synchronized (LOCK) {
            if (sTargetChosenReceiveAction == null) {
                sTargetChosenReceiveAction = reactContext.getPackageName() + "/" + TargetChosenReceiver.class.getName() + "_ACTION";
            }
            Context applicationContext = reactContext.getApplicationContext();
            TargetChosenReceiver targetChosenReceiver = sLastRegisteredReceiver;
            if (targetChosenReceiver != null) {
                applicationContext.unregisterReceiver(targetChosenReceiver);
            }
            sLastRegisteredReceiver = new TargetChosenReceiver();
            if (Build.VERSION.SDK_INT < 34 || applicationContext.getApplicationInfo().targetSdkVersion < 34) {
                applicationContext.registerReceiver(sLastRegisteredReceiver, new IntentFilter(sTargetChosenReceiveAction));
            } else {
                applicationContext.registerReceiver(sLastRegisteredReceiver, new IntentFilter(sTargetChosenReceiveAction), 2);
            }
        }
        Intent intent = new Intent(sTargetChosenReceiveAction);
        intent.setPackage(reactContext.getPackageName());
        intent.setClass(reactContext.getApplicationContext(), TargetChosenReceiver.class);
        intent.putExtra(EXTRA_RECEIVER_TOKEN, sLastRegisteredReceiver.hashCode());
        return PendingIntent.getBroadcast(reactContext, 0, intent, 1409286144).getIntentSender();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001c, code lost:
        if (r4.hasExtra(EXTRA_RECEIVER_TOKEN) == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0029, code lost:
        if (r4.getIntExtra(EXTRA_RECEIVER_TOKEN, 0) == hashCode()) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002c, code lost:
        r3 = (android.content.ComponentName) r4.getParcelableExtra("android.intent.extra.CHOSEN_COMPONENT");
        r4 = com.facebook.react.bridge.Arguments.createMap();
        r4.putBoolean(com.google.firebase.analytics.FirebaseAnalytics.Param.SUCCESS, true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003f, code lost:
        if (r3 == null) goto L_0x004b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0041, code lost:
        r4.putString("message", r3.flattenToString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004b, code lost:
        r4.putString("message", "OK");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0052, code lost:
        callbackResolve(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onReceive(android.content.Context r3, android.content.Intent r4) {
        /*
            r2 = this;
            java.lang.Object r0 = LOCK
            monitor-enter(r0)
            cl.json.social.TargetChosenReceiver r1 = sLastRegisteredReceiver     // Catch:{ all -> 0x0056 }
            if (r1 == r2) goto L_0x0009
            monitor-exit(r0)     // Catch:{ all -> 0x0056 }
            return
        L_0x0009:
            android.content.Context r3 = r3.getApplicationContext()     // Catch:{ all -> 0x0056 }
            cl.json.social.TargetChosenReceiver r1 = sLastRegisteredReceiver     // Catch:{ all -> 0x0056 }
            r3.unregisterReceiver(r1)     // Catch:{ all -> 0x0056 }
            r3 = 0
            sLastRegisteredReceiver = r3     // Catch:{ all -> 0x0056 }
            monitor-exit(r0)     // Catch:{ all -> 0x0056 }
            java.lang.String r3 = "receiver_token"
            boolean r3 = r4.hasExtra(r3)
            if (r3 == 0) goto L_0x0055
            java.lang.String r3 = "receiver_token"
            r0 = 0
            int r3 = r4.getIntExtra(r3, r0)
            int r0 = r2.hashCode()
            if (r3 == r0) goto L_0x002c
            goto L_0x0055
        L_0x002c:
            java.lang.String r3 = "android.intent.extra.CHOSEN_COMPONENT"
            android.os.Parcelable r3 = r4.getParcelableExtra(r3)
            android.content.ComponentName r3 = (android.content.ComponentName) r3
            com.facebook.react.bridge.WritableMap r4 = com.facebook.react.bridge.Arguments.createMap()
            java.lang.String r0 = "success"
            r1 = 1
            r4.putBoolean(r0, r1)
            if (r3 == 0) goto L_0x004b
            java.lang.String r0 = "message"
            java.lang.String r3 = r3.flattenToString()
            r4.putString(r0, r3)
            goto L_0x0052
        L_0x004b:
            java.lang.String r3 = "message"
            java.lang.String r0 = "OK"
            r4.putString(r3, r0)
        L_0x0052:
            callbackResolve(r4)
        L_0x0055:
            return
        L_0x0056:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0056 }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: cl.json.social.TargetChosenReceiver.onReceive(android.content.Context, android.content.Intent):void");
    }

    public static void callbackResolve(Object obj) {
        Promise promise = callback;
        if (promise != null) {
            promise.resolve(obj);
        }
        callback = null;
    }

    public static void callbackReject(String str) {
        Promise promise = callback;
        if (promise != null) {
            promise.reject(str);
        }
        callback = null;
    }
}
