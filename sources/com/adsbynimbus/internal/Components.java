package com.adsbynimbus.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import androidx.lifecycle.LifecycleCoroutineScope;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import java.util.Set;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000D\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001fH\bø\u0001\u0000\u001a\u0010\u0010 \u001a\u00020\u001d*\b\u0012\u0004\u0012\u00020\"0!\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0011\u0010\u0002\u001a\u00020\u00038G¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004\"\u0011\u0010\u0005\u001a\u00020\u00038G¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0004\"\u0011\u0010\u0006\u001a\u00020\u00038G¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004\"\u0011\u0010\u0007\u001a\u00020\u00038G¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0004\"\u0011\u0010\b\u001a\u00020\u00038G¢\u0006\u0006\u001a\u0004\b\b\u0010\u0004\"\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0011\u0010\u0011\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0016\u0010\u0014\u001a\u00020\u0003*\u00020\u00158Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0016\"\u0016\u0010\u0014\u001a\u00020\u0003*\u00020\u00178Æ\u0002¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0018\"\u0015\u0010\u0019\u001a\u00020\n*\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b\u0002\u0007\n\u0005\b20\u0001¨\u0006#"}, d2 = {"INSTANCE_ID", "", "isApi21", "", "()Z", "isApi23", "isApi26", "isApi28", "isApi29", "nimbusScope", "Lkotlinx/coroutines/CoroutineScope;", "getNimbusScope", "()Lkotlinx/coroutines/CoroutineScope;", "sharedPreferences", "Landroid/content/SharedPreferences;", "getSharedPreferences", "()Landroid/content/SharedPreferences;", "uuid", "getUuid", "()Ljava/lang/String;", "isLandscape", "Landroid/content/Context;", "(Landroid/content/Context;)Z", "", "(I)Z", "lifecycleOrNimbusScope", "getLifecycleOrNimbusScope", "(Landroid/content/Context;)Lkotlinx/coroutines/CoroutineScope;", "ifBelowAndroid7", "", "action", "Lkotlin/Function0;", "install", "", "Lcom/adsbynimbus/internal/Component;", "core_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Component.kt */
public final class Components {
    public static final String INSTANCE_ID = "Nimbus-Instance-Id";
    private static final CoroutineScope nimbusScope = CoroutineScopeKt.CoroutineScope(new CoroutineName("Nimbus"));
    private static final SharedPreferences sharedPreferences;
    private static final String uuid;

    public static final void ifBelowAndroid7(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "action");
    }

    public static final boolean isApi21() {
        return true;
    }

    public static final boolean isApi23() {
        return true;
    }

    public static final boolean isApi26() {
        return true;
    }

    public static final boolean isLandscape(int i) {
        return i == 2;
    }

    public static final void install(Set<? extends Component> set) {
        Intrinsics.checkNotNullParameter(set, "<this>");
        if (!set.isEmpty()) {
            for (Component install : set) {
                install.install();
            }
            return;
        }
        Job unused = BuildersKt__Builders_commonKt.launch$default(nimbusScope, (CoroutineContext) null, (CoroutineStart) null, new Components$install$2((Continuation<? super Components$install$2>) null), 3, (Object) null);
    }

    static {
        Object obj;
        String uuid2 = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(uuid2, "randomUUID().toString()");
        uuid = uuid2;
        try {
            Result.Companion companion = Result.Companion;
            obj = Result.m2444constructorimpl(Platform.INSTANCE.getSharedPreferences());
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.m2444constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m2450isFailureimpl(obj)) {
            obj = null;
        }
        sharedPreferences = (SharedPreferences) obj;
    }

    public static final CoroutineScope getNimbusScope() {
        return nimbusScope;
    }

    public static final CoroutineScope getLifecycleOrNimbusScope(Context context) {
        LifecycleCoroutineScope lifecycleScope;
        Intrinsics.checkNotNullParameter(context, "<this>");
        LifecycleOwner lifecycleOwner = context instanceof LifecycleOwner ? (LifecycleOwner) context : null;
        return (lifecycleOwner == null || (lifecycleScope = LifecycleOwnerKt.getLifecycleScope(lifecycleOwner)) == null) ? nimbusScope : lifecycleScope;
    }

    public static final String getUuid() {
        return uuid;
    }

    public static final boolean isApi28() {
        return Build.VERSION.SDK_INT >= 28;
    }

    public static final boolean isApi29() {
        return Build.VERSION.SDK_INT >= 29;
    }

    public static final boolean isLandscape(Context context) {
        Intrinsics.checkNotNullParameter(context, "<this>");
        return context.getResources().getConfiguration().orientation == 2;
    }

    public static final SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}
