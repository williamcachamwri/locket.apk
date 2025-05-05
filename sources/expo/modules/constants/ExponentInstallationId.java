package expo.modules.constants;

import android.content.Context;
import android.content.SharedPreferences;
import expo.modules.notifications.serverregistration.InstallationId;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\r\u001a\u00020\fJ\b\u0010\u000e\u001a\u0004\u0018\u00010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\b8BX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lexpo/modules/constants/ExponentInstallationId;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mSharedPreferences", "Landroid/content/SharedPreferences;", "nonBackedUpUuidFile", "Ljava/io/File;", "getNonBackedUpUuidFile", "()Ljava/io/File;", "uuid", "", "getOrCreateUUID", "getUUID", "Companion", "expo-constants_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ExponentInstallationId.kt */
public final class ExponentInstallationId {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String LEGACY_UUID_KEY = "uuid";
    public static final String UUID_FILE_NAME = "expo_installation_uuid.txt";
    private final Context context;
    private final SharedPreferences mSharedPreferences;
    private String uuid;

    public ExponentInstallationId(Context context2) {
        Intrinsics.checkNotNullParameter(context2, "context");
        this.context = context2;
        SharedPreferences sharedPreferences = context2.getSharedPreferences(InstallationId.LEGACY_PREFERENCES_FILE_NAME, 0);
        Intrinsics.checkNotNullExpressionValue(sharedPreferences, "getSharedPreferences(...)");
        this.mSharedPreferences = sharedPreferences;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003c, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r5, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0040, code lost:
        throw r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0043, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r3, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0047, code lost:
        throw r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0079, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r5, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x007d, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String getUUID() {
        /*
            r7 = this;
            java.lang.String r0 = r7.uuid
            if (r0 == 0) goto L_0x0005
            return r0
        L_0x0005:
            java.io.File r0 = r7.getNonBackedUpUuidFile()
            r1 = 1
            r2 = 0
            io.sentry.instrumentation.file.SentryFileReader r3 = new io.sentry.instrumentation.file.SentryFileReader     // Catch:{ Exception -> 0x0048 }
            r3.<init>((java.io.File) r0)     // Catch:{ Exception -> 0x0048 }
            java.io.Closeable r3 = (java.io.Closeable) r3     // Catch:{ Exception -> 0x0048 }
            r4 = r3
            io.sentry.instrumentation.file.SentryFileReader r4 = (io.sentry.instrumentation.file.SentryFileReader) r4     // Catch:{ all -> 0x0041 }
            java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch:{ all -> 0x0041 }
            java.io.Reader r4 = (java.io.Reader) r4     // Catch:{ all -> 0x0041 }
            r5.<init>(r4)     // Catch:{ all -> 0x0041 }
            java.io.Closeable r5 = (java.io.Closeable) r5     // Catch:{ all -> 0x0041 }
            r4 = r5
            java.io.BufferedReader r4 = (java.io.BufferedReader) r4     // Catch:{ all -> 0x003a }
            java.lang.String r4 = r4.readLine()     // Catch:{ all -> 0x003a }
            java.util.UUID r4 = java.util.UUID.fromString(r4)     // Catch:{ all -> 0x003a }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x003a }
            r7.uuid = r4     // Catch:{ all -> 0x003a }
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x003a }
            kotlin.io.CloseableKt.closeFinally(r5, r2)     // Catch:{ all -> 0x0041 }
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0041 }
            kotlin.io.CloseableKt.closeFinally(r3, r2)     // Catch:{ Exception -> 0x0048 }
            goto L_0x0053
        L_0x003a:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x003c }
        L_0x003c:
            r6 = move-exception
            kotlin.io.CloseableKt.closeFinally(r5, r4)     // Catch:{ all -> 0x0041 }
            throw r6     // Catch:{ all -> 0x0041 }
        L_0x0041:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0043 }
        L_0x0043:
            r5 = move-exception
            kotlin.io.CloseableKt.closeFinally(r3, r4)     // Catch:{ Exception -> 0x0048 }
            throw r5     // Catch:{ Exception -> 0x0048 }
        L_0x0048:
            r3 = move-exception
            boolean r4 = r3 instanceof java.io.IOException
            if (r4 == 0) goto L_0x004f
            r4 = r1
            goto L_0x0051
        L_0x004f:
            boolean r4 = r3 instanceof java.lang.IllegalArgumentException
        L_0x0051:
            if (r4 == 0) goto L_0x00a8
        L_0x0053:
            java.lang.String r3 = r7.uuid
            if (r3 == 0) goto L_0x0058
            return r3
        L_0x0058:
            android.content.SharedPreferences r3 = r7.mSharedPreferences
            java.lang.String r4 = "uuid"
            java.lang.String r3 = r3.getString(r4, r2)
            if (r3 == 0) goto L_0x00a5
            r7.uuid = r3
            io.sentry.instrumentation.file.SentryFileWriter r5 = new io.sentry.instrumentation.file.SentryFileWriter     // Catch:{ IOException -> 0x007e }
            r5.<init>((java.io.File) r0)     // Catch:{ IOException -> 0x007e }
            java.io.Closeable r5 = (java.io.Closeable) r5     // Catch:{ IOException -> 0x007e }
            r0 = r5
            io.sentry.instrumentation.file.SentryFileWriter r0 = (io.sentry.instrumentation.file.SentryFileWriter) r0     // Catch:{ all -> 0x0077 }
            r0.write(r3)     // Catch:{ all -> 0x0077 }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0077 }
            kotlin.io.CloseableKt.closeFinally(r5, r2)     // Catch:{ IOException -> 0x007e }
            goto L_0x0096
        L_0x0077:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0079 }
        L_0x0079:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r5, r0)     // Catch:{ IOException -> 0x007e }
            throw r1     // Catch:{ IOException -> 0x007e }
        L_0x007e:
            r0 = move-exception
            java.lang.String r1 = expo.modules.constants.ExponentInstallationIdKt.TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Error while migrating UUID from legacy storage. "
            r2.<init>(r3)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            io.sentry.android.core.SentryLogcatAdapter.e(r1, r0)
            r1 = 0
        L_0x0096:
            if (r1 == 0) goto L_0x00a5
            android.content.SharedPreferences r0 = r7.mSharedPreferences
            android.content.SharedPreferences$Editor r0 = r0.edit()
            android.content.SharedPreferences$Editor r0 = r0.remove(r4)
            r0.apply()
        L_0x00a5:
            java.lang.String r0 = r7.uuid
            return r0
        L_0x00a8:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.constants.ExponentInstallationId.getUUID():java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002d, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0031, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String getOrCreateUUID() {
        /*
            r4 = this;
            java.lang.String r0 = r4.getUUID()
            if (r0 == 0) goto L_0x0007
            return r0
        L_0x0007:
            java.util.UUID r0 = java.util.UUID.randomUUID()
            java.lang.String r0 = r0.toString()
            r4.uuid = r0
            io.sentry.instrumentation.file.SentryFileWriter r0 = new io.sentry.instrumentation.file.SentryFileWriter     // Catch:{ IOException -> 0x0032 }
            java.io.File r1 = r4.getNonBackedUpUuidFile()     // Catch:{ IOException -> 0x0032 }
            r0.<init>((java.io.File) r1)     // Catch:{ IOException -> 0x0032 }
            java.io.Closeable r0 = (java.io.Closeable) r0     // Catch:{ IOException -> 0x0032 }
            r1 = r0
            io.sentry.instrumentation.file.SentryFileWriter r1 = (io.sentry.instrumentation.file.SentryFileWriter) r1     // Catch:{ all -> 0x002b }
            java.lang.String r2 = r4.uuid     // Catch:{ all -> 0x002b }
            r1.write(r2)     // Catch:{ all -> 0x002b }
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x002b }
            r1 = 0
            kotlin.io.CloseableKt.closeFinally(r0, r1)     // Catch:{ IOException -> 0x0032 }
            goto L_0x0049
        L_0x002b:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x002d }
        L_0x002d:
            r2 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r1)     // Catch:{ IOException -> 0x0032 }
            throw r2     // Catch:{ IOException -> 0x0032 }
        L_0x0032:
            r0 = move-exception
            java.lang.String r1 = expo.modules.constants.ExponentInstallationIdKt.TAG
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Error while writing new UUID. "
            r2.<init>(r3)
            java.lang.StringBuilder r0 = r2.append(r0)
            java.lang.String r0 = r0.toString()
            io.sentry.android.core.SentryLogcatAdapter.e(r1, r0)
        L_0x0049:
            java.lang.String r0 = r4.uuid
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.constants.ExponentInstallationId.getOrCreateUUID():java.lang.String");
    }

    private final File getNonBackedUpUuidFile() {
        return new File(this.context.getNoBackupFilesDir(), "expo_installation_uuid.txt");
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lexpo/modules/constants/ExponentInstallationId$Companion;", "", "()V", "LEGACY_UUID_KEY", "", "UUID_FILE_NAME", "expo-constants_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ExponentInstallationId.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
