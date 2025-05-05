package expo.modules.kotlin.sharedobjects;

import expo.modules.kotlin.jni.JavaScriptObject;
import expo.modules.kotlin.jni.JavaScriptWeakObject;
import io.sentry.protocol.SentryStackFrame;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J%\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0013H\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0004H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aJ\u0015\u0010\u001b\u001a\u00020\u0004H\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u001e\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0011\u001a\u00020\tH\u0000¢\u0006\u0002\b\u001fJ\u0017\u0010 \u001a\u0004\u0018\u00010\t2\u0006\u0010\u0012\u001a\u00020\u0013H\u0000¢\u0006\u0002\b!J\u001c\u0010 \u001a\u0004\u0018\u00010\t2\u0006\u0010\u0018\u001a\u00020\u0004H\u0000ø\u0001\u0000¢\u0006\u0004\b\"\u0010#R\u0016\u0010\u0003\u001a\u00020\u0004X\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u0005R6\u0010\u0006\u001a\u001e\u0012\u0004\u0012\u00020\u0004\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bj\u0002`\u000b0\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006$"}, d2 = {"Lexpo/modules/kotlin/sharedobjects/SharedObjectRegistry;", "", "()V", "currentId", "Lexpo/modules/kotlin/sharedobjects/SharedObjectId;", "I", "pairs", "", "Lkotlin/Pair;", "Lexpo/modules/kotlin/sharedobjects/SharedObject;", "Lexpo/modules/kotlin/jni/JavaScriptWeakObject;", "Lexpo/modules/kotlin/sharedobjects/SharedObjectPair;", "getPairs$expo_modules_core_release", "()Ljava/util/Map;", "setPairs$expo_modules_core_release", "(Ljava/util/Map;)V", "add", "native", "js", "Lexpo/modules/kotlin/jni/JavaScriptObject;", "add-5WKnsLU$expo_modules_core_release", "(Lexpo/modules/kotlin/sharedobjects/SharedObject;Lexpo/modules/kotlin/jni/JavaScriptObject;)I", "delete", "", "id", "delete-kyJHjyY$expo_modules_core_release", "(I)V", "pullNextId", "pullNextId-HSeVr_g", "()I", "toJavaScriptObject", "toJavaScriptObject$expo_modules_core_release", "toNativeObject", "toNativeObject$expo_modules_core_release", "toNativeObject-kyJHjyY$expo_modules_core_release", "(I)Lexpo/modules/kotlin/sharedobjects/SharedObject;", "expo-modules-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SharedObjectRegistry.kt */
public final class SharedObjectRegistry {
    private int currentId = SharedObjectId.m2281constructorimpl(1);
    private Map<SharedObjectId, Pair<SharedObject, JavaScriptWeakObject>> pairs = new LinkedHashMap();

    public final Map<SharedObjectId, Pair<SharedObject, JavaScriptWeakObject>> getPairs$expo_modules_core_release() {
        return this.pairs;
    }

    public final void setPairs$expo_modules_core_release(Map<SharedObjectId, Pair<SharedObject, JavaScriptWeakObject>> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.pairs = map;
    }

    /* renamed from: pullNextId-HSeVr_g  reason: not valid java name */
    private final int m2289pullNextIdHSeVr_g() {
        int i;
        synchronized (this) {
            i = this.currentId;
            this.currentId = SharedObjectId.m2281constructorimpl(i + 1);
        }
        return i;
    }

    /* renamed from: add-5WKnsLU$expo_modules_core_release  reason: not valid java name */
    public final int m2290add5WKnsLU$expo_modules_core_release(SharedObject sharedObject, JavaScriptObject javaScriptObject) {
        Intrinsics.checkNotNullParameter(sharedObject, SentryStackFrame.JsonKeys.NATIVE);
        Intrinsics.checkNotNullParameter(javaScriptObject, "js");
        int r0 = m2289pullNextIdHSeVr_g();
        sharedObject.m2279setSharedObjectIdkyJHjyY$expo_modules_core_release(r0);
        JavaScriptObject.defineProperty$default(javaScriptObject, SharedObjectRegistryKt.sharedObjectIdPropertyName, r0, (List) null, 4, (Object) null);
        javaScriptObject.defineDeallocator$expo_modules_core_release(new SharedObjectRegistry$add$1(this, r0));
        JavaScriptWeakObject createWeak = javaScriptObject.createWeak();
        synchronized (this) {
            this.pairs.put(SharedObjectId.m2280boximpl(r0), TuplesKt.to(sharedObject, createWeak));
            Unit unit = Unit.INSTANCE;
        }
        return r0;
    }

    /* renamed from: delete-kyJHjyY$expo_modules_core_release  reason: not valid java name */
    public final void m2291deletekyJHjyY$expo_modules_core_release(int i) {
        Pair remove;
        synchronized (this) {
            remove = this.pairs.remove(SharedObjectId.m2280boximpl(i));
        }
        if (remove != null) {
            SharedObject sharedObject = (SharedObject) remove.component1();
            JavaScriptWeakObject javaScriptWeakObject = (JavaScriptWeakObject) remove.component2();
            sharedObject.m2279setSharedObjectIdkyJHjyY$expo_modules_core_release(SharedObjectId.m2281constructorimpl(0));
            sharedObject.deallocate();
        }
    }

    /* renamed from: toNativeObject-kyJHjyY$expo_modules_core_release  reason: not valid java name */
    public final SharedObject m2292toNativeObjectkyJHjyY$expo_modules_core_release(int i) {
        SharedObject sharedObject;
        synchronized (this) {
            Pair pair = this.pairs.get(SharedObjectId.m2280boximpl(i));
            sharedObject = pair != null ? (SharedObject) pair.getFirst() : null;
        }
        return sharedObject;
    }

    public final SharedObject toNativeObject$expo_modules_core_release(JavaScriptObject javaScriptObject) {
        Intrinsics.checkNotNullParameter(javaScriptObject, "js");
        if (!javaScriptObject.hasProperty(SharedObjectRegistryKt.sharedObjectIdPropertyName)) {
            return null;
        }
        Pair pair = this.pairs.get(SharedObjectId.m2280boximpl(SharedObjectId.m2281constructorimpl(javaScriptObject.getProperty(SharedObjectRegistryKt.sharedObjectIdPropertyName).getInt())));
        if (pair != null) {
            return (SharedObject) pair.getFirst();
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0018, code lost:
        r2 = (expo.modules.kotlin.jni.JavaScriptWeakObject) r2.getSecond();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final expo.modules.kotlin.jni.JavaScriptObject toJavaScriptObject$expo_modules_core_release(expo.modules.kotlin.sharedobjects.SharedObject r2) {
        /*
            r1 = this;
            java.lang.String r0 = "native"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            monitor-enter(r1)
            java.util.Map<expo.modules.kotlin.sharedobjects.SharedObjectId, kotlin.Pair<expo.modules.kotlin.sharedobjects.SharedObject, expo.modules.kotlin.jni.JavaScriptWeakObject>> r0 = r1.pairs     // Catch:{ all -> 0x0028 }
            int r2 = r2.m2278getSharedObjectIdHSeVr_g$expo_modules_core_release()     // Catch:{ all -> 0x0028 }
            expo.modules.kotlin.sharedobjects.SharedObjectId r2 = expo.modules.kotlin.sharedobjects.SharedObjectId.m2280boximpl(r2)     // Catch:{ all -> 0x0028 }
            java.lang.Object r2 = r0.get(r2)     // Catch:{ all -> 0x0028 }
            kotlin.Pair r2 = (kotlin.Pair) r2     // Catch:{ all -> 0x0028 }
            if (r2 == 0) goto L_0x0025
            java.lang.Object r2 = r2.getSecond()     // Catch:{ all -> 0x0028 }
            expo.modules.kotlin.jni.JavaScriptWeakObject r2 = (expo.modules.kotlin.jni.JavaScriptWeakObject) r2     // Catch:{ all -> 0x0028 }
            if (r2 == 0) goto L_0x0025
            expo.modules.kotlin.jni.JavaScriptObject r2 = r2.lock()     // Catch:{ all -> 0x0028 }
            goto L_0x0026
        L_0x0025:
            r2 = 0
        L_0x0026:
            monitor-exit(r1)
            return r2
        L_0x0028:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: expo.modules.kotlin.sharedobjects.SharedObjectRegistry.toJavaScriptObject$expo_modules_core_release(expo.modules.kotlin.sharedobjects.SharedObject):expo.modules.kotlin.jni.JavaScriptObject");
    }
}
