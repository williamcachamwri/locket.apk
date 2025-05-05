package io.sentry.cache;

import io.sentry.IOptionsObserver;
import io.sentry.JsonDeserializer;
import io.sentry.SentryLevel;
import io.sentry.SentryOptions;
import io.sentry.protocol.SdkVersion;
import java.util.Map;

public final class PersistingOptionsObserver implements IOptionsObserver {
    public static final String DIST_FILENAME = "dist.json";
    public static final String ENVIRONMENT_FILENAME = "environment.json";
    public static final String OPTIONS_CACHE = ".options-cache";
    public static final String PROGUARD_UUID_FILENAME = "proguard-uuid.json";
    public static final String RELEASE_FILENAME = "release.json";
    public static final String SDK_VERSION_FILENAME = "sdk-version.json";
    public static final String TAGS_FILENAME = "tags.json";
    private final SentryOptions options;

    public PersistingOptionsObserver(SentryOptions sentryOptions) {
        this.options = sentryOptions;
    }

    private void serializeToDisk(Runnable runnable) {
        try {
            this.options.getExecutorService().submit((Runnable) new PersistingOptionsObserver$$ExternalSyntheticLambda3(this, runnable));
        } catch (Throwable th) {
            this.options.getLogger().log(SentryLevel.ERROR, "Serialization task could not be scheduled", th);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$serializeToDisk$0$io-sentry-cache-PersistingOptionsObserver  reason: not valid java name */
    public /* synthetic */ void m2409lambda$serializeToDisk$0$iosentrycachePersistingOptionsObserver(Runnable runnable) {
        try {
            runnable.run();
        } catch (Throwable th) {
            this.options.getLogger().log(SentryLevel.ERROR, "Serialization task failed", th);
        }
    }

    public void setRelease(String str) {
        serializeToDisk(new PersistingOptionsObserver$$ExternalSyntheticLambda2(this, str));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setRelease$1$io-sentry-cache-PersistingOptionsObserver  reason: not valid java name */
    public /* synthetic */ void m2413lambda$setRelease$1$iosentrycachePersistingOptionsObserver(String str) {
        if (str == null) {
            delete(RELEASE_FILENAME);
        } else {
            store(str, RELEASE_FILENAME);
        }
    }

    public void setProguardUuid(String str) {
        serializeToDisk(new PersistingOptionsObserver$$ExternalSyntheticLambda0(this, str));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setProguardUuid$2$io-sentry-cache-PersistingOptionsObserver  reason: not valid java name */
    public /* synthetic */ void m2412lambda$setProguardUuid$2$iosentrycachePersistingOptionsObserver(String str) {
        if (str == null) {
            delete(PROGUARD_UUID_FILENAME);
        } else {
            store(str, PROGUARD_UUID_FILENAME);
        }
    }

    public void setSdkVersion(SdkVersion sdkVersion) {
        serializeToDisk(new PersistingOptionsObserver$$ExternalSyntheticLambda4(this, sdkVersion));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setSdkVersion$3$io-sentry-cache-PersistingOptionsObserver  reason: not valid java name */
    public /* synthetic */ void m2414lambda$setSdkVersion$3$iosentrycachePersistingOptionsObserver(SdkVersion sdkVersion) {
        if (sdkVersion == null) {
            delete(SDK_VERSION_FILENAME);
        } else {
            store(sdkVersion, SDK_VERSION_FILENAME);
        }
    }

    public void setDist(String str) {
        serializeToDisk(new PersistingOptionsObserver$$ExternalSyntheticLambda1(this, str));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setDist$4$io-sentry-cache-PersistingOptionsObserver  reason: not valid java name */
    public /* synthetic */ void m2410lambda$setDist$4$iosentrycachePersistingOptionsObserver(String str) {
        if (str == null) {
            delete(DIST_FILENAME);
        } else {
            store(str, DIST_FILENAME);
        }
    }

    public void setEnvironment(String str) {
        serializeToDisk(new PersistingOptionsObserver$$ExternalSyntheticLambda6(this, str));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setEnvironment$5$io-sentry-cache-PersistingOptionsObserver  reason: not valid java name */
    public /* synthetic */ void m2411lambda$setEnvironment$5$iosentrycachePersistingOptionsObserver(String str) {
        if (str == null) {
            delete(ENVIRONMENT_FILENAME);
        } else {
            store(str, ENVIRONMENT_FILENAME);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: lambda$setTags$6$io-sentry-cache-PersistingOptionsObserver  reason: not valid java name */
    public /* synthetic */ void m2415lambda$setTags$6$iosentrycachePersistingOptionsObserver(Map map) {
        store(map, "tags.json");
    }

    public void setTags(Map<String, String> map) {
        serializeToDisk(new PersistingOptionsObserver$$ExternalSyntheticLambda5(this, map));
    }

    private <T> void store(T t, String str) {
        CacheUtils.store(this.options, t, OPTIONS_CACHE, str);
    }

    private void delete(String str) {
        CacheUtils.delete(this.options, OPTIONS_CACHE, str);
    }

    public static <T> T read(SentryOptions sentryOptions, String str, Class<T> cls) {
        return read(sentryOptions, str, cls, (JsonDeserializer) null);
    }

    /* JADX WARNING: type inference failed for: r4v0, types: [io.sentry.JsonDeserializer, io.sentry.JsonDeserializer<R>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T, R> T read(io.sentry.SentryOptions r1, java.lang.String r2, java.lang.Class<T> r3, io.sentry.JsonDeserializer<R> r4) {
        /*
            java.lang.String r0 = ".options-cache"
            java.lang.Object r1 = io.sentry.cache.CacheUtils.read(r1, r0, r2, r3, r4)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.cache.PersistingOptionsObserver.read(io.sentry.SentryOptions, java.lang.String, java.lang.Class, io.sentry.JsonDeserializer):java.lang.Object");
    }
}
