package io.sentry.android.core.cache;

import io.sentry.Hint;
import io.sentry.SentryEnvelope;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.UncaughtExceptionHandlerIntegration;
import io.sentry.android.core.AnrV2Integration;
import io.sentry.android.core.AppStartState;
import io.sentry.android.core.SentryAndroidOptions;
import io.sentry.android.core.internal.util.AndroidCurrentDateProvider;
import io.sentry.cache.EnvelopeCache;
import io.sentry.transport.ICurrentDateProvider;
import io.sentry.util.FileUtils;
import io.sentry.util.HintUtils;
import io.sentry.util.Objects;
import java.io.File;
import java.io.FileOutputStream;

public final class AndroidEnvelopeCache extends EnvelopeCache {
    public static final String LAST_ANR_REPORT = "last_anr_report";
    private final ICurrentDateProvider currentDateProvider;

    public AndroidEnvelopeCache(SentryAndroidOptions sentryAndroidOptions) {
        this(sentryAndroidOptions, AndroidCurrentDateProvider.getInstance());
    }

    AndroidEnvelopeCache(SentryAndroidOptions sentryAndroidOptions, ICurrentDateProvider iCurrentDateProvider) {
        super(sentryAndroidOptions, (String) Objects.requireNonNull(sentryAndroidOptions.getCacheDirPath(), "cacheDirPath must not be null"), sentryAndroidOptions.getMaxCacheItems());
        this.currentDateProvider = iCurrentDateProvider;
    }

    public void store(SentryEnvelope sentryEnvelope, Hint hint) {
        super.store(sentryEnvelope, hint);
        SentryAndroidOptions sentryAndroidOptions = (SentryAndroidOptions) this.options;
        Long appStartMillis = AppStartState.getInstance().getAppStartMillis();
        if (HintUtils.hasType(hint, UncaughtExceptionHandlerIntegration.UncaughtExceptionHint.class) && appStartMillis != null) {
            long currentTimeMillis = this.currentDateProvider.getCurrentTimeMillis() - appStartMillis.longValue();
            if (currentTimeMillis <= sentryAndroidOptions.getStartupCrashDurationThresholdMillis()) {
                sentryAndroidOptions.getLogger().log(SentryLevel.DEBUG, "Startup Crash detected %d milliseconds after SDK init. Writing a startup crash marker file to disk.", Long.valueOf(currentTimeMillis));
                writeStartupCrashMarkerFile();
            }
        }
        HintUtils.runIfHasType(hint, AnrV2Integration.AnrV2Hint.class, new AndroidEnvelopeCache$$ExternalSyntheticLambda0(this, sentryAndroidOptions));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$store$0$io-sentry-android-core-cache-AndroidEnvelopeCache  reason: not valid java name */
    public /* synthetic */ void m2401lambda$store$0$iosentryandroidcorecacheAndroidEnvelopeCache(SentryAndroidOptions sentryAndroidOptions, AnrV2Integration.AnrV2Hint anrV2Hint) {
        Long timestamp = anrV2Hint.timestamp();
        sentryAndroidOptions.getLogger().log(SentryLevel.DEBUG, "Writing last reported ANR marker with timestamp %d", timestamp);
        writeLastReportedAnrMarker(timestamp);
    }

    public File getDirectory() {
        return this.directory;
    }

    private void writeStartupCrashMarkerFile() {
        String outboxPath = this.options.getOutboxPath();
        if (outboxPath == null) {
            this.options.getLogger().log(SentryLevel.DEBUG, "Outbox path is null, the startup crash marker file will not be written", new Object[0]);
            return;
        }
        try {
            new File(outboxPath, EnvelopeCache.STARTUP_CRASH_MARKER_FILE).createNewFile();
        } catch (Throwable th) {
            this.options.getLogger().log(SentryLevel.ERROR, "Error writing the startup crash marker file to the disk", th);
        }
    }

    public static boolean hasStartupCrashMarker(SentryOptions sentryOptions) {
        String outboxPath = sentryOptions.getOutboxPath();
        if (outboxPath == null) {
            sentryOptions.getLogger().log(SentryLevel.DEBUG, "Outbox path is null, the startup crash marker file does not exist", new Object[0]);
            return false;
        }
        File file = new File(outboxPath, EnvelopeCache.STARTUP_CRASH_MARKER_FILE);
        try {
            boolean exists = file.exists();
            if (exists && !file.delete()) {
                sentryOptions.getLogger().log(SentryLevel.ERROR, "Failed to delete the startup crash marker file. %s.", file.getAbsolutePath());
            }
            return exists;
        } catch (Throwable th) {
            sentryOptions.getLogger().log(SentryLevel.ERROR, "Error reading/deleting the startup crash marker file on the disk", th);
            return false;
        }
    }

    public static Long lastReportedAnr(SentryOptions sentryOptions) {
        File file = new File((String) Objects.requireNonNull(sentryOptions.getCacheDirPath(), "Cache dir path should be set for getting ANRs reported"), LAST_ANR_REPORT);
        try {
            if (!file.exists() || !file.canRead()) {
                sentryOptions.getLogger().log(SentryLevel.DEBUG, "Last ANR marker does not exist. %s.", file.getAbsolutePath());
                return null;
            }
            String readText = FileUtils.readText(file);
            if (readText.equals("null")) {
                return null;
            }
            return Long.valueOf(Long.parseLong(readText.trim()));
        } catch (Throwable th) {
            sentryOptions.getLogger().log(SentryLevel.ERROR, "Error reading last ANR marker", th);
        }
    }

    private void writeLastReportedAnrMarker(Long l) {
        FileOutputStream fileOutputStream;
        String cacheDirPath = this.options.getCacheDirPath();
        if (cacheDirPath == null) {
            this.options.getLogger().log(SentryLevel.DEBUG, "Cache dir path is null, the ANR marker will not be written", new Object[0]);
            return;
        }
        try {
            fileOutputStream = new FileOutputStream(new File(cacheDirPath, LAST_ANR_REPORT));
            fileOutputStream.write(String.valueOf(l).getBytes(UTF_8));
            fileOutputStream.flush();
            fileOutputStream.close();
            return;
        } catch (Throwable th) {
            this.options.getLogger().log(SentryLevel.ERROR, "Error writing the ANR marker to the disk", th);
            return;
        }
        throw th;
    }
}
