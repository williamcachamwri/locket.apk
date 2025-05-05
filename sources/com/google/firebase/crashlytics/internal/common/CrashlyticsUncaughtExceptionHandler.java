package com.google.firebase.crashlytics.internal.common;

import com.google.firebase.crashlytics.internal.CrashlyticsNativeComponent;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.settings.SettingsProvider;
import java.lang.Thread;
import java.util.concurrent.atomic.AtomicBoolean;

class CrashlyticsUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    private final CrashListener crashListener;
    private final Thread.UncaughtExceptionHandler defaultHandler;
    private final AtomicBoolean isHandlingException = new AtomicBoolean(false);
    private final CrashlyticsNativeComponent nativeComponent;
    private final SettingsProvider settingsProvider;

    interface CrashListener {
        void onUncaughtException(SettingsProvider settingsProvider, Thread thread, Throwable th);
    }

    public CrashlyticsUncaughtExceptionHandler(CrashListener crashListener2, SettingsProvider settingsProvider2, Thread.UncaughtExceptionHandler uncaughtExceptionHandler, CrashlyticsNativeComponent crashlyticsNativeComponent) {
        this.crashListener = crashListener2;
        this.settingsProvider = settingsProvider2;
        this.defaultHandler = uncaughtExceptionHandler;
        this.nativeComponent = crashlyticsNativeComponent;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0035, code lost:
        if (r7.defaultHandler == null) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0037, code lost:
        com.google.firebase.crashlytics.internal.Logger.getLogger().d("Completed exception processing. Invoking default exception handler.");
        r7.defaultHandler.uncaughtException(r8, r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0044, code lost:
        com.google.firebase.crashlytics.internal.Logger.getLogger().d("Completed exception processing, but no default exception handler.");
        java.lang.System.exit(1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004e, code lost:
        r7.isHandlingException.set(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0053, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0024, code lost:
        if (r7.defaultHandler != null) goto L_0x0037;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void uncaughtException(java.lang.Thread r8, java.lang.Throwable r9) {
        /*
            r7 = this;
            java.lang.String r0 = "Completed exception processing. Invoking default exception handler."
            java.lang.String r1 = "Completed exception processing, but no default exception handler."
            java.util.concurrent.atomic.AtomicBoolean r2 = r7.isHandlingException
            r3 = 1
            r2.set(r3)
            r2 = 0
            boolean r4 = r7.shouldRecordUncaughtException(r8, r9)     // Catch:{ Exception -> 0x0029 }
            if (r4 == 0) goto L_0x0019
            com.google.firebase.crashlytics.internal.common.CrashlyticsUncaughtExceptionHandler$CrashListener r4 = r7.crashListener     // Catch:{ Exception -> 0x0029 }
            com.google.firebase.crashlytics.internal.settings.SettingsProvider r5 = r7.settingsProvider     // Catch:{ Exception -> 0x0029 }
            r4.onUncaughtException(r5, r8, r9)     // Catch:{ Exception -> 0x0029 }
            goto L_0x0022
        L_0x0019:
            com.google.firebase.crashlytics.internal.Logger r4 = com.google.firebase.crashlytics.internal.Logger.getLogger()     // Catch:{ Exception -> 0x0029 }
            java.lang.String r5 = "Uncaught exception will not be recorded by Crashlytics."
            r4.d(r5)     // Catch:{ Exception -> 0x0029 }
        L_0x0022:
            java.lang.Thread$UncaughtExceptionHandler r4 = r7.defaultHandler
            if (r4 == 0) goto L_0x0044
            goto L_0x0037
        L_0x0027:
            r4 = move-exception
            goto L_0x0054
        L_0x0029:
            r4 = move-exception
            com.google.firebase.crashlytics.internal.Logger r5 = com.google.firebase.crashlytics.internal.Logger.getLogger()     // Catch:{ all -> 0x0027 }
            java.lang.String r6 = "An error occurred in the uncaught exception handler"
            r5.e(r6, r4)     // Catch:{ all -> 0x0027 }
            java.lang.Thread$UncaughtExceptionHandler r4 = r7.defaultHandler
            if (r4 == 0) goto L_0x0044
        L_0x0037:
            com.google.firebase.crashlytics.internal.Logger r1 = com.google.firebase.crashlytics.internal.Logger.getLogger()
            r1.d(r0)
            java.lang.Thread$UncaughtExceptionHandler r0 = r7.defaultHandler
            r0.uncaughtException(r8, r9)
            goto L_0x004e
        L_0x0044:
            com.google.firebase.crashlytics.internal.Logger r8 = com.google.firebase.crashlytics.internal.Logger.getLogger()
            r8.d(r1)
            java.lang.System.exit(r3)
        L_0x004e:
            java.util.concurrent.atomic.AtomicBoolean r8 = r7.isHandlingException
            r8.set(r2)
            return
        L_0x0054:
            java.lang.Thread$UncaughtExceptionHandler r5 = r7.defaultHandler
            if (r5 == 0) goto L_0x0065
            com.google.firebase.crashlytics.internal.Logger r1 = com.google.firebase.crashlytics.internal.Logger.getLogger()
            r1.d(r0)
            java.lang.Thread$UncaughtExceptionHandler r0 = r7.defaultHandler
            r0.uncaughtException(r8, r9)
            goto L_0x006f
        L_0x0065:
            com.google.firebase.crashlytics.internal.Logger r8 = com.google.firebase.crashlytics.internal.Logger.getLogger()
            r8.d(r1)
            java.lang.System.exit(r3)
        L_0x006f:
            java.util.concurrent.atomic.AtomicBoolean r8 = r7.isHandlingException
            r8.set(r2)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.crashlytics.internal.common.CrashlyticsUncaughtExceptionHandler.uncaughtException(java.lang.Thread, java.lang.Throwable):void");
    }

    /* access modifiers changed from: package-private */
    public boolean isHandlingException() {
        return this.isHandlingException.get();
    }

    private boolean shouldRecordUncaughtException(Thread thread, Throwable th) {
        if (thread == null) {
            Logger.getLogger().e("Crashlytics will not record uncaught exception; null thread");
            return false;
        } else if (th == null) {
            Logger.getLogger().e("Crashlytics will not record uncaught exception; null throwable");
            return false;
        } else if (!this.nativeComponent.hasCrashDataForCurrentSession()) {
            return true;
        } else {
            Logger.getLogger().d("Crashlytics will not record uncaught exception; native crash exists for session.");
            return false;
        }
    }
}
