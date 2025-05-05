package expo.modules.kotlin.sharedobjects;

import expo.modules.kotlin.AppContext;
import expo.modules.kotlin.jni.JavaScriptObject;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\r\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\u000e\u0010\u0005J\u0017\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u001aHÖ\u0001¢\u0006\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0001\u0002¨\u0006\u001d"}, d2 = {"Lexpo/modules/kotlin/sharedobjects/SharedObjectId;", "", "value", "", "constructor-impl", "(I)I", "getValue", "()I", "equals", "", "other", "equals-impl", "(ILjava/lang/Object;)Z", "hashCode", "hashCode-impl", "toJavaScriptObject", "Lexpo/modules/kotlin/jni/JavaScriptObject;", "appContext", "Lexpo/modules/kotlin/AppContext;", "toJavaScriptObject-impl", "(ILexpo/modules/kotlin/AppContext;)Lexpo/modules/kotlin/jni/JavaScriptObject;", "toNativeObject", "Lexpo/modules/kotlin/sharedobjects/SharedObject;", "toNativeObject-impl", "(ILexpo/modules/kotlin/AppContext;)Lexpo/modules/kotlin/sharedobjects/SharedObject;", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@JvmInline
/* compiled from: SharedObjectRegistry.kt */
public final class SharedObjectId {
    private final int value;

    /* renamed from: box-impl  reason: not valid java name */
    public static final /* synthetic */ SharedObjectId m2280boximpl(int i) {
        return new SharedObjectId(i);
    }

    /* renamed from: constructor-impl  reason: not valid java name */
    public static int m2281constructorimpl(int i) {
        return i;
    }

    /* renamed from: equals-impl  reason: not valid java name */
    public static boolean m2282equalsimpl(int i, Object obj) {
        return (obj instanceof SharedObjectId) && i == ((SharedObjectId) obj).m2288unboximpl();
    }

    /* renamed from: equals-impl0  reason: not valid java name */
    public static final boolean m2283equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* renamed from: hashCode-impl  reason: not valid java name */
    public static int m2284hashCodeimpl(int i) {
        return Integer.hashCode(i);
    }

    /* renamed from: toString-impl  reason: not valid java name */
    public static String m2287toStringimpl(int i) {
        return "SharedObjectId(value=" + i + ")";
    }

    public boolean equals(Object obj) {
        return m2282equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m2284hashCodeimpl(this.value);
    }

    public String toString() {
        return m2287toStringimpl(this.value);
    }

    /* renamed from: unbox-impl  reason: not valid java name */
    public final /* synthetic */ int m2288unboximpl() {
        return this.value;
    }

    private /* synthetic */ SharedObjectId(int i) {
        this.value = i;
    }

    public final int getValue() {
        return this.value;
    }

    /* renamed from: toNativeObject-impl  reason: not valid java name */
    public static final SharedObject m2286toNativeObjectimpl(int i, AppContext appContext) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        return appContext.getSharedObjectRegistry$expo_modules_core_release().m2292toNativeObjectkyJHjyY$expo_modules_core_release(i);
    }

    /* renamed from: toJavaScriptObject-impl  reason: not valid java name */
    public static final JavaScriptObject m2285toJavaScriptObjectimpl(int i, AppContext appContext) {
        Intrinsics.checkNotNullParameter(appContext, "appContext");
        SharedObject r1 = m2286toNativeObjectimpl(i, appContext);
        if (r1 == null) {
            return null;
        }
        return appContext.getSharedObjectRegistry$expo_modules_core_release().toJavaScriptObject$expo_modules_core_release(r1);
    }
}
