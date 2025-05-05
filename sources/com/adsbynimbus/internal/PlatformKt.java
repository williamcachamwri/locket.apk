package com.adsbynimbus.internal;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.provider.Settings;
import androidx.media3.common.MimeTypes;
import com.adsbynimbus.Nimbus;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import expo.modules.interfaces.permissions.PermissionsResponse;
import java.lang.ref.WeakReference;
import java.util.Set;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a2\u0010\u0015\u001a\u00020\u0016*\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u000b2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\b\b\u0002\u0010\u001c\u001a\u00020\u001d\"\u001a\u0010\u0000\u001a\u00020\u0001X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0002\u0010\u0003\"\u0004\b\u0004\u0010\u0005\"\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\"\u0016\u0010\n\u001a\u00020\u000b*\u00020\f8Æ\u0002¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e\"\u0016\u0010\u000f\u001a\u00020\u000b*\u00020\u00108Ç\u0002¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012\"\u0016\u0010\u0013\u001a\u00020\u000b*\u00020\u00108Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0012¨\u0006\u001e"}, d2 = {"application", "Landroid/app/Application;", "getApplication", "()Landroid/app/Application;", "setApplication", "(Landroid/app/Application;)V", "defaultAdInfo", "Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;", "getDefaultAdInfo", "()Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;", "instanceId", "", "Landroid/content/SharedPreferences;", "getInstanceId", "(Landroid/content/SharedPreferences;)Ljava/lang/String;", "secureId", "Landroid/content/Context;", "getSecureId", "(Landroid/content/Context;)Ljava/lang/String;", "secureInstanceId", "getSecureInstanceId", "initializeSdk", "", "publisher", "api", "components", "", "Lcom/adsbynimbus/internal/Component;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "core_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Platform.kt */
public final class PlatformKt {
    public static Application application;
    private static final AdvertisingIdClient.Info defaultAdInfo = new AdvertisingIdClient.Info(Nimbus.EMPTY_AD_ID, true);

    public static final Application getApplication() {
        Application application2 = application;
        if (application2 != null) {
            return application2;
        }
        Intrinsics.throwUninitializedPropertyAccessException(MimeTypes.BASE_TYPE_APPLICATION);
        return null;
    }

    public static final void setApplication(Application application2) {
        Intrinsics.checkNotNullParameter(application2, "<set-?>");
        application = application2;
    }

    public static /* synthetic */ void initializeSdk$default(Context context, String str, String str2, Set set, CoroutineScope coroutineScope, int i, Object obj) {
        if ((i & 8) != 0) {
            coroutineScope = Components.getNimbusScope();
        }
        initializeSdk(context, str, str2, set, coroutineScope);
    }

    public static final void initializeSdk(Context context, String str, String str2, Set<? extends Component> set, CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        Intrinsics.checkNotNullParameter(str, "publisher");
        Intrinsics.checkNotNullParameter(str2, "api");
        Intrinsics.checkNotNullParameter(set, "components");
        Intrinsics.checkNotNullParameter(coroutineScope, PermissionsResponse.SCOPE_KEY);
        Context applicationContext = context.getApplicationContext();
        Intrinsics.checkNotNull(applicationContext, "null cannot be cast to non-null type android.app.Application");
        setApplication((Application) applicationContext);
        Platform platform = Platform.INSTANCE;
        Platform.apiKey = str2;
        Platform platform2 = Platform.INSTANCE;
        Platform.publisherKey = str;
        getApplication().registerActivityLifecycleCallbacks(Platform.INSTANCE);
        if (context instanceof Activity) {
            Platform.INSTANCE.setCurrentActivity(new WeakReference(context));
        }
        if (!set.isEmpty()) {
            for (Component install : set) {
                install.install();
            }
        } else {
            Job unused = BuildersKt__Builders_commonKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new PlatformKt$initializeSdk$2((Continuation<? super PlatformKt$initializeSdk$2>) null), 3, (Object) null);
        }
        Job unused2 = BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getIO(), (CoroutineStart) null, new PlatformKt$initializeSdk$3((Continuation<? super PlatformKt$initializeSdk$3>) null), 2, (Object) null);
    }

    public static final AdvertisingIdClient.Info getDefaultAdInfo() {
        return defaultAdInfo;
    }

    public static final String getInstanceId(SharedPreferences sharedPreferences) {
        String str;
        String string;
        String str2;
        Intrinsics.checkNotNullParameter(sharedPreferences, "<this>");
        try {
            Result.Companion companion = Result.Companion;
            string = Platform.INSTANCE.getSharedPreferences().getString(Components.INSTANCE_ID, (String) null);
            if (string == null) {
                Context application2 = getApplication();
                Result.Companion companion2 = Result.Companion;
                String string2 = Settings.Secure.getString(application2.getContentResolver(), "android_id");
                Intrinsics.checkNotNullExpressionValue(string2, "getString(contentResolve…ttings.Secure.ANDROID_ID)");
                byte[] bytes = string2.getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                str2 = Result.m2444constructorimpl(UUID.nameUUIDFromBytes(bytes).toString());
                String uuid = UUID.randomUUID().toString();
                Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID().toString()");
                if (Result.m2450isFailureimpl(str2)) {
                    str2 = uuid;
                }
                string = (String) str2;
                SharedPreferences.Editor edit = Platform.INSTANCE.getSharedPreferences().edit();
                edit.putString(Components.INSTANCE_ID, string);
                edit.apply();
            }
        } catch (Throwable th) {
            Result.Companion companion3 = Result.Companion;
            str = Result.m2444constructorimpl(ResultKt.createFailure(th));
        }
        str = Result.m2444constructorimpl(string);
        String uuid2 = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid2, "randomUUID().toString()");
        if (Result.m2450isFailureimpl(str)) {
            str = uuid2;
        }
        return (String) str;
    }

    public static final String getSecureId(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        Intrinsics.checkNotNullExpressionValue(string, "getString(contentResolve…ttings.Secure.ANDROID_ID)");
        return string;
    }

    public static final String getSecureInstanceId(Context context) {
        String str;
        Intrinsics.checkNotNullParameter(context, "<this>");
        try {
            Result.Companion companion = Result.Companion;
            String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            Intrinsics.checkNotNullExpressionValue(string, "getString(contentResolve…ttings.Secure.ANDROID_ID)");
            byte[] bytes = string.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            str = Result.m2444constructorimpl(UUID.nameUUIDFromBytes(bytes).toString());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            str = Result.m2444constructorimpl(ResultKt.createFailure(th));
        }
        String uuid = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid, "randomUUID().toString()");
        if (Result.m2450isFailureimpl(str)) {
            str = uuid;
        }
        return (String) str;
    }
}
