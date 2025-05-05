package com.adsbynimbus;

import android.app.Application;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import androidx.media3.common.MimeTypes;
import com.adsbynimbus.internal.Component;
import com.adsbynimbus.internal.Components;
import com.adsbynimbus.internal.Platform;
import com.adsbynimbus.internal.PlatformKt;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.firebase.dynamiclinks.DynamicLink;
import io.sentry.SentryEvent;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference0Impl;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001KB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u00100\u001a\u0002012\u0006\u00102\u001a\u000203H\u0007J\b\u00104\u001a\u00020\u0006H\u0007J\n\u00105\u001a\u0004\u0018\u00010\tH\u0007J\b\u00106\u001a\u00020\tH\u0007J\n\u00107\u001a\u0004\u0018\u00010\tH\u0007J\b\u00108\u001a\u00020\tH\u0007J\n\u00109\u001a\u0004\u0018\u00010\tH\u0007J\u0012\u0010*\u001a\u0004\u0018\u00010\t2\u0006\u0010:\u001a\u00020;H\u0007J0\u0010<\u001a\u0002012\u0006\u0010:\u001a\u00020;2\u0006\u0010=\u001a\u00020\t2\u0006\u0010>\u001a\u00020\t2\u000e\b\u0002\u0010?\u001a\b\u0012\u0004\u0012\u00020A0@H\u0007J\b\u0010B\u001a\u00020\u0004H\u0007J\b\u0010C\u001a\u00020\u0004H\u0007J\u0010\u0010D\u001a\u0002012\u0006\u00102\u001a\u000203H\u0007J\u0010\u0010E\u001a\u0002012\u0006\u0010F\u001a\u00020\u0004H\u0007J\u0010\u0010G\u001a\u0002012\u0006\u0010\u001c\u001a\u00020\tH\u0007J\u0010\u0010H\u001a\u0002012\u0006\u0010I\u001a\u00020\u0004H\u0007J\u0010\u0010J\u001a\u0002012\u0006\u0010&\u001a\u00020\tH\u0007R\u0012\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\u00020\u00068\u0006X\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0007\u0010\u0002R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R,\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8\u0006@FX\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\r\u0010\u0002\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0012\u001a\u00020\u00138Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u0004\u0018\u00010\u00178\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0018\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u0004\u0018\u00010\u00178\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u0012\u0010\u001c\u001a\u00020\t8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001d\u001a\u00020\u00048\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R1\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00048F@FX\u0002¢\u0006\u0018\u0012\u0004\b \u0010\u0002\u001a\u0004\b#\u0010\u0019\"\u0004\b$\u0010%*\u0004\b!\u0010\"R\u0014\u0010&\u001a\u0004\u0018\u00010\t8\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000R!\u0010'\u001a\u00020\t8FX\u0002¢\u0006\u0012\u0012\u0004\b(\u0010\u0002\u001a\u0004\b*\u0010+*\u0004\b)\u0010\"R\u000e\u0010,\u001a\u00020\tXT¢\u0006\u0002\n\u0000R\u001a\u0010-\u001a\b\u0012\u0004\u0012\u00020\t0.8\u0006@\u0006X\u000e¢\u0006\u0004\n\u0002\u0010/¨\u0006L"}, d2 = {"Lcom/adsbynimbus/Nimbus;", "", "()V", "COPPA", "", "DEFAULT_AD_INFO", "Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;", "getDEFAULT_AD_INFO$annotations", "EMPTY_AD_ID", "", "value", "", "adVisibilityMinPercentage", "getAdVisibilityMinPercentage$annotations", "getAdVisibilityMinPercentage", "()I", "setAdVisibilityMinPercentage", "(I)V", "applicationContext", "Landroid/app/Application;", "getApplicationContext", "()Landroid/app/Application;", "closeDrawable", "Landroid/graphics/drawable/Drawable;", "isInitialized", "()Z", "muteDrawable", "sdkName", "sessionId", "testMode", "<set-?>", "thirdPartyViewabilityEnabled", "getThirdPartyViewabilityEnabled$annotations", "getThirdPartyViewabilityEnabled$delegate", "()Ljava/lang/Object;", "getThirdPartyViewabilityEnabled", "setThirdPartyViewabilityEnabled", "(Z)V", "usPrivacyString", "userAgent", "getUserAgent$annotations", "getUserAgent$delegate", "getUserAgent", "()Ljava/lang/String;", "version", "videoMimeTypes", "", "[Ljava/lang/String;", "addLogger", "", "logger", "Lcom/adsbynimbus/Nimbus$Logger;", "getAdIdInfo", "getApiKey", "getId", "getPublisherKey", "getSessionId", "getUsPrivacyString", "context", "Landroid/content/Context;", "initialize", "publisherKey", "apiKey", "components", "", "Lcom/adsbynimbus/internal/Component;", "isCOPPA", "isTestMode", "removeLogger", "setCOPPA", "coppa", "setSessionId", "setTestMode", "enabled", "setUsPrivacyString", "Logger", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Nimbus.kt */
public final class Nimbus {
    public static boolean COPPA = false;
    public static final AdvertisingIdClient.Info DEFAULT_AD_INFO = PlatformKt.getDefaultAdInfo();
    public static final String EMPTY_AD_ID = "00000000-0000-0000-0000-000000000000";
    public static final Nimbus INSTANCE = new Nimbus();
    private static int adVisibilityMinPercentage = 25;
    public static Drawable closeDrawable = null;
    public static Drawable muteDrawable = null;
    public static final String sdkName = "Adsbynimbus";
    public static String sessionId = Components.getUuid();
    public static boolean testMode = false;
    public static String usPrivacyString = null;
    public static final String version = "2.26.1";
    public static String[] videoMimeTypes = {MimeTypes.VIDEO_MP4};

    @JvmStatic
    public static /* synthetic */ void getAdVisibilityMinPercentage$annotations() {
    }

    @Deprecated(message = "This field will be removed in a future release", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static /* synthetic */ void getDEFAULT_AD_INFO$annotations() {
    }

    @JvmStatic
    @Deprecated(message = "This property will be removed in a future release", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static /* synthetic */ void getThirdPartyViewabilityEnabled$annotations() {
    }

    @JvmStatic
    public static /* synthetic */ void getUserAgent$annotations() {
    }

    @JvmStatic
    public static final void initialize(Context context, String str, String str2) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "publisherKey");
        Intrinsics.checkNotNullParameter(str2, DynamicLink.Builder.KEY_API_KEY);
        initialize$default(context, str, str2, (Set) null, 8, (Object) null);
    }

    private Nimbus() {
    }

    public final Application getApplicationContext() {
        return PlatformKt.getApplication();
    }

    public static /* synthetic */ void initialize$default(Context context, String str, String str2, Set set, int i, Object obj) {
        if ((i & 8) != 0) {
            set = SetsKt.emptySet();
        }
        initialize(context, str, str2, set);
    }

    @JvmStatic
    public static final void initialize(Context context, String str, String str2, Set<? extends Component> set) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(str, "publisherKey");
        Intrinsics.checkNotNullParameter(str2, DynamicLink.Builder.KEY_API_KEY);
        Intrinsics.checkNotNullParameter(set, "components");
        PlatformKt.initializeSdk$default(context, str, str2, set, (CoroutineScope) null, 8, (Object) null);
    }

    static {
        ViewabilityProvider viewabilityProvider = ViewabilityProvider.INSTANCE;
        Platform platform = Platform.INSTANCE;
    }

    public static final boolean getThirdPartyViewabilityEnabled() {
        return ViewabilityProvider.thirdPartyViewabilityEnabled;
    }

    private static Object getThirdPartyViewabilityEnabled$delegate() {
        return Reflection.mutableProperty0(new MutablePropertyReference0Impl(ViewabilityProvider.INSTANCE, ViewabilityProvider.class, "thirdPartyViewabilityEnabled", "getThirdPartyViewabilityEnabled()Z", 0));
    }

    public static final void setThirdPartyViewabilityEnabled(boolean z) {
        ViewabilityProvider.thirdPartyViewabilityEnabled = z;
    }

    public static final String getUserAgent() {
        return Platform.INSTANCE.getUserAgent();
    }

    private static Object getUserAgent$delegate() {
        return Reflection.property0(new PropertyReference0Impl(Platform.INSTANCE, Platform.class, "userAgent", "getUserAgent()Ljava/lang/String;", 0));
    }

    @JvmStatic
    public static final void addLogger(Logger logger) {
        Intrinsics.checkNotNullParameter(logger, SentryEvent.JsonKeys.LOGGER);
        com.adsbynimbus.internal.Logger.getLoggers().add(logger);
    }

    @JvmStatic
    public static final void removeLogger(Logger logger) {
        Intrinsics.checkNotNullParameter(logger, SentryEvent.JsonKeys.LOGGER);
        com.adsbynimbus.internal.Logger.getLoggers().remove(logger);
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bæ\u0001\u0018\u00002\u00020\u0001:\u0001\bJ\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\t"}, d2 = {"Lcom/adsbynimbus/Nimbus$Logger;", "", "log", "", "priority", "", "message", "", "Default", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Nimbus.kt */
    public interface Logger {
        void log(int i, String str);

        @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\r"}, d2 = {"Lcom/adsbynimbus/Nimbus$Logger$Default;", "Lcom/adsbynimbus/Nimbus$Logger;", "level", "", "(I)V", "getLevel", "()I", "setLevel", "log", "", "priority", "message", "", "core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        /* compiled from: Nimbus.kt */
        public static final class Default implements Logger {
            private int level;

            public Default(int i) {
                this.level = i;
            }

            public final int getLevel() {
                return this.level;
            }

            public final void setLevel(int i) {
                this.level = i;
            }

            public void log(int i, String str) {
                Intrinsics.checkNotNullParameter(str, "message");
                if (i >= this.level) {
                    Class<Nimbus> cls = Nimbus.class;
                    Log.println(i, "Nimbus", str);
                }
            }
        }
    }

    @JvmStatic
    @Deprecated(message = "Use the Nimbus.testMode property instead", replaceWith = @ReplaceWith(expression = "testMode", imports = {}))
    public static final boolean isTestMode() {
        return testMode;
    }

    @JvmStatic
    @Deprecated(message = "Use the Nimbus.testMode property instead", replaceWith = @ReplaceWith(expression = "testMode", imports = {}))
    public static final void setTestMode(boolean z) {
        testMode = z;
    }

    @JvmStatic
    @Deprecated(message = "Use the Nimbus.COPPA property instead", replaceWith = @ReplaceWith(expression = "COPPA", imports = {}))
    public static final boolean isCOPPA() {
        return COPPA;
    }

    @JvmStatic
    @Deprecated(message = "Use the Nimbus.COPPA property instead", replaceWith = @ReplaceWith(expression = "COPPA", imports = {}))
    public static final void setCOPPA(boolean z) {
        COPPA = z;
    }

    @JvmStatic
    @Deprecated(message = "Use the Nimbus.sessionId property instead", replaceWith = @ReplaceWith(expression = "sessionId", imports = {}))
    public static final String getSessionId() {
        return sessionId;
    }

    @JvmStatic
    @Deprecated(message = "Use the Nimbus.sessionId property instead", replaceWith = @ReplaceWith(expression = "sessionId", imports = {}))
    public static final void setSessionId(String str) {
        Intrinsics.checkNotNullParameter(str, "sessionId");
        sessionId = str;
    }

    @JvmStatic
    @Deprecated(message = "Use the Nimbus.usPrivacyString property instead", replaceWith = @ReplaceWith(expression = "usPrivacyString", imports = {}))
    public static final void setUsPrivacyString(String str) {
        Intrinsics.checkNotNullParameter(str, "usPrivacyString");
        usPrivacyString = str;
    }

    @JvmStatic
    @Deprecated(message = "Use the Nimbus.usPrivacyString property instead", replaceWith = @ReplaceWith(expression = "usPrivacyString", imports = {}))
    public static final String getUsPrivacyString() {
        return usPrivacyString;
    }

    @JvmStatic
    @Deprecated(message = "This method will be removed in a future release", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final AdvertisingIdClient.Info getAdIdInfo() {
        return Platform.adInfo;
    }

    @JvmStatic
    @Deprecated(message = "This method will be removed in a future release", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final String getPublisherKey() {
        Object obj;
        try {
            Result.Companion companion = Result.Companion;
            obj = Result.m2444constructorimpl(Platform.publisherKey);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m2444constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m2450isFailureimpl(obj)) {
            obj = null;
        }
        String str = (String) obj;
        if (str == null) {
            return null;
        }
        if (str.length() > 0) {
            return str;
        }
        return null;
    }

    @JvmStatic
    @Deprecated(message = "This method will be removed in a future release", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final String getApiKey() {
        Object obj;
        try {
            Result.Companion companion = Result.Companion;
            obj = Result.m2444constructorimpl(Platform.apiKey);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m2444constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m2450isFailureimpl(obj)) {
            obj = null;
        }
        String str = (String) obj;
        if (str == null) {
            return null;
        }
        if (str.length() > 0) {
            return str;
        }
        return null;
    }

    @JvmStatic
    @Deprecated(message = "This method will be removed in a future release", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final String getUserAgent(Context context) {
        Object obj;
        Intrinsics.checkNotNullParameter(context, "context");
        try {
            Result.Companion companion = Result.Companion;
            obj = Result.m2444constructorimpl(getUserAgent());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m2444constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m2450isFailureimpl(obj)) {
            obj = null;
        }
        return (String) obj;
    }

    public static final int getAdVisibilityMinPercentage() {
        return adVisibilityMinPercentage;
    }

    public static final void setAdVisibilityMinPercentage(int i) {
        adVisibilityMinPercentage = RangesKt.coerceIn(i, 0, 100);
    }

    @JvmStatic
    @Deprecated(message = "This method will be removed in a future release", replaceWith = @ReplaceWith(expression = "", imports = {}))
    public static final String getId() {
        String str;
        try {
            Result.Companion companion = Result.Companion;
            str = Result.m2444constructorimpl(Platform.INSTANCE.getIid());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            str = Result.m2444constructorimpl(ResultKt.createFailure(th));
        }
        String uuid = Components.getUuid();
        if (Result.m2450isFailureimpl(str)) {
            str = uuid;
        }
        return (String) str;
    }

    public final boolean isInitialized() {
        if (Platform.publisherKey.length() > 0) {
            if (Platform.apiKey.length() > 0) {
                return true;
            }
        }
        return false;
    }
}
