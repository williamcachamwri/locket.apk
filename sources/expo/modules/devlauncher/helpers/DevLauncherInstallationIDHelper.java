package expo.modules.devlauncher.helpers;

import android.content.Context;
import java.io.File;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0015\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0000¢\u0006\u0002\b\nJ\u000e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lexpo/modules/devlauncher/helpers/DevLauncherInstallationIDHelper;", "", "()V", "installationID", "", "getInstallationID", "context", "Landroid/content/Context;", "getInstallationIDFile", "Ljava/io/File;", "getInstallationIDFile$expo_dev_launcher_release", "getOrCreateInstallationID", "setInstallationID", "", "newID", "Companion", "expo-dev-launcher_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DevLauncherInstallationIDHelper.kt */
public final class DevLauncherInstallationIDHelper {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String INSTALLATION_ID_FILENAME = "expo-dev-launcher-installation-id.txt";
    private static final String TAG = "DevLauncherInstallationIDHelper";
    private String installationID;

    public final String getOrCreateInstallationID(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String installationID2 = getInstallationID(context);
        if (installationID2 != null) {
            return installationID2;
        }
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "toString(...)");
        setInstallationID(uuid, context);
        return uuid;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0039, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r1, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x003d, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0040, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0044, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String getInstallationID(android.content.Context r4) {
        /*
            r3 = this;
            java.lang.String r0 = r3.installationID
            if (r0 == 0) goto L_0x0005
            return r0
        L_0x0005:
            java.io.File r4 = r3.getInstallationIDFile$expo_dev_launcher_release(r4)
            boolean r0 = r4.exists()     // Catch:{ Exception -> 0x0045 }
            if (r0 == 0) goto L_0x004f
            io.sentry.instrumentation.file.SentryFileReader r0 = new io.sentry.instrumentation.file.SentryFileReader     // Catch:{ Exception -> 0x0045 }
            r0.<init>((java.io.File) r4)     // Catch:{ Exception -> 0x0045 }
            java.io.Closeable r0 = (java.io.Closeable) r0     // Catch:{ Exception -> 0x0045 }
            r4 = r0
            io.sentry.instrumentation.file.SentryFileReader r4 = (io.sentry.instrumentation.file.SentryFileReader) r4     // Catch:{ all -> 0x003e }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ all -> 0x003e }
            java.io.Reader r4 = (java.io.Reader) r4     // Catch:{ all -> 0x003e }
            r1.<init>(r4)     // Catch:{ all -> 0x003e }
            java.io.Closeable r1 = (java.io.Closeable) r1     // Catch:{ all -> 0x003e }
            r4 = r1
            java.io.BufferedReader r4 = (java.io.BufferedReader) r4     // Catch:{ all -> 0x0037 }
            java.lang.String r4 = r4.readLine()     // Catch:{ all -> 0x0037 }
            r3.installationID = r4     // Catch:{ all -> 0x0037 }
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0037 }
            r4 = 0
            kotlin.io.CloseableKt.closeFinally(r1, r4)     // Catch:{ all -> 0x003e }
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x003e }
            kotlin.io.CloseableKt.closeFinally(r0, r4)     // Catch:{ Exception -> 0x0045 }
            goto L_0x004f
        L_0x0037:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0039 }
        L_0x0039:
            r2 = move-exception
            kotlin.io.CloseableKt.closeFinally(r1, r4)     // Catch:{ all -> 0x003e }
            throw r2     // Catch:{ all -> 0x003e }
        L_0x003e:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0040 }
        L_0x0040:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r4)     // Catch:{ Exception -> 0x0045 }
            throw r1     // Catch:{ Exception -> 0x0045 }
        L_0x0045:
            r4 = move-exception
            java.lang.String r0 = TAG
            java.lang.String r1 = "Failed to read stored installation ID"
            java.lang.Throwable r4 = (java.lang.Throwable) r4
            io.sentry.android.core.SentryLogcatAdapter.e(r0, r1, r4)
        L_0x004f:
            java.lang.String r4 = r3.installationID
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.devlauncher.helpers.DevLauncherInstallationIDHelper.getInstallationID(android.content.Context):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r3, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0022, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void setInstallationID(java.lang.String r2, android.content.Context r3) {
        /*
            r1 = this;
            r1.installationID = r2
            java.io.File r2 = r1.getInstallationIDFile$expo_dev_launcher_release(r3)
            io.sentry.instrumentation.file.SentryFileWriter r3 = new io.sentry.instrumentation.file.SentryFileWriter     // Catch:{ Exception -> 0x0023 }
            r3.<init>((java.io.File) r2)     // Catch:{ Exception -> 0x0023 }
            java.io.Closeable r3 = (java.io.Closeable) r3     // Catch:{ Exception -> 0x0023 }
            r2 = r3
            io.sentry.instrumentation.file.SentryFileWriter r2 = (io.sentry.instrumentation.file.SentryFileWriter) r2     // Catch:{ all -> 0x001c }
            java.lang.String r0 = r1.installationID     // Catch:{ all -> 0x001c }
            r2.write(r0)     // Catch:{ all -> 0x001c }
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x001c }
            r2 = 0
            kotlin.io.CloseableKt.closeFinally(r3, r2)     // Catch:{ Exception -> 0x0023 }
            goto L_0x002d
        L_0x001c:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x001e }
        L_0x001e:
            r0 = move-exception
            kotlin.io.CloseableKt.closeFinally(r3, r2)     // Catch:{ Exception -> 0x0023 }
            throw r0     // Catch:{ Exception -> 0x0023 }
        L_0x0023:
            r2 = move-exception
            java.lang.String r3 = TAG
            java.lang.String r0 = "Failed to write or set resource values to installation ID file"
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            io.sentry.android.core.SentryLogcatAdapter.e(r3, r0, r2)
        L_0x002d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.devlauncher.helpers.DevLauncherInstallationIDHelper.setInstallationID(java.lang.String, android.content.Context):void");
    }

    public final File getInstallationIDFile$expo_dev_launcher_release(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return new File(context.getNoBackupFilesDir(), INSTALLATION_ID_FILENAME);
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n \u0006*\u0004\u0018\u00010\u00040\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lexpo/modules/devlauncher/helpers/DevLauncherInstallationIDHelper$Companion;", "", "()V", "INSTALLATION_ID_FILENAME", "", "TAG", "kotlin.jvm.PlatformType", "expo-dev-launcher_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: DevLauncherInstallationIDHelper.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
